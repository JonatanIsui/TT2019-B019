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
            <table className = ''>
                <thead className = ''>
                    <tr className = ''>
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
                                <td className = '' >{item.correo}</td>
                                <td className = ''>{item.fechaLogin}</td>
                                <td className = ''><button className = '' onClick = {this.handleEliminar} id = {item.id}>Eliminar</button></td>
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
                console.log(e.target.id)
                console.log(this.proveedores)
                this.respuesta = true
                if(Object.keys(this.proveedores).length !== 0){
                    this.proveedores.forEach(item =>{
                        if(item.id === parseInt(e.target.id)){
                           this.handleProveedores()
                            this.respuesta = false
                            console.log("Son iguales")
                            return false
                        }else{
                            this.respuesta = true
                            console.log("Son difierentes")
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
                ReactDOM.render(this.actualizar(this.proveedores),document.getElementById("div"))
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
            console.log(this.solicitudes)
            if(Object.keys(this.solicitudes).length !==0){
                ReactDOM.render(
                    <table className = ''>
                        <thead className = ''>
                            <tr className = ''>
                                <th className = ''>Nombre de la empresa</th>
                                <th className = ''>Nombre del encargado</th>
                                <th className = ''>Apellido del encargado</th>
                                <th className = ''>Direccion</th>
                                <th className = ''>Telefono</th>
                                <th className = ''>Identificacion</th>
                                <th className = ''></th>
                                <th className = ''></th>
                            </tr>
                       </thead>
                        <tbody className = '' >
                            {
                                this.solicitudes.map((item)=>{                                                
                                    return(
                                        <tr className = '' key = {item.id}>
                                            <td className = '' >{item.nombreEmpresa}</td>
                                            <td className = '' >{item.nombreEncargado}</td>
                                            <td className = '' >{item.apellidoEncargado}</td>
                                            <td className = '' >{item.direccion}</td>
                                            <td className = '' >{item.telefono}</td>
                                            <td className = '' ><a href={"data:application/pdf;base64,"+item.identificacion} download={item.nombreEmpresa+".png"}>Descargar identificacion</a></td>
                                            <td><button className = '' id = {item.id} onClick = {this.handleRechazar}>Rechazar</button></td>
                                            <td><button className = '' id = {item.id} onClick = {this.handleAceptar}>Aceptar</button></td>
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
            if(window.confirm("Esta seguro que deseas rechazar esta solicitud")){
                this.res = await this.AdmService.rechazarSolicitud(e.target.id)
                this.handleSolicitudes()
            }
        }catch(e){
            console.log(e)
        }
    }

    handleAceptar = async (e) =>{
        try{
            this.res = await this.AdmService.aceptarSolicitud(e.target.id)
            this.handleSolicitudes()
        }catch(e){
            console.log(e)
        }
    }

    render(){
        return(   
            <Fragment>
                <button className = '' onClick = {this.handleArquitectos}>Ver arquitectos</button>
                <button className = '' onClick = {this.handleProveedores}>Ver proveedores</button>
                <button className = '' onClick = {this.handleSolicitudes}>Solicitudes de proveedores</button>
            </Fragment>
        )
    }

}

export default MostrarUsuarios