import React from 'react'
import AdmService from '../service/AdmService'

class InformacionUsuario extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    id = this.props.info
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
    render(){
        if(this.id.proveedor === null){
            this.parametros = (<div>
                    <p className = ''>Nombre: {this.id.arquitecto.nombre}</p>
                    <p className = ''>Apellido: {this.id.arquitecto.apellido}</p>
                    <p className = ''>Dirección: {this.id.arquitecto.direccion}</p>
                    <p className = ''>Teléfono: {this.id.arquitecto.telefono}</p>
                </div>
            )
        } else{
            this.parametros = (<div>
                    <p className = ''>Nombre de la empresa: {this.id.proveedor.nombreEmpresa}</p>
                    <p className = ''>Nombre del encargado: {this.id.proveedor.nombreEncargado}</p>
                    <p className = ''>Apellido del encargado: {this.id.proveedor.apellidoEncargado}</p>
                    <p className = ''>Dirección: {this.id.proveedor.direccion}</p>
                    <p className = ''>Teléfono: {this.id.proveedor.telefono}</p>
                </div>
            )
        }
        return(
        <div className = ''>
            {this.parametros}
            <p className = ''>Correo: {this.id.correo}</p>
            <p className = ''>Última conexión: {this.id.fechaLogin}</p>
            <button id ={this.id.id} className = 'btn btn-dark' onClick = {this.handleEliminarUsuario}>Eliminar</button>
        </div>
        )
    }
}

export default InformacionUsuario