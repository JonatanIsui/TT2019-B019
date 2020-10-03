import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './componentes/Login'
import Nuevacontra from './componentes/Nuevacontra'
import RegistroArquitecto from './componentes/RegistroArquitecto'
import RegistroProveedor from './componentes/RegistroProveedor'
import InterfaceArquitecto from './componentes/InterfaceArquitecto'
import NuevaConsulta from './componentes/NuevaConsulta'
import ConsultaGuardada from './componentes/NuevaConsulta'
import Glosario from './componentes/Glosario'
import Interfaceproveedor from './componentes/Interfaceproveedor'
import Nuevomaterial from './componentes/Nuevomaterial'
import InterfaceAdministrador from './componentes/InterfaceAdministrador'
ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
        <Switch>
        <Route exact path="/">
          <Login/>
        </Route>
        <Route path="/RecuperarContra">
          <Nuevacontra/>
        </Route>
        <Route path="/NuevoArquitecto">
          <RegistroArquitecto/>
        </Route>
        <Route path="/NuevoProveedor"> 
          <RegistroProveedor/>
        </Route>
        <Route path="/interfaceArquitecto">
          <InterfaceArquitecto/>
        </Route>
        <Route path="/Nuevaconsulta">
          <NuevaConsulta/>
        </Route>
        <Route path="/Consultaguardada">
          <ConsultaGuardada/>
        </Route>
        <Route path="/Glosario">
          <Glosario/>
        </Route>
        <Route path="/interfaceproveedor">
          <Interfaceproveedor/>
        </Route>
        <Route path="/Nuevomaterial">
          <Nuevomaterial/>
        </Route>
        <Route path="/interfaceadministrador">
          <InterfaceAdministrador/>
        </Route>
        </Switch>
      </BrowserRouter>
  </React.StrictMode>,document.getElementById('root')
);
