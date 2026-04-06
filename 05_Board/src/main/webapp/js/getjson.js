$(function () {

    // java의 main느낌

    getJSON();

})

    function getJSON() {

        $("#json-btn").click(function () {


        // $("#json-btn").click(() => {
        //
        // })
        // document.querySelector("#json-btn").addEventListener("click", function () {
        //
        // })
        // document.querySelector("#json-btn").addEventListener("click", () => {
        //
        // });
        //위 4개랑 onclick이랑 같은 거



        $.ajax({
          url: 'get-json' // 젤 중요한 게 url
          // type: 기본이 겟요청
          // data: select 정보 다 필요한 거니까 where 필요없음. 당연히 파라미터도 없음
          // success: 이렇게 써도 되고
        }).done(function (resData) { // 이렇게 써도 됨. resData: 맘대로 지은 이름
          console.log(resData);
            let content =''; //콘솔에 영어 뜨는 거 싫으면 엠티 스트링으로 초기화
          // 반복문
          $.each(resData, function (index, person) { //index, person 변수명 마음대로
            content += `<div>${person.name} / ${person.age}</div>` // 백틱 사용 가능 조건?
          })
            $(".result").append(content); // 위 디브가 어디 들어가고 싶다? result. 그래서 append 해준 것


            // 반복문
            // $.each(resData, function (index, person) {
            //     let content = `<div>${person.name} / ${person.age}</div>`
            //     $(".result").append(content);
            // }) 위에랑 같은 거. 다른 버전. 원래 이거로 썼다가 위로 변경!

        })
        })

    }