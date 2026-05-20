import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Compo03 from './01_component/Compo03.jsx'
import Compo04_copy from './01_component/Compo04_copy.jsx'
import Props01 from './02_props/Props01.jsx'
import Props02_parent from './02_props/Props02_parent.jsx'
import Props02_child from './02_props/Props02_child.jsx'
import Props03_Wrap from './02_props/Props03_Wrap.jsx'
import Props03_Hello from './02_props/Props03_Hello.jsx'
import Props04_Notice from './02_props/Props04_Notice.jsx'
import Props04_Post from './02_props/Props04_Post.jsx'
import App2 from './02_props/App2.jsx'
import App3 from './03_hooks/App3.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/* <Compo03 /> */}
    {/* <Compo04_copy /> */}
    {/* <Props01 name11='mz' color='blue' /> */}
    {/* <Props01 /> */}
    {/* <Props01 color="red" /> */}
    {/* <Props02_parent>
      <Props02_child color="tomato"/>
      <Props02_child />
    </Props02_parent>

    <Props03_Wrap wrap="wrapData">
      <Props03_Hello color="pink"/>
      <Props03_Hello color="purple" name="mz" isTrue={true}/>
    </Props03_Wrap> */}

    {/* <Props04_Notice> */}
      {/* <Props04_Post isTrue={true}/> */}
      {/* <Props04_Post folding/>
      <Props04_Post />
    </Props04_Notice> */}

    {/* <App2 /> */}
    <App3 />
  </StrictMode>,
)
