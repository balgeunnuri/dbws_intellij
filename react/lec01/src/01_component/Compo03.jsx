// jsx (js xml)
// js + html


// jsx 규칙

// 1. {js code}
// 2. 닫기 (self-closing)
// 3. 감싸기 (more than 2)   fragment : <> 빈 껍데기 태그
// 4. style -> 객체로
// 5. class / className


// 1), 3) 
// 여러줄일때 {} + return
// let a = 1;
// const Compo03 = () => {
//     let b = 2;
//     let styleObj = {
//         color: "red",
//         border: "5px solid purple",
//         fontSize: "1.5rem",
//     }

//     return (
//     <div style={styleObj}>
//         <h1 style={{color: "blue"}}>{a} compo03 test3333333333</h1>
//         <h1>{b} compo03 test3333333333</h1>
//         <hr />
//         <input type="text" className="compo-input" />

//         <input type="checkbox" id="chk-input" />

//         <label htmlFor="chk-input">check</label>
//     </div>
//     );
// };

// export default Compo03;


function Compo03() {
    let c = [
        { name : "mz", age: 10 },
        { name : "mz2", age: 20 },
    ];
    return (
        <>
            <h1>Test {c[0].name}</h1>
            <h1>Test {c[0].age}</h1>
            {c.map((obj, index) => (<h1>{index}_ {obj.name}</h1>))}
            <hr />
            {c.map((obj, index) => index + "_" + obj.name).join(", ")} 
            {/* 돔 안에 있고 없고 차이 */}
        </>
    )
}
export default Compo03;