import React from 'react'
import FormularioLogin from '../components/FormularioLogin'
import Boton from '../components/Boton'

class Login extends React.Component{
    constructor(props){
        super(props)
        this.state = {}
    }
    
    render(){
        return(
            <div className = ''>
                <div className = ''>
                    <FormularioLogin/>
                </div>
                <div className = ''>
                    <Boton
                        text = 'Registrarse como arquitecto'
                        url = '/RegistroArquitecto'
                    />
                </div>
                <div className = ''>
                    <Boton
                        text = 'Registrarse como proveedor'
                        url = '/RegistroProveedor'
                    />
                </div>
                <div className = ''>
                    <Boton
                        text = 'Olvidaste tu contraseña?'
                        url = '/RecuperarContra'
                    />
                </div>
            </div>
        )
    }
}
export default Login