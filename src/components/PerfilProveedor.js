import React from 'react'
import ProveedorService from '../service/ProveedorService'
class PerfilProveedor extends React.Component{
    ProveedorService = new ProveedorService()
    handleBaja=(usuario)=>async e=>{
        e.preventDefault()
        let aux={}
        let confirmar = prompt("Por favor ingrese su contraseña para dar de baja el perfil");
        console.log(typeof(aux))
        if(confirmar===usuario.password){
            aux = await this.ProveedorService.eliminarPerfil(usuario)
            if(Object.keys(aux).length>0){
                alert(aux)
                localStorage.removeItem('proveedor')
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
        console.log(usuario)
        return(<div>
            <ul className="navbar-nav">
                <li className="nav-item">
                    Nombre de la empresa: {usuario.proveedor.nombreEmpresa}
                </li>
                <li className="nav-item">
                    Nombre del encargado: {usuario.proveedor.nombreEncargado} {usuario.proveedor.apellidoEncargado}
                </li>
                <li className="nav-item">
                    Correo: {usuario.correo}
                </li>
                <li className="nav-item">
                    Telefono: {usuario.proveedor.telefono}
                </li>
                <li className="nav-item">
                    Direccion: {usuario.proveedor.direccion}
                </li>
                <li className="nav-item">
                <button className = 'btn btn-light' onClick={this.handleBaja(usuario)}>Baja de cuenta</button>
                </li>
            </ul>
        </div>)
    }
}
export default PerfilProveedor