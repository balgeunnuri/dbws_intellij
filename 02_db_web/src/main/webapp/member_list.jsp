<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/member_list.css">
  <script !src="">
    function deleteMember(nooo){
      let ok = confirm("really?");
      if(ok){
        location.href='del?num='+nooo;
      }
    }
    function updateMemberName(nnn, naaame) {
      let name = prompt('edit?', naaame)
      // alert(name);
      if (name != "" && name != null){
      location.href='update?num='+nnn+'&name='+name;

      }
    }


  </script>
</head>
<body>



<div class="members-container">
  <c:forEach var="m" items="${members}">
    <div class="member-item">
      <div class="member-name" ><span onclick="updateMemberName('${m.no}','${m.name}')">${m.name}</span></div>
      <div class="member-age">${m.age}세</div>
      <div><button onclick="deleteMember(${m.no})">del</button></div>
    </div>
  </c:forEach>
</div>




</body>
</html>
