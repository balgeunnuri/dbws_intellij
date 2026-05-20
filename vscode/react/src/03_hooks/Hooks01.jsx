import { useEffect, useState } from "react";

const Hooks01 = (props) => {
    const [mode, setMode] = useState(props.val)
    
    const changeMode = () => {
        console.log("btn clicked..!!");
        console.log(mode);

        // props.val = "night";
        // setMode((prevMode)=>{
        //     const newMode = prevMode == "day" ? "night" : "day";
        //     console.log(newMode);

        //     if(newMode == "day"){
        //         document.body.style.backgroundColor = "white";
        //         document.body.style.color = "black";
        //     } else {
        //         document.body.style.backgroundColor = "black";
        //         document.body.style.color = "white";
        //     };

        //     return newMode;

        //  setMode((prevMode)=>{
        //     const newMode = prevMode == "day" ? "night" : "day";
        //     console.log(newMode);
        //     document.body.style.backgroundColor = newMode == "day" ? "white" : "black";
        //     document.body.style.color = newMode == "day" ? "black" : "white";
        //     return newMode;
    // });


    // set은 값 변경 용도로만 사용하면 코드 깔끔해짐. 나머지는 useEffect에 담아서 처리
    // set 하는 값 자체를 잘 정리해서 넣고, 부수적인 효과를 줄 수 있지만, ( css )
    // 값 변경에 따른 추가 작업을 따로 할 수 있음.
        setMode((currentMode) => (currentMode == "day"? "night" : "day"));
    };
    useEffect(()=>{
        console.log("effect~~");

        document.body.style.backgroundColor = mode == "day" ? "white" : "black";
        document.body.style.color = mode == "day" ? "black" : "white";
    },[mode]);

    return (
        <div>
            view mode : <button onClick={changeMode}>{mode}</button>
        </div>
    );
};

export default Hooks01;