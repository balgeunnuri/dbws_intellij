<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <div class="review-wrap">
    <div>
      <div>
        <div class="review-reg-col">Title</div>
        <div class="review-reg-col2">
          <input name="title" value="${review.title}" disabled>

        </div>
      </div>
      <div>
        <div class="review-reg-col">Text</div>
        <div class="review-reg-col2">
          <textarea name="txt" maxlength="200" disabled>${review.txt}</textarea>
          <br> <span id="cntSpan">0</span> / 200
        </div>
      </div>
      <div>
        <div>posted at ${review.date}</div>
      </div>

      <div>
        <button class="review-reg-btn" onclick="location.href='review-update?pk=${param.pk}'">update</button>
<%--        ${param.pk} == ${review.no}--%>
<%--        <button class="review-reg-btn" onclick="location.href='review-del?no=${review.no}'">delete</button>--%>
        <button class="review-reg-btn" onclick="deleteReview('${param.pk}')">delete</button>
        <button class="review-reg-btn" onclick="history.back()">list</button>
      </div>
    </div>
  </div>
<script type="text/javascript">
  const textarea = document.querySelector("textarea[name='txt']");
  const cntSpan = document.querySelector("#cntSpan");
    cntSpan.innerText = textarea.value.length;

    function deleteReview(no) {
      let ok = confirm("really?")
      if (ok) {
        location.href = 'review-del?no=' + no;
      }
    }
</script>

</body>
</html>
