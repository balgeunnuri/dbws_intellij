<%--
  Created by IntelliJ IDEA.
  User: soldesk
  Date: 2026-03-24
  Time: 오전 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${sessionScope.user.id}(${sessionScope.user.name}) 님 환영합니다. <br>
<button onclick="location.href='user-info'">mypage</button> <button onclick="location.href='user-login'">logout</button>

</body>
</html>
