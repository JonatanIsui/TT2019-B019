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
            <div className = 'container-fluid p-3 my-3 bg-dark text-white'>
                <div className='row justify-content-center'>
                    <div className = 'col-4-lg'>
                        <h1 className = ''>Recupera tu contraseña</h1>
                    </div>
                </div>

                <form className = '' onSubmit = {this.handleSubmit}>
                
                <div className='row justify-content-center'>
                            <div className='col-lg-4'>
                	        Correo:
                            </div>
                </div>
                <p></p>
                

                <div className='row justify-content-center'>
                            
                                <div className='col-lg-4'>
                                    <input type = 'email' placeholder = 'correo*' className = 'form-control' name = 'correo' onChange = {this.handleChange} required/>
                                </div>

                        </div>

                <p></p>
                <div className = 'row row justify-content-center'>
                    <input type = 'submit' value = 'Recuperar contraseña' className = 'btn btn-light'/>
                </div>
                </form>

                
            </div>
        )
    }
}

export default FormularioPassword