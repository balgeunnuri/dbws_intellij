<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>my page</h1>

<div>
    <div>ID. ${user.l_id}</div>
    <div>PW. ${user.l_pw}</div>
    <div>NAME. ${user.l_name}</div>
</div>
<hr>
${userVO}
<%--값의 종류 뭘 땡긴 거? attribute.? 내가 안 넣었는데 만들어줌 첫글자를 소문자로 해야 만들어줌?--%>

</body>


</html>