// const Props01 = (props) => {
//     return <h1 style={{ color: props.color }}>name : {props.name11}</h1>;
// };

// 비구조화 할당 (destructuring assigment)
// 펼쳐서 받기
// const Props01 = ({ name11, color }) => {
//     return <h1 style={{ color }}>name : {name11}</h1>;
// };

const Props01 = ({ name11 = "no named", color = "gray" }) => {
    return <h1 style={{ color }}>name : {name11}</h1>;
};

export default Props01;
