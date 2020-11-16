import React from 'react'
import ReactDOM from 'react-dom'
import Login from './pages/Login'
import RegistroProveedor from './pages/RegistroProveedor'
import RegistroUsuario from './pages/RegistroUsuario'
import RecuperarPassword from './pages/RecuperarPassword'
import CambioPassword from './pages/CambioPassword'
import Arquitecto from './pages/Arquitecto'
import 'bootstrap/dist/css/bootstrap.css'
import { BrowserRouter, Route, Switch } from 'react-router-dom';


//const container = document.getElementById('root')
//Como es un componente se llama desde etiquetas
ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
        <div className = ''>
            <div>
                <h1>Barra de navegacion</h1>
            </div>
            <Switch>
                <Route component = {Login} exact path = '/'/>
                <Route component = {RecuperarPassword} exact path = '/RecuperarContra' />
                <Route component = {RegistroUsuario} exact path = '/RegistroArquitecto' />
                <Route component = {RegistroProveedor} exact path = '/RegistroProveedor'/>
                <Route component = {CambioPassword} exact path = '/CambioPassword/:id'/>
            </Switch> 
        </div>
      </BrowserRouter>
  </React.StrictMode>,document.getElementById('root')
)