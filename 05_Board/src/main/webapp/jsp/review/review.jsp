<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Review Page ~~~</h2>

<div class="review-container">
    <div>
        <div class="review-title">
            Review Page <a href="review-add">[write]</a>
        </div>
        <c:forEach var="r" items="${reviews}">
            <div class="review-row">
                <div>
                    <span onclick="location.href='review-detail?pk=${r.no}'">${r.title}</span>
                </div>
                <div>${r.date}</div>
            </div>
        </c:forEach>
    </div>
</div>

<div>
    <a href="review">[begin]</a>
    <c:forEach var="i" begin="1" end="${totalPage}">
        <a href="review?p=${i}"> ${[i]} </a>
    </c:forEach>
    <a href="review?p=${totalPage}">[end]</a>

</div>

<div>
    <input id="search-input">
    <button id="search-btn">search</button>
    <hr>
    <span id="result"></span>
</div>

</body>
</html>
