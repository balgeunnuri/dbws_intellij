const Props04_Post = (props) => {

    // if(!props.isTrue) return null;
    if(!props.folding) return null;

    return <div>
        <div>공지 제목 {props.isTrue}</div>
        <div>내용은 ~~</div>
        </div>;
};

export default Props04_Post;