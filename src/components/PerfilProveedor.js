import React from 'react'
import ProveedorService from '../service/ProveedorService'
class PerfilProveedor extends React.Component{
    ProveedorService = new ProveedorService()
    handleBaja=(usuario)=>async e=>{
        e.preventDefault()
        let aux={}
        let confirmar = prompt("Por favor ingrese su contraseña para dar de baja el perfil");
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
        return(<div>


                <div className="container-fluid">
                <div className="row justify-content-center">
                        <div className = 'col-lg-10 text-center'><h3>Nombre de la empresa: {usuario.proveedor.nombreEmpresa}</h3>
                        </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className = 'col-lg-10 text-center'><h3>Nombre del encargado: {usuario.proveedor.nombreEncargado} </h3>
                        </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className = 'col-lg-10 text-center'><h3>Correo: {usuario.correo}</h3></div>
                    </div>
                    {usuario.proveedor.telefono!=="" &&
                        <div className="row justify-content-center">
                            <div className = 'col-lg-10 text-center'><h3>Tel&eacute;fono: {usuario.proveedor.telefono}</h3></div>
                        </div>
                    }
                    {usuario.proveedor.direccion!=="" &&
                        <div className="row justify-content-center">
                            <div className = 'col-lg-10 text-center'><h3>Direcci&oacute;n: {usuario.proveedor.direccion}</h3></div>
                        </div>
                    }
                    <div className="row justify-content-center">
                        <button className = 'btn btn-light' onClick={this.handleBaja(usuario)}>Baja de cuenta</button>
                    </div>
                </div>

        </div>)
    }
}
export default PerfilProveedor