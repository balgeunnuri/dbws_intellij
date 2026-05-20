import { useState } from "react";
import Props04_Notice from "./Props04_Notice";
import Props04_Post from "./Props04_Post";

const App2 = () => {
    const [foldable, setFoldable] = useState(true);
    const foldHanlder = () => {
        console.log(11);
        setFoldable((asd)=> !asd);
    };

    return (
        <>
            <button onClick={foldHanlder}>{foldable ? "접기" : "펼치기"}</button>
            <Props04_Notice>
                <Props04_Post folding={foldable}/>
            </Props04_Notice>
        </>
    );
};

export default App2