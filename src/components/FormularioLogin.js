import React from 'react'
import {UsuarioService} from '../service/UsuarioService'

//No permite espacios en blanco en la contraseña
const validate = values =>{
    const errors = {}
    const stateToString = JSON.stringify (values.password).replace(/ /g,'')
    if(stateToString.length === 2){
        errors.password = 'No se permiten espacios en blanco'
    }
    return errors
}

class FormularioLogin extends React.Component{
    /*Los prosp son definidas por el padre y son fijas durante 
la vida de un componente. El estado cambia con el tiempo*/     
    state = {
        errors : {},
        sending : false,
    }
    UsuarioService = new UsuarioService()
    usuario = []
    
    handleChage = e =>{
        this.setState({
            [e.target.name] : e.target.value
        })
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
        console.log(this.state)
        if(!Object.keys(result).length){
            this.setState({sending : true})
            try{
                this.usua = this.state;
                this.usuario = this.UsuarioService.login(this.usua)
                //Si no es nulo
                if(!!this.usuario.arquitecto){
                    window.location = '/'
                }else if(!!this.usuario.proveedor){
                    window.location = '/'
                }else if(!!this.usuario.administrador){
                    window.location = '/'
                }
            }catch(errors){
                this.setState({errors:errors.message})
            }finally{
                this.setState({sending : false})
            }
        }
    }
    render(){
        //&& si existe lo de la izquierda, retorna lo de la derecha del estado
        const{errors} = this.state
        return(
            <div className = ''>
                <div className = ''>
                    <h1 className = ''>Iniciar sesión</h1>
                </div>
                <div className = ''>
                    <form className = '' onSubmit = {this.handleSubmit}>
                        <div className = ''>
                            <input type = 'email' placeholder = 'correo*' className = '' name = 'correo' onChange = {this.handleChage} required/>
                            {errors.email && <p className =''>{errors.email}</p>}
                        </div>
                        <div className = ''>
                            <input type = 'password' placeholder = 'contraseña*' className = '' name = 'password' onChange = {this.handleChage} required/>
                            {errors.password && <p className =''>{errors.password}</p>}
                        </div>
                        <div className = ''>
                            <input type = 'submit' value = 'Iniciar sesión' className = ''/>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default FormularioLogin