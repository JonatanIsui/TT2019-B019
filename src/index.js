import React from 'react'
import ReactDOM from 'react-dom'
import Login from './pages/Login'
import RegistroProveedor from './pages/RegistroProveedor'
import RegistroUsuario from './pages/RegistroUsuario'
import RecuperarPassword from './pages/RecuperarPassword'
import CambioPassword from './pages/CambioPassword'
import Arquitecto from './pages/Arquitecto'
import Proveedor from './pages/Proveedor'
import Administrador from './pages/Administrador'
import 'bootstrap/dist/css/bootstrap.css'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';


//const container = document.getElementById('root')
//Como es un componente se llama desde etiquetas
ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
        <div className = 'bg-dark text-white'>
            <Switch>
                <Route component = {Login} exact path = '/'/>
                <Route component = {RecuperarPassword} exact path = '/RecuperarContra' />
                <Route component = {RegistroUsuario} exact path = '/RegistroArquitecto'  />
                <Route component = {RegistroProveedor} exact path = '/RegistroProveedor' />
                <Route component = {CambioPassword} exact path = '/CambioPassword/:id' />
                <Route component = {Arquitecto} exact path = '/Arquitecto/:id' />
                <Route component = {Proveedor} exact path = '/Proveedor/:id' />
                <Route component = {Administrador} exact path = '/Administrador/:id'/>
            </Switch> 
        </div>
      </BrowserRouter>
  </React.StrictMode>,document.getElementById('root')
)