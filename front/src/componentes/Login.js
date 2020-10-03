import React from 'react';
import {UsuarioService} from '../Servicio/UsuarioService'
import { Link } from 'react-router-dom';
class Login extends React.Component{
    constructor(){
        super();
        this.state={
            erro:null,
            sending:false
        }
        this.usuarioService=new UsuarioService();
        this.usuario=[]
    }
    handleChange(e){
        this.setState({
            [e.target.name]:e.target.value
        });
    }
    async handleSubmit(e){
        e.preventDefault();
        this.setState({sending:true})
        try{
            this.usuario=this.usuarioService.autorizarLogin(this.state.email,this.state.password)
            if(this.usuario.tipo==='arquitecto'){
                window.location="/interfaceArquitecto";
            }else if (this.usuario.tipo==='proveedor') {
                window.location="/interfaceproveedor";
            } else if(this.usuario.tipo==='administrador'){
                window.location="/interfaceadministrador";
            }
        }catch(error){
            this.setState({error:error.message})
        }finally{
            this.setState({sending:false});
        }
       /* await this.setState({ sending: true,
            repuesta:this.usuarioService.getAll()
        });
        console.log(this.state)*/
    }
    
    render(){
        return(<div>
            <h3>Iniciar sesión</h3>
            <form onSubmit={this.handleSubmit.bind(this)}>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Correo electr&oacute;nico" name='email' id='email' type='email'/>
                </div>
                <div>
                    <input  onChange={this.handleChange.bind(this)} value={this.state.value} placeholder="Contraseña" name='password' id='password' type='password' />
                </div>
                <div>
                    <input type='submit' value="Iniciar sesión"/>
                </div>
            </form>
            <div>
              <Link to="/NuevoArquitecto">Registrase como arquitecto.</Link>
            </div>
            <div>
              <Link to="/NuevoProveedor">Registrase como proveedor.</Link>
            </div>
            <div>
              <Link to="/RecuperarContra">¿Olvidaste tu contraseña?.</Link>
            </div>
        </div>
    )}
}
export default Login