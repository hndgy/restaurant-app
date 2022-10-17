import React from 'react'
import { Route, Routes} from 'react-router-dom'
import Admin from './Admin'
import EditOrder from './EditOrder'
import Home from './Home'
import Kitchen from './Kitchen'
import NewOrder from './NewOrder'
import Salle from './Salle'

function Pages() {
  return (
    <Routes>
      <Route path='/' element={<Home/>}/>
      <Route path='/kitchen' element={<Kitchen/>}/>
      <Route path='/salle' element={<Salle/>}/>
      <Route path='/newOrder' element={<NewOrder/>}/>
      <Route path='/editOrder/:orderId' element={<EditOrder/>}/>
      <Route path='/admin' element={<Admin/>}/>
    </Routes>
  )
}

export default Pages