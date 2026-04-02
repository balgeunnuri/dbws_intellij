<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/movie.css">
    <link rel="stylesheet" href="css/review.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="js/review.js"></script>
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