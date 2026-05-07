import { useEffect, useState } from 'react'


function App() {
  const [msg, setMsg] = useState("");

  useEffect(()=>{
    fetch("http://localhost:5000/api/hello")// flask 주소
    .then((res)=> res.json())
    .then((data)=> setMsg(data.message));
  }, []);

  return <div>{msg}</div>

}

export default App;
