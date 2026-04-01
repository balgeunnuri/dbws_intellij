
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>- my page -</h1>
<div class="info-wrap">
  <div class="info-row">
    <div>ID.</div>
    <div>${sessionScope.user.id}</div>
  </div>
  <div class="info-row">
    <div>PW.</div>
    <div>${sessionScope.user.pw}</div>
  </div>
  <div class="info-row">
    <div>NAME.</div>
    <div>${sessionScope.user.name}</div>
  </div>
  <div>
    <button class="movie-btn" onclick="location.href='user-edit'">edit</button>
    <button class="movie-btn" onclick="deleteUser()">delete</button>
    <div class="confirm" style="display: none">
      <form action="user-delete" method="post">
        <input type="password" name="pw">
        <button class="movie-btn conf">confirm</button>
      </form>
    </div>
  </div>

</div>
<script !src="">
  function deleteUser() {
    // let ok = confirm("Are you sure?");
    // if (ok) {
    //   location.href = "/user-delete";
    // }
    document.querySelector('.confirm').style.display = 'block';
  }
</script>
</body>
</html>
