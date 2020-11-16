import { render } from '@testing-library/react'
import React from 'react'

class FormularioRecuperarPassword extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    render(){
        const{errors} = this.state
        return(
            <div className = ''>
                <div className = ''>
                    <h1 className = ''>Iniciar sesión</h1>
                </div>
                <div className = ''>
                    <form className = '' onSubmit = {this.handleSubmit}>
                        <div className = ''>
                            <input type = 'password' placeholder = 'correo*' className = '' name = 'correo' onChange = {this.handleChage} required/>
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
