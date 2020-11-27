import React, { Fragment } from 'react'
import AdmService from '../service/AdmService'

class FormularioDiccionario extends React.Component{
    state = {
        errors: {} ,
        sending: true
    }
    AdmService = new AdmService()
    res = []

    //Este metodo agrega lo ingresado en los input al state
    handleChage = (e) =>{
        this.setState({
            [e.target.name] : e.target.value
        })
    }
    //Este metodo envia el state a spring
    handleAdd = async (e) =>{
        try{
            this.res = await this.AdmService.addDefinicion(this.state)
        }catch(e){
            console.log(e)
        }
    }
    render(){
        return(
            <Fragment>
                <form onSubmit = {this.handleAdd}>
                    <div className = ''>
                        <input type = 'text' placeholder = 'Palabra*' className = '' name = 'nombre' onChange = {this.handleChage} required/>
                    </div>
                    <div className = ''>
                        <input type = 'text' placeholder = 'Definicion*' className = '' name = 'definicion' onChange = {this.handleChage} required/>
                    </div>
                    <div className = ''>
                        <input type = 'submit' value = 'Agregar definicion' className = ''/>
                    </div>
                    </form>
            </Fragment>
        )
    }
}
export default FormularioDiccionario