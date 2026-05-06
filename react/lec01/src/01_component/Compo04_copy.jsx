import Compo02 from "./Compo02";

const Compo04_copy = () => {
    
    const obj = {
        column : ["품명", "가격", "지역"],
        rowData : [
            { name : "americano", price : 1000, loc : "종각" },
            { name : "cafe latte", price : 2000, loc : "종각" },
            { name : "vanilla latte", price : 3000, loc : "종각" },

        ],
    };
    
    return (
    <div>
        <div id="compo04-container">
            <div>
               {obj.column.map((col, i) => <div key={i}>{col}</div>)}
            </div>
            {obj.rowData.map((row, index)=> (
             <div style={{display: "flex", justifyContent: "space-around"}} key={index} data-key={index}>
                <div>{row.name}</div>
                <div>{row.price}</div>
                <div>{row.loc}</div>
            </div>
            ))}
        </div>
    </div>
    );
    
};

export default Compo04_copy;