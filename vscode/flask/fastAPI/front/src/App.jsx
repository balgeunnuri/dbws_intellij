// import { useState } from 'react'

// function App() {
//   const [msg, setMsg] = useState("not yet");

//   useState(()=>{
//       fetch("http://127.0.0.1:8000")
//       .then((res)=> res.json())
//       .then((data)=> console.log(data));

//   }, []);

//   return <div>fast api  {msg} </div>;
// }

// export default App

import { useState, useEffect } from 'react'
import axios from 'axios'

function App() {
  const [msg, setMsg] = useState("not yet");

  useEffect(()=>{
    axios.get("http://127.0.0.1:8000/users/")
      .then((res)=> {
        console.log(res.data[0].name);
      setMsg(res.data[1].name);
  });
  }, []);

  return <div>fast api {msg}</div>
}

export default App