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
                    <img alt="" src="/images/${movie.img}">
                </div>
                <div class="movie-title">${movie.title}</div>
                <div class="movie-actor">${movie.actor}</div>
                <div>
                    <button class="movie-btn" onclick="delMovie(${movie.no})">delete</button>
                    <button class="movie-btn" onclick="location.href='update?no=${movie.no}'">수정(jsp)</button>
                    <button class="movie-btn" onclick="location.href='edit?no=${movie.no}'">수정(img]jsp)</button>
                    <button class="movie-btn">수정(js)</button>
                </div>
            </div>
        </c:forEach>
    </div>


    <div>

        <c:choose>
            <c:when test="${currentPage != 1}">
                <button class="movie-btn pn" onclick="location.href='movie?p=${currentPage - 1}'">prev</button>
            </c:when>
            <c:otherwise>
                <button class="movie-btn pn shake">prev</button>
            </c:otherwise>
        </c:choose>


        <c:choose>
            <c:when test="${currentPage != totalPage}">
                <button class="movie-btn pn" onclick="location.href='movie?p=${currentPage + 1}'">next</button>
            </c:when>
            <c:otherwise>
                <button class="movie-btn pn shake" >next</button>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<hr>

<div>
    <a href="movie">[begin]</a>
    <c:forEach begin="1" end="${totalPage}" var="i">
        <a href="movie?p=${i}"> [${i}] </a>
    </c:forEach>
    <a href="movie?p=${totalPage}">[end]</a>
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
