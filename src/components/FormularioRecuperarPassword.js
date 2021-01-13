import React from 'react'
import UsuarioService from '../service/UsuarioService'
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

    //Este metodo se encarga de mandar el correo y la nueva contraseña al servidor
    handleSubmit = async e =>{
        e.preventDefault()
        const id = this.props.match.params.id;
        this.setState({"correo" : id}) 
        await this.setState({
            password:document.getElementById('passwordRecuperar').value,
            password2:document.getElementById('password2Recuperar').value,
            "correo" : id
        })
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
                this.usuario = await this.UsuarioService.cambioPassword(this.usua)
                const{password,...sinpassword} = this.usuario
                //Cifrar y serializar
                if(!!this.usuario.arquitecto){
                    localStorage.setItem('arquitecto','true')
                    const red = btoa(JSON.stringify(sinpassword.arquitecto))
                    this.props.match.params.id=null
                    alert("La contraseña se restablecio con éxito")
                    window.location = '/Arquitecto/'+red+''
                }else if(!!this.usuario.proveedor){
                    localStorage.setItem('proveedor','true')
                    const red = btoa(JSON.stringify(sinpassword.proveedor))
                    this.props.match.params.id=null
                    alert("La contraseña se restablecio con éxito")
                    window.location = '/Proveedor/'+red+''
                }else if(!!this.usuario.administrador){
                    localStorage.setItem('administrador','true')
                    const red = btoa(JSON.stringify(sinpassword.administrador))
                    this.props.match.params.id=null
                    alert("La contraseña se restablecio con éxito")
                    window.location = '/Administrador/'+red+''
                }else{
                    alert("En estos momento no se puede restablecer tu contraseña, intente mas tarde")
                }
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
            <div className = 'container-fluid p-3 my-3 bg-dark text-white'>
                <div className='row justify-content-center'>
                    <div className = 'col-4-lg'>
                        <h1 className = ''>Recupera tu contrase&ntilde;a</h1>
                    </div>
                </div>

                <div className = ''>
                <form className = '' onSubmit = {this.handleSubmit}>
                    <div className = 'row justify-content-center'>
                            <div className = 'col-lg-4 text-center'>
                                Introduce una contrase&ntilde;a nueva<input type = 'password' placeholder = 'Contrase&ntilde;a*' className = 'form-control' name = 'password' id='passwordRecuperar' onChange = {this.handleChage} required/>
                                {errors.password && <p className =''>{errors.password}</p>}
                            </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className = 'col-lg-4 text-center'>
                            Repite la contrase&ntilde;a<input type = 'password' placeholder = 'Repite la contrase&ntilde;a*' className = 'form-control' name = 'password2' id='password2Recuperar' onChange = {this.handleChage} required/>
                            {errors.password2 && <p className =''>{errors.password2}</p>}
                        </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className = 'col-lg-4 text-center'>
                            <input type = 'submit' value = 'Recuperar contraseña' className = 'btn btn-light'/>
                        </div>
                    </div>
                </form>
                </div>


            </div>
        )
    }
}

export default withRouter(FormularioRecuperarPassword)