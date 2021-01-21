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
import Terminos from './pages/Terminos'
import 'bootstrap/dist/css/bootstrap.css'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import PreguntasFrecuentes from './pages/PreguntasFrecuentes'
import { BrowserRouter, Route, Switch } from 'react-router-dom';

const footerStyle = {
  backgroundColor: "#343A40",
  fontSize: "15px",
  color: "white",
  borderTop: "1px solid #E7E7E7",
  textAlign: "center",
  padding: "10px",
  position: "fixed",
  left: "0",
  bottom: "0",
  height: "120px",
  width: "100%"
};

const phantomStyle = {
  display: "block",
  padding: "10px",
  height: "130px",
  width: "100%"
};
function Footer({ children }) {
  return (
    <div>
      <div style={phantomStyle} />
      <div style={footerStyle}>{children}</div>
    </div>
  );
}

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
                <Route component = {PreguntasFrecuentes} exact path = '/preguntasfrecuentes'/>
                <Route component ={Terminos} exact path='/terminos'/>
            </Switch> 
            
            <Footer>
              <div className="row text-white justify-content-center">
                <div className ="col-6">
                  TT2019-B019
                </div>
                <div>
                  Para quejas y sugerencias, envíanos un correo a estimatucasa@gmail.com
                </div>
                <div className ="col-6">
                <a href="/preguntasfrecuentes"><button type="button" className='btn btn-light'>Preguntas frecuentes</button></a>
                </div>
                <div>
                <a href="/terminos"><button type="button" className='btn btn-light'>Términos y condiciones</button></a>
                </div>
              </div>
            </Footer>
        </div>
      </BrowserRouter>
  </React.StrictMode>,document.getElementById('root')
)