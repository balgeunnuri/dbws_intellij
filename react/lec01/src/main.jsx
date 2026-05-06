import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Compo01 from './01_component/Compo01.jsx'
import Compo02 from './01_component/Compo02.jsx'
import Compo03 from './01_component/Compo03.jsx'
import Compo04 from './01_component/Compo04.jsx'
import Compo04_copy from './01_component/Compo04_copy.jsx'
import Props01 from './02_props/Props01.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    {/* <App /> */}
    {/* <Compo01 /> */}
    {/* <Compo02 /> */}
    {/* <Compo03 /> */}
    {/* <Compo04 /> */}
    {/* <Compo04_copy /> */}
    <Props01 name11="benr" color="blue" />
    <Props01 />
    <Props01 color="red" />
  </StrictMode>,
);
