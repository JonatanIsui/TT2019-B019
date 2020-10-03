import React from 'react';
import {UsuarioService} from '../Servicio/UsuarioService'
import { Link } from 'react-router-dom';
class Nuevomaterial extends React.Component{
    constructor(){
        super();
        this.state={
            erro:null,
            sending:false
        }
    }
    handleChange(e){
        this.setState({
            [e.target.name]:e.target.value
        });
    }
    async handleSubmit(e){
       /* await this.setState({ sending: true,
            repuesta:this.usuarioService.getAll()
        });
        console.log(this.state)*/
    }
    
    render(){
        return(<div>
            <h3>Nuevo material</h3>
            <form onSubmit={this.handleSubmit.bind(this)}>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Nombre del material" name='nombre' id='nombre' type='text'/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Medida" name='medida' id='medida' type='text'/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Descripcion" name='Descripcion' id='Descripcion' type='text'/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Precio" name='precio' id='precio' type='text'/>
                </div>
                <div>
                    <input type='submit' value="Cargar"/>
                </div>
            </form>
            <div>
              <Link to="/interfaceproveedor">Cancelar</Link>
            </div>
        </div>
    )}
}
export default Nuevomaterial