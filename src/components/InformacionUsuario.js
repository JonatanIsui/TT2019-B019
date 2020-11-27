import React from 'react'
import AdmService from '../service/AdmService'
import { withRouter } from "react-router";

class InformacionUsuario extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    id = JSON.parse(atob(this.props.match.params.id))
    AdmService = new AdmService()
    res = []
    paramatros
    
    handleEliminarUsuario = async (e) =>{
        e.preventDefault()
        try{
            if(window.confirm("Esta seguro que deseas eliminar al usuario")){
                this.res = await this.AdmService.eliminarUsuario(e.target.id)
                window.history.back()
            }
        }catch(e){
            console.log(e)
        }
    }

    handleRegresar = async (e) =>{
        e.preventDefault()
        window.history.back()
    }

    render(){
        if(this.id.proveedor === null){
            this.parametros = (<div>
                    <p className = ''>Nombre: {this.id.arquitecto.nombre}</p>
                    <p className = ''>Apellido: {this.id.arquitecto.apellido}</p>
                    <p className = ''>Direccion: {this.id.arquitecto.direccion}</p>
                    <p className = ''>Telefono: {this.id.arquitecto.telefono}</p>
                </div>
            )
        } else{
            this.parametros = (<div>
                    <p className = ''>Nombre de la empresa: {this.id.proveedor.nombreEmpresa}</p>
                    <p className = ''>Nombre del encargado: {this.id.proveedor.nombreEncargado}</p>
                    <p className = ''>Apellido del encargado: {this.id.proveedor.apellidoEncargado}</p>
                    <p className = ''>Direccion: {this.id.proveedor.direccion}</p>
                    <p className = ''>Telefono: {this.id.proveedor.telefono}</p>
                </div>
            )
        }
        return(
        <div className = ''>
            {this.parametros}
            <p className = ''>Correo: {this.id.correo}</p>
            <p className = ''>Ultima conexion: {this.id.fechaLogin}</p>
            <button className = '' onClick = {this.handleRegresar}>Cancelar</button>
            <button id ={this.id.id} className = '' onClick = {this.handleEliminarUsuario}>Eliminar</button>
        </div>
        )
    }
}

export default withRouter(InformacionUsuario)