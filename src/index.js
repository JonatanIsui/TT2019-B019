import React from 'react'
import ReactDOM from 'react-dom'
import Login from './pages/Login'
import RegistroProveedor from './pages/RegistroProveedor'
import RegistroUsuario from './pages/RegistroUsuario'
import RecuperarPassword from './pages/RecuperarPassword'
import 'bootstrap/dist/css/bootstrap.css'
import { BrowserRouter, Route, Switch } from 'react-router-dom';


//const container = document.getElementById('root')
//Como es un componente se llama desde etiquetas
ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
          <Switch>
              <Route component = {Login} exact path = '/'/>
              <Route component = {RecuperarPassword} exact path = '/RecuperarContra' />
              <Route component = {RegistroUsuario} exact path = '/RegistroArquitecto' />
              <Route component = {RegistroProveedor} exact path = '/RegistroProveedor'/>
          </Switch> 
      </BrowserRouter>
  </React.StrictMode>,document.getElementById('root')
)