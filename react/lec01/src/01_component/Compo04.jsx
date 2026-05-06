import Compo02 from "./Compo02";

const Compo04 = () => {
    
    const obj = {
        column : ["품명", "가격"],
        rowData : [
            { name : "americano", price : 1000 },
            { name : "cafe latte", price : 2000 },
            { name : "vanilla latte", price : 3000 },

        ],
    };
    
    return (
    <div>
        {/* <Compo02 /> */}
        <div style={{width: "300px", border: "2px solid"}}>
            <div style={{display: "flex", justifyContent: "space-around"}}>
                <div>{obj.column[0]}</div>
                <div>{obj.column[1]}</div>
            </div>
            <div style={{display: "flex", justifyContent: "space-around"}}>
                <div>{obj.rowData[0].name}</div>
                <div>{obj.rowData[0].price}</div>
            </div>
             <div style={{display: "flex", justifyContent: "space-around"}}>
                <div>{obj.rowData[1].name}</div>
                <div>{obj.rowData[1].price}</div>
            </div>
             <div style={{display: "flex", justifyContent: "space-around"}}>
                <div>{obj.rowData[2].name}</div>
                <div>{obj.rowData[2].price}</div>
            </div>
        </div>
    </div>
    );
    
};

export default Compo04;