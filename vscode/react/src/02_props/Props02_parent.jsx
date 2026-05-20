import Props02_child from "./Props02_child";

const Props02_parent = ({ children }) =>{
    return (
        <div>
            <h1>parent~~</h1>
            {children}
            {/* <Props02_child /> */}
        </div>
    );
};

export default Props02_parent;