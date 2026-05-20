const Props03_Hello = ({ color = "gray", name = "no named", isTrue }) => {
    return (
        <div style={{ color }}>
            Hello~ {name} {isTrue}
            {/* {isTrue ? <b>true~~</b> : 0} */}
            {isTrue && <b>True~?</b>}
        </div>
    );
};

// 맞으면 && 다음 거 나옴.

// 단축평가 논리 계산법

console.log(true && "hello"); // hello
console.log(false && "hello"); // false
console.log("hello" && "bye"); //bye
console.log(null && "hello"); //null
console.log(undefined && "hello"); // undefined
console.log("" && "hello~"); // ''
console.log(0 && "hello~"); // 0
console.log(1 && "hi~~"); // hi~~

export default Props03_Hello;