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
<h1>menu page</h1>

<div>
    <c:forEach items="${menus}" var="menu">
        <h1>메뉴명 : ${menu.m_name} / 가격 : ${menu.m_price}
        <button onclick="deleteMenu(${menu.m_no})">delete</button>
        <button onclick="updateMenu(${menu.m_no})">update(js)</button>
            <button onclick="location.href='/menu/update?no=${menu.m_no}'">update(jsp)</button>
            <button onclick="location.href='/menu/detail?no=${menu.m_no}'">detail page</button>
            <button onclick="location.href='/menu/${menu.m_no}'">detail page</button>
            <button onclick="location.href='/menu/json?m_no=${menu.m_no}'">get JSON</button>
            <button onclick="location.href='/menu/json/all'">get JSON</button>
        </h1>

    </c:forEach>
</div>

</body>

<script>

    function updateMenu(no) {
        const price = prompt("가격을 입력해주세요");
            location.href = "menu/edit?m_no=" + no + "&m_price=" + price; // 파라미터명 db와 같게
    }

    function deleteMenu(no) {
     let ok = confirm("정말로 삭제하시겠습니까?")
     if (ok) {
      location.href = "menu/del?no=" + no;
     }
    }
</script>
</html>