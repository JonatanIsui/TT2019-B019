import React from 'react';
import { Link } from 'react-router-dom';
class InterfaceArquitecto extends React.Component{
render(){
    return(<div>
         <Link to="/">Salir</Link>
         <button><Link to="/Nuevaconsulta">Nueva consulta</Link></button>
         <button><Link to="/Consultaguardada">Consulta guardadas</Link></button>
         <button><Link to="/Glosario">Glosario</Link></button>
        </div>
    )}
}
export default InterfaceArquitecto