<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: soldesk
  Date: 2026-04-23
  Time: 오전 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>result.jsp</h1>

<!-- 상품 등록 폼 -->
<div class="reg-form">
    <h3>상품 등록</h3>
    <form action="add-coffee">
        품명 : <input type="text" name="product_name" required> <br>
        사진 : <input type="file" name="product_image" required> <br>
        가격 : <input type="number" name="product_price" required> <br>
        유통기한 : <input type="date" name="product_expiry" required> <br>
        <button type="submit">등록</button>
    </form>
</div>

<c:forEach items="${coffees}" var="coffee">
    <p>${coffee.c_name}</p>
</c:forEach>

<!-- 상품 목록 테이블 -->
<table>
    <tr>
        <th>no.</th>
        <th>사진</th>
        <th>커피명</th>
        <th>가격</th>
        <th>유통기한</th>
    </tr>
    <c:forEach items="${coffees}" var="c">
        <tr>
            <td>${c.c_no }</td>
            <td><img src="${c.c_img }" alt="커피 이미지"></td>
            <td>${c.c_name }</td>
            <td><fmt:formatNumber value="${c.c_price }" type="currency" /></td>
            <td><fmt:formatDate value="${c.c_exp }" /></td>
        </tr>
    </c:forEach>
</table>

<!-- 검색 폼들 -->
<div class="row">
    <div>
        <h3>품명으로 검색(포함)</h3>
        <form action="search-coffee">
            품명 : <input type="text" name="product_search_name">
            <button type="submit">검색</button>
        </form>
    </div>
    <div>
        <h3>가격으로 검색</h3>
        <form action="search-coffee">
            <input type="number" name="price_search" placeholder="가격 이하">
            <button type="submit">검색</button>
        </form>
    </div>
</div>
<div class="row">
    <div>
        <h3>상품번호로 지우기</h3>
        <form action="del-coffee">
            <input type="number" name="no" placeholder="상품번호">
            <button type="submit">삭제</button>
        </form>
    </div>
    <div>
        <h3>가격대 설정 검색</h3>
        <form action="search-coffee">
            <input type="number" name="price_min" placeholder="최소 가격">
            ~ <input type="number" name="price_max" placeholder="최대 가격">
            <button type="submit">검색</button>
        </form>
    </div>
</div>
<div class="row">
    <div>
        <h3>가격 인상</h3>
        <form action="update-coffee">
            <input type="number" name="price1" placeholder="가격 이하"> 원
            이하인거 <input type="number" name="price2" placeholder="가격 인상">
            원 인상
            <button type="submit">수정</button>
        </form>
    </div>
</div>

</body>
</html>
