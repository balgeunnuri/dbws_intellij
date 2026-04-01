<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/movie.css">
    <link rel="stylesheet" href="css/review.css">
</head>
<body>

<div class="login-area">
    <span style="color: red">${msg}</span>
    <jsp:include page="${loginPage}"></jsp:include>
</div>

<div class="container">
    <div class="title">
        <a href="hello-servlet"> Mz's place </a>
    </div>
    <div class="menu">
        <div>
            <a href="menu1">[Menu1]</a>
        </div>
        <div>
            <a href="movie">[Movie]</a>
        </div>
        <div>
            <a href="review">[Review]</a>
        </div>
        <div>
            <a href="jquery">[jQuery]</a>
        </div>
    </div>
    <div class="content">
        <jsp:include page="${content}"></jsp:include>
    </div>
</div>
</body>
</html>