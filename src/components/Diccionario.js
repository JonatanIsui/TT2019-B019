import React, { Fragment } from 'react'
import AdmService from '../service/AdmService'
import ReactDOM from 'react-dom';
import FormularioDiccionario from '../components/FormularioDiccionario'

class Diccionario extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    AdmService =  new AdmService()
    definiciones = {}
    res = {}

    //Este metodo tiene la tabla que se renderiza
    actualizar = values =>{
        return(
            <table className = ''>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Articulo</th>
                        <th className = ' '>Definicion</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody className = '' >
                {
                    values.map((item)=>{                                                
                        return(
                            <tr className = '' key = {item.id}>
                                <td className = '' >{item.nombre}</td>
                                    <td className = '' >{item.definicion}</td>
                                    <td className = '' ><button className = '' id = {item.id} onClick = {this.handleEliminar}>Eliminar</button></td>
                            </tr>
                        )                        
                    })
                }
                </tbody>
            </table>                             
        )
    }
    //Este metodo muestra las definiciones
    handleVer = async (e) =>{
        try{
            this.definiciones = await this.AdmService.allDefiniciones()
            if(Object.keys(this.definiciones.length) !== 0 && typeof this.definiciones !== 'string'){
                console.log(this.definiciones)
                ReactDOM.render(this.actualizar(this.definiciones)       
                ,document.getElementById("div")
            )
            }else{
                ReactDOM.render(<p>En este momento no hay definiciones agregadas</p>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    }
    //Este metodo renderiza el formulario para agregar una nueva definicion
    handleNuevo = async (e) =>{
        ReactDOM.render(<FormularioDiccionario/>,document.getElementById("div"))
    }

    //Este metodo elimina una definicion
    handleEliminar = async (e) =>{
        e.preventDefault()
        try{
            this.res = await this.AdmService.eliminarDefinicion(e.target.id)
            this.handleVer()
        }catch(e){
            console.log(e)
        }
    }
    render(){
        return(
            <Fragment>
                <button className = '' onClick = {this.handleVer}>Ver diccionario</button>
                <button className = '' onClick = {this.handleNuevo}>Agregar definicion</button>
            </Fragment>
        )
    }
}

export default Diccionario