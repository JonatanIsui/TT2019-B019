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

    //Este metodo es el encargado de recupelar los datos del formulario
    handleChange = e =>{
        if(e.target.name === 'correo' || e.target.name === 'password' || e.target.name === 'password2'){
            this.setState({
                [e.target.name] : e.target.value
            })
        }else{
            this.arquitecto[e.target.name] = e.target.value        
            this.setState({arquitecto : this.arquitecto})
        }
    }

    //Este metodo se encarga de mandar la informacion del arquitectos al servido
    handleSubmit = async e =>{
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
                this.usua = this.state;
                this.usuario = await this.ArquitectoService.addArquitecto(this.usua)
                alert('Ha sido aprovado inicie sesion para ingresar')
                window.history.back()
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
                            <div className='col-lg-4'>
                	        Nombres:
                            </div>
                            <div className='col-lg-4'>
                            Apellidos:
                            </div>
                        </div>


                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-4'>
                                <input type = 'text' placeholder = 'Nombre(s)*' className = 'form-control' name = 'nombre' onChange = {this.handleChange} required/>
                            </div>
                            <div className = 'col-lg-4'>
                                <input type = 'text' placeholder = 'Apellido(s)*' className = 'form-control' name = 'apellido' onChange = {this.handleChange} required/>
                            </div>
                        </div>
                        <p></p>


                        <div className='row justify-content-center'>
                            <div className='col-lg-4'>
                	        Telefono
                            </div>
                            <div className='col-lg-4'>
                            Direccion:
                            </div>
                        </div>
                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-4'>
                                <input type = 'tel' placeholder = 'Telefono' className = 'form-control' name = 'telefono' onChange = {this.handleChange}/>
                            </div>
                            <div className = 'col-lg-4'>
                                <input type = 'text' placeholder = 'Direccion' className = 'form-control' name = 'direccion' onChange = {this.handleChange}/>
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
                                <input type = 'email' placeholder = 'Correo*' className = 'form-control' name = 'correo' onChange = {this.handleChange} required/>
                            </div>
                        </div>
                        <p></p>
                        <div className='row justify-content-center'>
                            <div className='col-lg-4'>
                	        Contrasena:
                            </div>
                            <div className='col-lg-4'>
                            <span className='text-nowrap'>Repita la contrasena</span>
                            </div>
                        </div>
                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-4'>
                                <input type = 'password' placeholder = 'Contraseña*' className = 'form-control' name = 'password' onChange = {this.handleChange} required/>
                                {errors.password && <p className = ''>{errors.password}</p>}
                            </div>
                            <div className = 'col-lg-4'>
                                <input type = 'password' placeholder = 'Repite la contraseña*' className = 'form-control' name = 'password2' onChange = {this.handleChange} required/>
                                {errors.password2 && <p className = ''>{errors.password2}</p>}
                            </div>
                        </div>
                        <p></p>
                        <div className='row row justify-content-center'>
                            <div className = 'col-lg-3 text-center'>
                                <input type = 'submit' value = 'Registrase' className  = 'btn btn-light'/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        
        )
    }
}

export default FormularioUsuario