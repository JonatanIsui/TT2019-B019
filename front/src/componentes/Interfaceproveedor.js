import React from 'react';
import { Link } from 'react-router-dom';
class Interfaceproveedor extends React.Component{
render(){
    return(<div>
         <Link to="/">Salir</Link>
         <form onSubmit={this.onFormSubmit}>
                <h1>Subir catalogo</h1>
                <input type="file" onChange={(e)=>this.handleFile(e)} />
                <button type="submit">Cargar</button>
            </form>
         <button><Link to="/Nuevomaterial">Nuevo material</Link></button>
         <button><Link to="/Eliminarmaterial">Eliminar matarial</Link></button>
        </div>
    )}
}
export default Interfaceproveedor