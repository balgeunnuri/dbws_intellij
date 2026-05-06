// 2. 함수형 컴포넌트 => 권장. 그냥 이거 쓰세요!
// 방법 여러가지

// 1)
// const Compo02 = function () {
//     return <h1>compo test22222222222</h1>
// };

// export default Compo02; // Compo02를 다른 데서 쓸 수 있게 하겠다.

// 2) 쌤이 쓰시는 방법
// const Compo02 = () => {
//     return <h1>compo test22222222222</h1>;
// };
// export default Compo02;

// 3)
// function Compo02() {
//     return <h1>compo test22222222222</h1>;
// }
// export default Compo02;

// 4)
export default function Compo02() {
    return <h1>compo test22222222222</h1>;
}