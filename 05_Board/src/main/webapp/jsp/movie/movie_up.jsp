
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>- Movie Update -</h1>

<form action="update" method="post">
<div style="display: flex; justify-content: center;">
    <div class="movie-detail">
        <div>
            <div class="col-1">No.</div>
            <div class="col-2"><input type="text" name="no" value="${movie.no}" readonly> </div>
        </div>
        <div>
            <div><img src="/images/${movie.img}"></div>
        </div>
        <div>
            <div class="col-1">Title.</div>
            <div class="col-2"><input type="text" name="title" value="${movie.title}"></div>
<%--            <input type="text" value="${movie.no}" name="no" hidden=""></div>--%>
        </div>
        <div>
            <div class="col-1">Actor.</div>
            <div class="col-2"><input type="text" name="actor" value="${movie.actor}"></div>
        </div>

        <div>
            <div class="col-1">Story.</div>
            <div class="col-2"><textarea name="story" id="" cols="30" rows="10">${movie.story}</textarea></div>
        </div>
        <div>
<%--            <div><button class="movie-btn" name="no" value="${movie.no}">done</button></div>--%>
            <div><button class="movie-btn">done</button></div>
        </div>


    </div>
</div>
</form>
</body>
</html>
