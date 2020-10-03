import React from 'react';
import { Link } from 'react-router-dom';
import {UsuarioService} from '../Servicio/UsuarioService'
class RegistroProveedor extends React.Component{
    constructor(){
        super()
        this.state={
            email:"",
            password:"",
            tipo:"proveedor",
            proveedor:{empresa:"",
            nombreEncargado:"",
            apellidoEncargado:"",
            telefono:"",
            direccion:""},
            error:null,
            sendig:false
        }
        this.usuarioService=new UsuarioService();
    }
    handleChange(e){
        if(e.target.name==="email" || e.target.name==="password"){
            this.setState({
                [e.target.name]:e.target.value
            })
        }else if(e.target.name==="empresa"){
            this.state.proveedor.empresa=e.target.value
        }else if(e.target.name==="apellidoEncargado"){
            this.state.proveedor.apellidoEncargado=e.target.value
        }else if(e.target.name==="telefone"){
            this.state.proveedor.telefono=e.target.value
        }else if(e.target.name==="direccion"){
            this.state.proveedor.direccion=e.target.value
        }else if(e.target.name==="nombreEncargado"){
            this.state.proveedor.nombreEncargado=e.target.value
        }
    }
    async handleSubmit(e){
        e.preventDefault();
        this.usua=this.state;
        console.log(this.usua)
        this.usuarioService.usuarioNuevo(this.usua)
        //window.location="/";
    }
    render(){
        return(<div>
            <h3>REGISTRO PROVEEDOR</h3>
            <form onSubmit={this.handleSubmit.bind(this)}>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Nombre de la empresa" type="text" id="empresa" name="empresa"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Nombre del representante" type="text" id="nombreEncargado" name="nombreEncargado"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Apellido del representante" type="text" id="apellidoEncargado" name="apellidoEncargado"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Numero telefonico" type="text" id="telefono" name="telefono"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Direccion" type="text" id="direccion" name="direccion"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Correo electrónico" type="email" id="email" name="email"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Contraseña" type="password" id="password" name="password"/>
                </div>
                <div>
                    <input value="Registrarse" type="submit"/>
                </div>
            </form>
            <button><Link to="/">Cancelar</Link></button>
            <p>Tendra que esperar a que el administrador apruebe su ingreso al sistema</p>
    </div>)}
}
export default RegistroProveedor