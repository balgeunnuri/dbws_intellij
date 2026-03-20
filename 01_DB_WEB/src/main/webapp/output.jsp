
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1" width="300">
  <tr>
    <td>name</td>
    <td>age</td>
  </tr>

  <c:forEach items="${humans}" var="h">
    <tr>
      <td>${h.name}</td>
      <td>${h.age}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
