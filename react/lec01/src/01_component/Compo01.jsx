// 컴포넌트 => 하나의 레고조각
// 자바 클래스 느낌
// 하나 잘 해 놓으면 계속 사용

import { Component } from "react";

// * 항상 대문자로 시작 : 소문자는 dom 태그로 인식됨

// 1. 클래스형 컴포넌트

class Compo01 extends Component {
    render() {
        return <h1>component test</h1>;
    }
}

export default Compo01;