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
            <div className = 'container p-3 my-3 bg-dark text-white'>
                <div className = ''>
                    <FormularioLogin/>
                </div>
                <div className ='row'>
                    <div className = 'col-lg-4'>
                        <a href="/RegistroArquitecto"><button type="button" className='btn btn-light'>Registrarse como arquitecto</button></a>
                    </div>
                    <div className = 'col-lg-4'>
                        <a href="/RegistroProveedor"><button type="button" className='btn btn-light'>Registrarse como proveedor</button></a>
                    </div>
                    <div className = 'col-lg-4'>
                        <a href="/RecuperarContra"><button type="button" className='btn btn-light'>Olvidaste tu contraseña?</button></a>
                    </div>
                </div>
            </div>
        )
    }
}
export default Login