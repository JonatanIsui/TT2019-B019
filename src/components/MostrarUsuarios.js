import React, { Fragment } from 'react'
import AdmService from '../service/AdmService'
import ReactDOM from 'react-dom';

class MostrarUsuarios extends React.Component{
    AdmService = new AdmService()
    arquitectos = []
    proveedores = []
    solicitudes = []
    urlUsuarios = '/masInformacion/'
    urlSolicitud ='/masInformacionSolicitante/' 
    image = []

    //Este metodo se encarga de generar el DOM para mostrar los proveedores o los arquitectos
    actualizar = values =>{
        return(
            <table className = 'table table-hover table-dark'>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Nombre del usuario</th>
                        <th className = ''>Correo</th>
                        <th className = ''>Fecha de login</th>
                        <th className = ''></th>
                        <th className = ''></th>
                    </tr>
                </thead>
                <tbody className = '' >
                {
                    values.map((item)=>{                                                
                        return(
                            <tr className = '' key = {item.id}>
                                <td className = '' >{item.arquitecto.nombre} {item.arquitecto.apellido}</td>
                                <td className = '' >{item.correo}</td>
                                <td className = ''>{item.fechaLogin}</td>
                                <td className = ''><button className = 'btn btn-light' onClick = {this.handleEliminar} id = {item.id}>Eliminar</button></td>
                                <td className = ''><a href = {this.urlUsuarios+btoa(JSON.stringify(item))} >Mas informacion</a></td>
                            </tr>
                        )                        
                    })
                }
                </tbody>
            </table>                             
        )
    }

    actualizarProveedores = values =>{
        return(
            <table className = 'table table-hover table-dark'>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Nombre de la empresa</th>
                        <th className = ''>Nombre del representante</th>
                        <th className = ''>Correo</th>
                        <th className = ''>Fecha de login</th>
                        <th className = ''></th>
                        <th className = ''></th>
                    </tr>
                </thead>
                <tbody className = '' >
                {
                    values.map((item)=>{                                                
                        return(
                            <tr className = '' key = {item.id}>
                                <td className = '' >{item.proveedor.nombreEmpresa}</td>
                                <td className = '' >{item.proveedor.nombreEncargado} {item.proveedor.apellidoEncargado}</td>
                                <td className = '' >{item.correo}</td>
                                <td className = ''>{item.fechaLogin}</td>
                                <td className = ''><button className = 'btn btn-light' onClick = {this.handleEliminar} id = {item.id}>Eliminar</button></td>
                                <td className = ''><a href = {this.urlUsuarios+btoa(JSON.stringify(item))} >Mas informacion</a></td>
                            </tr>
                        )                        
                    })
                }
                </tbody>
            </table>                             
        )
    }

    //Este metodo elimina a un arquitecto o proveedor
    handleEliminar = async (e) =>{
        e.preventDefault()
        try{
            if(window.confirm("Esta seguro que deseas eliminar al usuario")){
                this.proveedores = await this.AdmService.allProveedores()
                this.resultado = await this.AdmService.eliminarUsuario(e.target.id)
                this.respuesta = true
                if(Object.keys(this.proveedores).length !== 0){
                    this.proveedores.forEach(item =>{
                        if(item.id === parseInt(e.target.id)){
                           this.handleProveedores()
                            this.respuesta = false
                            return false
                        }else{
                            this.respuesta = true
                        }
                    })
                }
                if(this.respuesta){
                    this.handleArquitectos()
                }
            }
        }catch(e){
            console.log(e)
        }    
    }

    //Este metodo es encargado de obtener los arquitectos registrados en el sistema
    handleArquitectos = async() =>{
        try{
            this.arquitectos = await this.AdmService.allArquitectos()
            if(Object.keys(this.arquitectos).length !== 0){
                ReactDOM.render(this.actualizar(this.arquitectos),document.getElementById("div"))
            }else{
                ReactDOM.render(<p>En este momento no hay arquitectos registrados</p>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    }

    //Este metodo es encargado de obtener los proveedores registrados en el sistema
    handleProveedores = async () =>{
        try{
            this.proveedores = await this.AdmService.allProveedores()
            if(Object.keys(this.proveedores).length !== 0){
                ReactDOM.render(this.actualizarProveedores(this.proveedores),document.getElementById("div"))
            }else{
                ReactDOM.render(<p>En este momento no hay proveedores registrados</p>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    }

    //Este metodo se encarga de mostrar la solicitudes de proveedores
    handleSolicitudes = async () =>{
        try{
            this.solicitudes = await this.AdmService.allSolicitudes()
            if(Object.keys(this.solicitudes).length !==0){
                ReactDOM.render(
                    <table className = 'table table-hover table-dark table-responsive'>
                        <thead className = ''>
                            <tr className = ''>
                                <th className = 'align-middle text-center'>Nombre de la empresa</th>
                                <th className = 'align-middle text-center'>Nombre del encargado</th>
                                <th className = 'align-middle text-center'>Apellido del encargado</th>
                                <th className = 'align-middle text-center'>Direcci&oacute;n</th>
                                <th className = 'align-middle text-center'>Tel&eacute;fono</th>
                                <th className = 'align-middle text-center'>Identificaci&oacute;n</th>
                                <th className = ''></th>
                                <th className = ''></th>
                            </tr>
                       </thead>
                        <tbody className = '' >
                            {
                                this.solicitudes.map((item)=>{                                                
                                    return(
                                        <tr className = '' key = {item.id}>
                                            <td className = 'align-middle text-center' >{item.nombreEmpresa}</td>
                                            <td className = 'align-middle text-center' >{item.nombreEncargado}</td>
                                            <td className = 'align-middle text-center' >{item.apellidoEncargado}</td>
                                            <td className = 'align-middle text-center' >{item.direccion}</td>
                                            <td className = 'align-middle text-center' >{item.telefono}</td>
                                            <td className = 'align-middle text-center' ><a href={"data:application/png;base64,"+item.identificacion} download={item.nombreEmpresa+".png"} className="text-white">Descargar identificacion</a></td>
                                            <td><button className = 'btn btn-light' id = {item.id} onClick = {this.handleRechazar}>Rechazar</button></td>
                                            <td><button className = 'btn btn-light' id = {item.id} onClick = {this.handleAceptar}>Aceptar</button></td>
                                        </tr>
                                    )                        
                                })
                            }
                      </tbody>
                    </table>             
                    ,document.getElementById("div")
                )
            } else{
                ReactDOM.render(<p>En este momento no hay solicitudes para registrar a un nuevo proveedor</p>,document.getElementById("div"))                
            }    
        }catch(e){
            console.log(e)
        }
    } 

    handleRechazar = async (e) =>{
        try{
            if(window.confirm("Esta seguro que deseas rechazar esta solicitud?")){
                this.res = await this.AdmService.rechazarSolicitud(e.target.id)
                this.handleSolicitudes()
            }
        }catch(e){
            console.log(e)
        }
    }

    handleAceptar = async (e) =>{
        e.preventDefault()
        try{
            this.res = await this.AdmService.aceptarSolicitud(e.target.id)
            alert("La solicitud a sido aceptada con éxito")
            this.handleSolicitudes()
        }catch(e){
            console.log(e)
        }
    }

    render(){
        return(   
            <Fragment>
                <button className = 'btn btn-light' onClick = {this.handleArquitectos}>Ver arquitectos</button>
                <button className = 'btn btn-light' onClick = {this.handleProveedores}>Ver proveedores</button>
                <button className = 'btn btn-light' onClick = {this.handleSolicitudes}>Solicitudes de proveedores</button>
            </Fragment>
        )
    }

}

export default MostrarUsuarios