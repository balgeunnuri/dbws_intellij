const express = require('express');
const http = require('http');
const { Server } = require('socket.io');
const path = require('path');

const app = express();
const server = http.createServer(app);
const io = new Server(server);

const PORT = process.env.PORT || 3000;

// 접속 중인 유저 목록
const users = new Map(); // socketId -> { username, room }
const rooms = new Map(); // roomName -> Set of socketIds

// 정적 파일 서빙
app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

io.on('connection', (socket) => {
  console.log(`[+] 새 연결: ${socket.id}`);

  // 채팅방 입장
  socket.on('join', ({ username, room }) => {
    if (!username || !room) return;

    // 기존 방에서 퇴장
    const prevUser = users.get(socket.id);
    if (prevUser) {
      leaveRoom(socket, prevUser.room);
    }

    // 새 방 입장
    users.set(socket.id, { username, room });
    socket.join(room);

    if (!rooms.has(room)) {
      rooms.set(room, new Set());
    }
    rooms.get(room).add(socket.id);

    // 입장 알림
    socket.to(room).emit('system', {
      message: `${username}님이 입장했습니다.`,
      timestamp: Date.now(),
    });

    // 현재 방 유저 목록 전송
    broadcastRoomUsers(room);

    // 입장 확인
    socket.emit('joined', { username, room });

    console.log(`[JOIN] ${username} -> #${room}`);
  });

  // 메시지 전송
  socket.on('message', ({ text }) => {
    const user = users.get(socket.id);
    if (!user || !text?.trim()) return;

    const payload = {
      id: `${socket.id}-${Date.now()}`,
      username: user.username,
      text: text.trim(),
      timestamp: Date.now(),
      socketId: socket.id,
    };

    io.to(user.room).emit('message', payload);
    console.log(`[MSG] #${user.room} | ${user.username}: ${text.trim()}`);
  });

  // 타이핑 이벤트
  socket.on('typing', (isTyping) => {
    const user = users.get(socket.id);
    if (!user) return;
    socket.to(user.room).emit('typing', {
      username: user.username,
      isTyping,
    });
  });

  // 연결 해제
  socket.on('disconnect', () => {
    const user = users.get(socket.id);
    if (user) {
      leaveRoom(socket, user.room);
      users.delete(socket.id);
    }
    console.log(`[-] 연결 해제: ${socket.id}`);
  });

  function leaveRoom(socket, room) {
    const user = users.get(socket.id);
    if (!user) return;

    socket.leave(room);

    if (rooms.has(room)) {
      rooms.get(room).delete(socket.id);
      if (rooms.get(room).size === 0) {
        rooms.delete(room);
      }
    }

    socket.to(room).emit('system', {
      message: `${user.username}님이 퇴장했습니다.`,
      timestamp: Date.now(),
    });

    broadcastRoomUsers(room);
  }

  function broadcastRoomUsers(room) {
    const roomSocketIds = rooms.get(room) || new Set();
    const roomUsers = [...roomSocketIds].map((id) => {
      const u = users.get(id);
      return u ? u.username : null;
    }).filter(Boolean);

    io.to(room).emit('roomUsers', { room, users: roomUsers });
  }
});

server.listen(PORT, () => {
  console.log(`\n🚀 채팅 서버 실행 중: http://localhost:${PORT}\n`);
});
