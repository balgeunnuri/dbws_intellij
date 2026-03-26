
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>- edit page -</h1>
<form action="user-edit" method="post">
<div class="info-wrap">
  <div class="info-row">
    <div>ID.</div>
    <div>${sessionScope.user.id}</div>
  </div>
  <div class="info-row">
    <div>PW.</div>
    <div><input name="pw" type="text" value="${sessionScope.user.pw}"></div>
  </div>
  <div class="info-row">
    <div>NAME.</div>
    <div><input name="name" type="text" value="${sessionScope.user.name}"></div>
  </div>
  <div>
    <button class="movie-btn" onclick="">done</button>
    <button type="button" class="movie-btn" onclick="history.back()">cancel</button>
  </div>

</div>
</form>
</body>
</html>
