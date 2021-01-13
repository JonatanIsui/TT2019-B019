import React from 'react'
import ArquitectoService from '../service/ArquitectoService'

//Este metodo se encarga de evitar que solo se ingresen espacios
const validate = values =>{
    const errors = {}
    const stateToString = JSON.stringify (values.password).replace(/ /g,'')
    if(stateToString.length === 2){
        errors.password = 'No se permiten espacios en blanco'
    }
    if(JSON.stringify(values.password) !== JSON.stringify(values.password2)){
        errors.password2 = 'La contraseña no coincide'
    }
    return errors
} 

class FormularioUsuario extends React.Component{
    state = {
        errors : {},
        sending : false,
        arquitecto : {}
    }
    ArquitectoService = new ArquitectoService()
    usuario = []
    arquitecto = {}

    //Este metodo se encarga de mandar la informacion del arquitectos al servido
    handleSubmit = async e =>{
        e.preventDefault()
        //Retorna los errores que se encuentra en los campos
        this.arquitecto['nombre']=document.getElementById('nombreArquitecto').value
        this.arquitecto['apellido']=document.getElementById('apellidoArquitecto').value
        this.arquitecto['telefono']=document.getElementById('telefonoArquitecto').value
        this.arquitecto['direccion']=document.getElementById('direccionArquitecto').value
        await this.setState({
            arquitecto : this.arquitecto,
            correo : document.getElementById('correoArquitecto').value,
            password : document.getElementById('passwordArquitecto').value,
            password2 : document.getElementById('password2Arquitecto').value    
        })
        //Se manda el estado sin errores
        const {errors,...sinerrors} = this.state
        const result = validate(sinerrors)
        this.setState({errors : result})
        //Object.keys.length retorna el numero de propiedades del objeto
        //Si el objeto esta vacio
        if(!Object.keys(result).length){
            this.setState({sending : true})
            try{
                this.usua = this.state;
                this.usuario = await this.ArquitectoService.addArquitecto(this.usua)
                if(this.usuario.length !== 0){
                    alert('Ha sido aprobado inicie sesión para ingresar')
                    window.history.back()
                }else{
                    alert('El correo ingresado ya se encuentra registrado')
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
                        <h1>Registro de arquitecto</h1>
                    </div>
                </div>

                <div className = ''>
                    
                    <form className = '' onSubmit = {this.handleSubmit}>


                        <div className='row justify-content-center'>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                	        Nombre(s):
                            </div>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                            Apellido(s):
                            </div>
                        </div>


                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'text' placeholder = 'Nombre(s)*' className = 'form-control' name = 'nombre' id='nombreArquitecto' required/>
                            </div>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'text' placeholder = 'Apellido(s)*' className = 'form-control' name = 'apellido' id='apellidoArquitecto' required/>
                            </div>
                        </div>
                        <p></p>


                        <div className='row justify-content-center'>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                	        Tel&eacute;fono
                            </div>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                            Direcci&oacute;n:
                            </div>
                        </div>
                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'tel' placeholder = 'Tel&eacute;fono' className = 'form-control' name = 'telefono' id='telefonoArquitecto'/>
                            </div>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'text' placeholder = 'Direcci&oacute;n' className = 'form-control' name = 'direccion' id='direccionArquitecto'/>
                            </div>
                        </div>
                        <p></p>
                        <div className='row justify-content-center'>
                            <div className='col-lg-8'>
                	        Correo:
                            </div>
                        </div>
                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-8'>
                                <input type = 'email' placeholder = 'Correo*' className = 'form-control' name = 'correo' id='correoArquitecto' required/>
                            </div>
                        </div>
                        <p></p>
                        <div className='row justify-content-center'>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                	        Contrase&ntilde;a:
                            </div>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                            <span className='text-nowrap'>Repita la contrase&ntilde;a</span>
                            </div>
                        </div>
                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'password' placeholder = 'Contrase&ntilde;a*' className = 'form-control' name = 'password' id='passwordArquitecto' required/>
                                {errors.password && <p className = ''>{errors.password}</p>}
                            </div>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'password' placeholder = 'Repite la contrase&ntilde;a*' className = 'form-control' name = 'password2' id='password2Arquitecto' required/>
                                {errors.password2 && <p className = ''>{errors.password2}</p>}
                            </div>
                        </div>
                        <p></p>
                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-3 text-center'>
                                <input type = 'submit' value = 'Registrarse' className  = 'btn btn-light'/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default FormularioUsuario