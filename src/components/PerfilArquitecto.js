import React from 'react'
import ArquitectoService from '../service/ArquitectoService'
class PerfilArquitecto extends React.Component{
    ArquitectoService = new ArquitectoService()
    handleBaja=(usuario)=>async e=>{
        e.preventDefault()
        let aux={}
        let confirmar = prompt("Por favor ingrese su contraseña para dar de baja el perfil");
        if(confirmar===usuario.password){
            aux = await this.ArquitectoService.eliminarPerfil(usuario)
            if(Object.keys(aux).length>0){
                alert(aux)
                localStorage.removeItem('arquitecto')
                window.location ='/'
            }else{
                alert("En estos momentos no podemos eliminar tu perfil, intentelo mas tarde")
            }
        }else{
            alert("La constraseña no es correcta")
        }
    }

    render(){
        const usuario=this.props.usuario
        return(<div>
            <ul className="navbar-nav">
                <li className="nav-item">
                    Nombre de usuario: {usuario.arquitecto.nombre} {usuario.arquitecto.apellido}
                </li>
                <li className="nav-item">
                    Correo: {usuario.correo}
                </li>
                {usuario.arquitecto.telefono!=="" &&
                    <li className="nav-item">
                        Telefono: {usuario.arquitecto.telefono}
                    </li>
                }
                {usuario.arquitecto.direccion!=="" &&
                    <li className="nav-item">
                        Direccion: {usuario.arquitecto.direccion}
                    </li>
                }
                <li className="nav-item">
                <button className = 'btn btn-light' onClick={this.handleBaja(usuario)}>Baja de cuenta</button>
                </li>
            </ul>
        </div>)
    }
}
export default PerfilArquitecto