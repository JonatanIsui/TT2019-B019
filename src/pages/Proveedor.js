import React from 'react';
import { withRouter } from "react-router";
import Boton from '../components/Boton'
import Material from '../components/Material'
class Proveedor extends React.Component{
render(){
    const id = JSON.parse(atob(this.props.match.params.id))
    return(
        <div className = ''>
            <h1 className = ''>Bienvenido {id.nombreEncargado}</h1>
            <div>
                <Material
                    id = {id.id}
                />
                <Boton
                    text = 'Cerrar sesion'
                    url = '/'
                />
            </div>
            <div id = 'div'>
            </div>  
            <div className = ''>
                <div className = ''>
                    <form className = '' onSubmit={this.handleSubmit}>
                        <h1 className = ''>Subir catalogo</h1>
                        <input type="file" className = '' onChange={(e)=>this.handleFile(e)} />
                        <input type="submit" className = '' value = 'Cargar'/>
                    </form>
                </div>
            </div>  
        </div>
    )}
}
export default withRouter(Proveedor)