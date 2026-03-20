<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<h2>Movie Page ~~~</h2>

<div style="display: flex; justify-content: center;">
    <form action="movie" method="post" enctype="multipart/form-data">

        <div class="movie-reg">
            <div>
                <div>Title</div>
                <div>
                    <input name="title">
                </div>
            </div>
            <div>
                <div>Actor</div>
                <div>
                    <input name="actor">
                </div>
            </div>
            <div>
                <div>File</div>
                <div>
                    <input type="file" name="file">
                </div>
            </div>
            <div>
                <div>Story</div>
                <div>
                    <textarea rows="5" cols="40" name="story"></textarea>
                </div>
            </div>
            <div>
                <div>
                    <button class="movie-btn">Add</button>
                </div>
            </div>
        </div>
    </form>
</div>
<hr>



<div style="width: 100%; display: flex; justify-content: center;">
    <div class="movie-container">
        <c:forEach var="movie" items="${movies}">
            <div class="movie-wrap">
                <div class="movie-img" onclick="location.href='detail-movie?no=${movie.no}'">
                    <img alt="" src="movieFile/${movie.img}">
                </div>
                <div class="movie-title">${movie.title}</div>
                <div class="movie-actor">${movie.actor}</div>
                <div>
                    <button class="movie-btn" onclick="delMovie(${movie.no})">delete</button>
                    <button class="movie-btn">수정(jsp)</button>
                    <button class="movie-btn">수정(img]jsp)</button>
                    <button class="movie-btn">수정(js)</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    function delMovie(no) {
        let ok = confirm("really")
        if (ok) {
            location.href = 'movie?no=' + no + '&type=d';
        }
    }
</script>
</body>
</html>
