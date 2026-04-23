<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>jsp loaded</h1>

<form action="/menu" method="post">
    <input type="text" name="m_name">
    <input type="text" name="m_price">
    <button>add</button>
</form>

<%--파라미터 값도 프레임워크에서는 db랑 똑같이 맞춰야 되는 게 규칙--%>

<hr>
<h1> <a href="/menu">전체 메뉴 조회</a> </h1>
</body>
</html>