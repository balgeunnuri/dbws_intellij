<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/product.css">
</head>
<body>
<h1>- product reg -</h1>
<form action="/product/create" method="post">
    <div>
        <div>name <input type="text" name="p_name"></div>
        <div>price <input type="text" name="p_price"></div>
        <div>
            <button>add</button>
        </div>
    </div>
</form>
<hr>
<h1>- product update -</h1>
<form action="/product/update" method="post">
    <div>
        <div>
            <select name="p_no">
                <c:forEach items="${products}" var="p">
                    <option value="${p.p_no}">no. ${p.p_no}</option>
                </c:forEach>
            </select>
        </div>
        <div><input type="text" placeholder="name" name="p_name"></div>
        <div><input type="text" placeholder="price" name="p_price"></div>
        <div>
            <button>update</button>
        </div>
    </div>
</form>
<hr>
<h1>- product delete -</h1>
<form action="/product/delete">
    <input type="text" name="pk">
    <button>delete</button>
</form>
<hr>
<h1>- product list -</h1>
<div>
    <div class="item">
        <div>no.</div>
        <div>name</div>
        <div>price</div>
    </div>
    <c:forEach items="${products}" var="p">
        <div class="item">
            <div>${p.p_no}</div>
            <div onclick="location.href='/product/detail?pk=${p.p_no}'">${p.p_name}</div>
            <div>${p.p_price}</div>
            <div>
                <button onclick="location.href='/product/delete?pk=${p.p_no}'">del</button>
            </div>
        </div>
    </c:forEach>
</div>


</body>
</html>