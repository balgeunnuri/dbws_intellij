// const Props01 = (Props) => {
//     return <h1 style={{color: Props.color}}>name : {Props.name11}</h1>;
// };

// 비구조화 할당(destructuring assigment)
// 펼쳐서 받기
// const Props01 = ({name11, color}) => {
//     return <h1 style={{ color }}>name : {name11}</h1>
// }


const Props01 = ({name11 = "no named", color = "gray"}) => {
    return <h1 style={{ color }}>name : {name11}</h1>
}
export default Props01;