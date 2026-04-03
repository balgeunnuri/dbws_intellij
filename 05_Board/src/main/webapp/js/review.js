$(function () {
    // body가 다 로드되면 실행하는 곳.

    // searchReview();
    showAllReview();
});

function showAllReview() {

        $.ajax({
            url: 'review-search', // 어디로 요청?
            //data: {title : reviewTitle} // data : 파라미터,
        }).done(function (resData) { // 콜백. 완료되면 자동으로 부르는 함수
            console.log(resData);
            $("#result").empty(); // 비우고 새로 그리고. 이전 기록 안 남아있게 해주는 코드.
            // 비동기가 무조건 좋은 것 아님. 고려해서 사용. 예 mbti 설문조사
            showResult(resData);

        }).fail(function (xhr, status, error) {
            console.log(status);
            console.log(error);
        })
}

function searchReview() { // 어나미펑션

    $("#search-btn").click(() => { // 애로우펑션
        const reviewTitle = $("#search-input").val();
        console.log(reviewTitle);

        $.ajax({
            url: 'review-search' // 어디로 요청?
            //data: {title : reviewTitle} // data : 파라미터,
            // 생략버전. 파라미터가 그 자체가 됨.

        }).done(function (resData) { // 콜백. 완료되면 자동으로 부르는 함수
            console.log(resData);
            $("#result").empty(); // 비우고 새로 그리고. 이전 기록 안 남아있게 해주는 코드.
            // 비동기가 무조건 좋은 것 아님. 고려해서 사용. 예 mbti 설문조사
            showResult(resData);

        }).fail(function (xhr, status, error) {
            console.log(status);
            console.log(error);
        })
    })

}

function showResult(resData) {

    $.each(resData, function (i, r) { // i=인덱스번호,r=하나하나 돌렸을 때 걔의 이름?
     console.log(i);
     console.log(r);
     // 백틱문법 여기는 js라서 편하게 사용가능.?
     let content = `<div class="review-row">
                <div>
                    <span onclick="location.href='review-detail?pk=${r.no}'">${r.title}</span>
                </div>
                <div>${r.date} <button onclick="del('${r.no}', this)">del</button></div>
            </div>`;
     // 개발자 도구에서 no, date 변수 확인 가능.
        $(`#result`).append(content); // append? 콘솔에서만 뜨는 걸 결과화면에 붙인 것.?
    })


}
function del(no, btn) {
    console.log(no)
    $.ajax({
        url: 'review-del',    // 어디로 요청?
        data: {no} // no : 파라미터
    }).done(function (resData) {
        console.log(resData);
        if (resData == 1){
            // 1. 전체 로드
            // showAllReview()
            // 2. 선택된 것만 삭제
            // $(btn).parent().parent().remove();
            // 위 아래 같은 코드.
            $(btn).closest('.review-row').remove();
        }
    })
}