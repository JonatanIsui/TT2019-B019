import React, { Fragment } from 'react'
import AdmService from '../service/AdmService'
import ReactDOM from 'react-dom';

class Diccionario extends React.Component{
    state = {}
    AdmService =  new AdmService()
    definiciones = {}
    res = {}

    //Este metodo tiene la tabla que se renderiza
    actualizar = values =>{
        return(
            <table className = 'table table-hover table-dark'>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Articulo</th>
                        <th className = ' '>Definicion</th>
                        <th></th>
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
                                    <td className = '' ><button className = 'btn btn-light' id = {item.id} onClick = {this.handleEliminar}>Eliminar</button></td>
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
    handleNuevo = async () =>{
        ReactDOM.render(
        <Fragment>
            <div className = 'container-fluid'>
                <form id = 'nuevo' onSubmit = {this.handleAdd}>
                    <p></p>
                    <div className = 'row justify-content-center'>
                        <div className = 'col-lg-8 text-center'>
                            Palabra:<input type = 'text' placeholder = 'Palabra*' className = 'form-control' id = 'nombreDiccionario' name = 'nombre' required/>
                        </div>
                    </div>
                    <p></p>
                    <div className = 'row justify-content-center'>
                        <div className = 'col-lg-8 text-center'>
                            Definición:<input type = 'text' placeholder = 'Definicion*' className = 'form-control' id = 'definicionDiccionario' name = 'definicion' required/>
                        </div>
                    </div>
                    <p></p>
                    <div className = 'row justify-content-center'>
                        <div className = 'col-lg-8 text-center'>
                            <input type = 'submit' value = 'Agregar definicion' className = 'btn btn-light'/>
                            <p></p>
                        </div>
                    </div>
                </form>
            </div>
        </Fragment>,document.getElementById("div"))
    }

    //Este metodo envia el state a spring
    handleAdd = async (e) =>{
        e.preventDefault()
        let nombre1 = document.getElementById('nombreDiccionario').value
        let definicion1 = document.getElementById('definicionDiccionario').value 
        try{
            if(JSON.stringify (nombre1).replace(/ /g,'').length>2 && JSON.stringify (definicion1).replace(/ /g,'').length>2){
                await this.setState({
                    "nombre":nombre1,
                    "definicion":definicion1
                })
                this.res = await this.AdmService.addDefinicion(this.state)
                alert(this.res)
                document.getElementById("nuevo").reset()
            }
        }catch(e){
            console.log(e)
        }
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
                <button className = 'btn btn-light' onClick = {this.handleVer}>Ver diccionario</button>
                <button className = 'btn btn-light' onClick = {this.handleNuevo}>Agregar definici&oacute;n</button>
            </Fragment>
        )
    }
}

export default Diccionario