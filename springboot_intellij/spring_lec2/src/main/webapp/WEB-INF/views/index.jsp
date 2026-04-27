<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<button onclick="location.href='/product/all'">products</button>
<button onclick="location.href='/products'">products (controller2)</button>
<%--비동기--%>
<button onclick="location.href='/api/product'">products-async</button>
</body>
</html>