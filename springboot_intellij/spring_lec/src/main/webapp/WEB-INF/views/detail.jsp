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

<div>
    <div>No. ${menu.m_no}</div>
    <div>Name. ${menu.m_name}</div>
    <div>Price. ${menu.m_price}</div>
</div>

</body>


</html>