import React from 'react';
import { Link } from 'react-router-dom';
class InterfaceAdministrador extends React.Component{
render(){
    return(<div>
         <Link to="/">Salir</Link>
         <button>Ver arquitectos</button>
         <button>Ver proveedores</button>
         <button>Solicitudes de proveedores</button>
        </div>
    )}
}
export default InterfaceAdministrador