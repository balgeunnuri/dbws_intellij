<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--위에 코드 갑자기 왜 생김? index 복붙인데, 무슨 뜻,의미?--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>user page</h1>

<form action="/user" method="post">
    <input type="text" name="l_id"> <br>
    <input type="text" name="l_pw"> <br>
    <input type="text" name="l_name"> <br>
    <button>sign up</button>
</form>

<hr>
<div>
    <c:forEach items="${users}" var="user">
        <h1>ID : ${user.l_id} / Name : ${user.l_name}
        <button onclick="location.href='/user/del?id=${user.l_id}'">delete</button>
        <button onclick="location.href='/user/${user.l_id}'">mypage</button>
<%--            경로에 변수 넣으면 @PathVariable 쓸 생각하기--%>
            <button onclick="location.href='/user/detail?l_id=${user.l_id}&l_pw=${user.l_pw}&l_name=${user.l_name}'">mypage2</button>
        </h1>

    </c:forEach>
</div>

</body>


</html>