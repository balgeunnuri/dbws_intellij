<%--
  Created by IntelliJ IDEA.
  User: soldesk
  Date: 2026-03-27
  Time: 오후 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="review-add" method="post">

  <div class="review-wrap">
    <div>
      <div>
        <div class="review-reg-col">Title</div>
        <div class="review-reg-col2">
          <input name="title">

        </div>
      </div>
      <div>
        <div class="review-reg-col">Text</div>
        <div class="review-reg-col2">
          <textarea name="txt" maxlength="200"></textarea>
          <br> <span id="cntSpan">0</span> / 200
        </div>
      </div>
      <div>
        <button class="review-reg-btn">post</button>
      </div>
    </div>
  </div>
</form>
<script type="text/javascript">
  const textarea = document.querySelector("textarea[name='txt']");
  const cntSpan = document.querySelector("#cntSpan");
  textarea.addEventListener('input', ()=>{
    const len = textarea.value.length;
    cntSpan.innerText = len;
  });

</script>

</body>
</html>
