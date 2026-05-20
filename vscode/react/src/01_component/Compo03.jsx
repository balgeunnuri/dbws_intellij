// jsx (js xml)
// js + html

// const Compo03 = () => {
//     React.createElement("h1", null, "test3");
// };

// jsx 규칙

// 1. {js code}
// 2. 닫기 (self-closing)
// 3. 감싸기 (more than 2)   fragment
// 4. style -> 객체로
// 5. class / className

// 1), 3)
// 여러줄일때 {} + return

let a = 1;
// const Compo03 = () => {
//     let b = 2;
//     let styleObj = {
//         color: "red",
//         border: "5px solid purple",
//         fontSize: "1.5rem",
//     };

//     return (
//         <div style={styleObj}>
//             <h1 style={{ color: "blue" }}>{a} compo03 test33333</h1>
//             <h1>{b} compo03 test33333</h1>
//             <hr />
//             <input type="text" className="compo-input" />

//             <input type="checkbox" id="chk-input" />
//             <label htmlFor="chk-input">check</label>
//         </div>
//     );
// };

function Compo03() {
    let c = [
        { name: "mz", age: 10 },
        { name: "mz2", age: 20 },
    ];

    return (
        <>
            <h1>Test {c[0].name}</h1>
            <h1>Test {c[0].age}</h1>
            {c.map((obj, index) => (
                <h1>
                    {index}_ {obj.name}
                </h1>
            ))}
            <hr />
            {c.map((obj, index) => index + "_" + obj.name).join(", ")}
        </>
    );
}

export default Compo03;
