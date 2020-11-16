import React from 'react'
import {UsuarioService} from '../service/UsuarioService'
import { withRouter } from "react-router";
const validate = values =>{
    const errors = {}
    const stateToString = JSON.stringify (values.password).replace(/ /g,'')
    if(stateToString.length === 2){
        errors.password = 'No se permiten espacios en blanco'
    }
    return errors
}

class FormularioRecuperarPassword extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    UsuarioService = new UsuarioService()
    usuario = []
    handleChage = e =>{
        this.setState({
            [e.target.name] : e.target.value
        })
        //Recuperar el parametro
        const id = this.props.match.params.id;
        this.setState({"correo" : id}) 
    }

    handleSubmit = e =>{
        e.preventDefault()
        //Se manda el estado sin errores
        const {errors,...sinerrors} = this.state
        //Retorna los errores que se encuentra en los campos
        const result = validate(sinerrors)
        this.setState({errors : result})
        //Object.keys.length retorna el numero de propiedades del objeto
        //Si el objeto esta vacio
        if(!Object.keys(result).length){
            this.setState({sending : true})
            try{
                this.usua = this.state
                console.log(this.state)
                this.usuario = this.UsuarioService.cambioPassword(this.usua)
            }catch(errors){
                this.setState({errors:errors.message})
            }finally{
                this.setState({sending : false})
            }
        }
    }
    
    render(){
        const{errors} = this.state
        return(
            <div className = ''>
                <div className = ''>
                    <h1 className = ''>Recupera tu contraseña</h1>
                </div>
                <div className = ''>
                    <form className = '' onSubmit = {this.handleSubmit}>
                        <div className = ''>
                            <input type = 'password' placeholder = 'Contraseña*' className = '' name = 'password' onChange = {this.handleChage} required/>
                            {errors.password && <p className =''>{errors.password}</p>}
                        </div>
                        <div className = ''>
                            <input type = 'password' placeholder = 'Repite la contraseña*' className = '' name = 'password2' onChange = {this.handleChage} required/>
                            {errors.password2 && <p className =''>{errors.password2}</p>}
                        </div>
                        <div className = ''>
                            <input type = 'submit' value = 'Recuperar contraseña' className = ''/>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default withRouter(FormularioRecuperarPassword)