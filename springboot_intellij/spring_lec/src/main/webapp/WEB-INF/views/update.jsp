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
<h1>detail page</h1>

<form action="/menu/update" method="post">
<div>
    <div>No. ${menu.m_no}</div>
    <div>Name. <input type="text" name="m_name" value="${menu.m_name}"> </div>
    <div>Price. <input type="text" name="m_price" value="${menu.m_price}"> </div>
<%--    name 부여 값받기를 VO나 DTO로 받아낼 생각인 것--%>
    <div>
        <button name="m_no" value="${menu.m_no}">done</button>
<%--        pk값 넘겨주기--%>
    </div>
</div>
</form>

</body>


</html>