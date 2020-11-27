import React from 'react'
import UsuarioService from '../service/UsuarioService'

class FormularioPassword extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    usuario = []
    UsuarioService = new UsuarioService()

    //Este metodo agrega el correo al estado
    handleChange = e =>{
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    //Este metodo con el corre se encarga de invocar el metodo que conecta con el servidor
    handleSubmit = e =>{
        e.preventDefault();
        this.setState({sending:true})
        try{
            this.usuario = this.UsuarioService.recuperarPassword(this.state.correo)
        }catch(errors){
            this.setState({errors:errors.message})   
        }finally{
            this.setState({sending : false})
        }
    }
    render(){
        return(
            <div className = ''>
                <div className = ''>
                    <h1 className = ''>Recupera tu constraseña</h1>
                </div>
                <div className = ''>
                    <form className = '' onSubmit = {this.handleSubmit}>
                        <div className = ''>
                            <input type = 'email' placeholder = 'correo' className = '' name = 'correo' onChange = {this.handleChange} required/>
                        </div>
                        <div className = ''>
                            <input type = 'submit' value = 'recuperar contraseña' className = ''/>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default FormularioPassword