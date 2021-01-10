import React from 'react'
import UsuarioService from '../service/UsuarioService'
import ReactDOM from 'react-dom'
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
        errors : {}
    }
    UsuarioService = new UsuarioService()
    usuario = []
    
    //Metodo que se encarga de llamar el servidor para ingresar al sistema
    handleSubmit = async e =>{
        e.preventDefault()
        await this.setState(
            {
                "correo" : document.getElementById('correoLogin').value,
                "password" : document.getElementById('passwordLogin').value
            }
        )
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
                this.usua = this.state;
                this.usuario =await this.UsuarioService.login(this.usua)
                const{password,...sinpassword} = this.usuario
                //Cifrar y serializar
                if(!!this.usuario.arquitecto){
                    localStorage.setItem('arquitecto','true')
                    const red = btoa(JSON.stringify(sinpassword.arquitecto))
                    window.location = '/Arquitecto/'+red+''
                }else if(!!this.usuario.proveedor){
                    localStorage.setItem('proveedor','true')
                    const red = btoa(JSON.stringify(sinpassword.proveedor))
                    window.location = '/Proveedor/'+red+''
                }else if(!!this.usuario.administrador){
                    localStorage.setItem('administrador','true')
                    const red = btoa(JSON.stringify(sinpassword.administrador))
                    window.location = '/Administrador/'+red+''
                }else{
                    ReactDOM.render(<p className = ''>contraseña o correo incorrecto</p>,document.getElementById('usuario'))
                }
            }catch(errors){
                console.log(e)
                this.setState({errors:errors.message})
            }
        }
    }
    render(){
        //&& si existe lo de la izquierda, retorna lo de la derecha del estado
        const{errors} = this.state
        return(
            <div className = 'container-fluid p-3 my-3 bg-dark text-white'>
                <div className = 'row justify-content-center'>

                    <div className='col-lg-4'><h1 className = ''>Iniciar sesión</h1></div>

                </div>
                <div className = ''>
                    <div className = '' id='usuario'>
                    </div>
                    
                    <form className = '' onSubmit = {this.handleSubmit}>
                        <div className='row'>
                            <div className = 'input-group'>
                                

                            <div className='col-lg-4 text-right'>Correo:</div>
                            <div className='col-lg-4'>
                                <span className="input-group-addon"><i className="glyphicon glyphicon-user"></i></span>
                                <input type = 'email' placeholder = 'correo*' className = 'form-control' id='correoLogin' name = 'correo' required/>
                                {errors.email && <p className =''>{errors.email}</p>}
                            </div>



                            </div>
                        </div>

                        <p></p>

                        <div className='row' >
                            <div className='col-lg-4 text-right'>Contraseña:</div>
                            <div className='col-lg-4'>
                                <input type = 'password' placeholder = 'contraseña*' className = 'form-control' id='passwordLogin' name = 'password' required/>
                                {errors.password && <p className =''>{errors.password}</p>}</div>

                        </div>
                        <p></p>
                        <div className='row justify-content-center'>
                            <div className = 'col-lg-3'>
                                <input type = 'submit' value = 'Iniciar sesión' className = 'btn btn-light '/>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        )
    }
}

export default FormularioLogin