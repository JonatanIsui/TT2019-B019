import React from 'react';
import { Link } from 'react-router-dom';
import {UsuarioService} from '../Servicio/UsuarioService'
class RegistroArquitecto extends React.Component{
    constructor(){
        super()
        this.state={
            tipo:"arquitecto",
            error:null,
            arquitecto:{
                nombre:"",
                apellido:""
            },
            sendig:false
        }
        this.usuarioService=new UsuarioService();
    }
    arquitecto={}
    handleChange(e){
        if(e.target.name==="email" || e.target.name==="password"){
            this.setState({
                [e.target.name]:e.target.value
            })
        }else if(e.target.name==="nombre"){
            this.state.arquitecto.nombre=e.target.value
        }else if(e.target.name==="apellido"){
            this.state.arquitecto.apellido=e.target.value
        }
    }
    async handleSubmit(e){
        e.preventDefault();
        this.usua=this.state;
        this.usuarioService.usuarioNuevo(this.usua)
        window.location="/";
    }
    render(){
        return(<div>
            <h3>REGISTRO ARQUITECTO</h3>
            <form onSubmit={this.handleSubmit.bind(this)}>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Nombre" type="text" id="nombre" name="nombre"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Apellido" type="text" id="apellido" name="apellido"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Correo electrónico" type="email" id="email" name="email"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Contraseña" type="password" id="password" name="password"/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value="Registrarse" type="submit"/>
                </div>
            </form>
            <button><Link to="/">Cancelar</Link></button>
            <p>Los datos ingresados al sistema estan protegidos por la ley de la proteccion de los datos</p>
    </div>)}
}
export default RegistroArquitecto