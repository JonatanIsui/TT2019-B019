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

            <div className="container-fluid">
                <div className="row justify-content-center">
                    <div className = 'col-lg-10 text-center'><h3>Nombre de usuario: {usuario.arquitecto.nombre} {usuario.arquitecto.apellido}</h3>
                    </div>
                </div>
                <div className="row justify-content-center">
                <div className = 'col-lg-10 text-center'><h3>Correo: {usuario.correo}</h3></div>
                </div>
                {usuario.arquitecto.telefono!=="" &&
                    <div className="row justify-content-center">
                        <div className = 'col-lg-10 text-center'><h3>Tel&eacute;fono: {usuario.arquitecto.telefono}</h3></div>
                    </div>
                }
                {usuario.arquitecto.direccion!=="" &&
                    <div className="row justify-content-center">
                        <div className = 'col-lg-10 text-center'><h3>Direcci&oacute;n: {usuario.arquitecto.direccion}</h3></div>
                    </div>
                }
                <div className="row justify-content-center">
                <button className = 'btn btn-light' onClick={this.handleBaja(usuario)}>Baja de cuenta</button>
                </div>
            </div>


        </div>)
    }
}
export default PerfilArquitecto