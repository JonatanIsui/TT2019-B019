import React from 'react'
import SolicitudProveedorService from '../service/SolicitudProveedorService'
//Metodo para validar que no se ingresen espacios en blanco
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
class FormularioProveedor extends React.Component{
    state = {
        errors : {},
        sending : false,
    }
    usuario = []
    SolicitudProveedorService = new SolicitudProveedorService()

    //Este metodo se encarga de cargar imagen al estado 
    handleFile = async e =>{
        e.preventDefault()
        const reader = new FileReader();
        if(Object.keys(e.target.files).length){
            await this.setState({
                [e.target.name] : e.target.files[0]
            })
            reader.readAsDataURL(this.state.identificacion)
            reader.onload = async t =>{
                await this.setState({
                    [e.target.name] : t.target.result
                })
            }
        }
    }

    //Este metodo se encarga de mandar el estado para mandar los datos del proveedor solicitante
    handleSubmit = async e =>{
        e.preventDefault()
        //Se manda el estado sin errores
        await this.setState({
            nombreEncargado:document.getElementById('nombreEncargadoProveedor').value,
            apellidoEncargado:document.getElementById('apellidoEncargadoProveedor').value,
            direccion:document.getElementById('direccionProveedor').value,
            nombreEmpresa:document.getElementById('nombreEmpresaProveedor').value,
            telefono:document.getElementById('telefonoProveedor').value,
            correo:document.getElementById('correoProveedor').value,
            password:document.getElementById('passwordProveedor').value,
            password2:document.getElementById('password2Proveedor').value
        })
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
                this.usuario = await this.SolicitudProveedorService.addSolicitudProveedor(this.usua)
                if(Object.keys(this.usuario).length !== 0){
                    alert('Solicitud enviada con éxito, espere respuesta al correo : '+this.usuario)
                    window.history.back()
                }else{
                    alert('Lo sentimos los datos porporcionados no son correctos o hay una solicitud pendiente')
                }
            }catch(errors){
                this.setState({errors:errors.message})
                console.log(errors)
            }finally{
                this.setState({sending : false})
            }
        }
    }

    render(){
        const{errors} = this.state
        return (
            <div className = 'container-fluid p-3 my-3 bg-dark text-white'>
                <div className = 'row justify-content-center'>
                    <h1>Registro de proveedor</h1>
                </div>
                <div className = ''>
                    <form className = '' onSubmit = {this.handleSubmit}>


                        <div className='row justify-content-center'>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                	        Nombre(s) del representante:
                            </div>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                            Apellidos del respresentante:
                            </div>
                        </div>
                        <div className='row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'text' placeholder = 'Nombre(s) del representante' className = 'form-control' name = 'nombreEncargado' id='nombreEncargadoProveedor' required/>
                            </div>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'text' placeholder = 'Apellidos del respresentante' className = 'form-control' name = 'apellidoEncargado' id='apellidoEncargadoProveedor' required/>
                            </div>
                        </div>
                        <p></p>


                        <div className='row justify-content-center'>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                	        Direcci&oacute;n de la empresa:
                            </div>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                            Nombre de la empresa:
                            </div>
                        </div>

                        <div className='row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'text' placeholder = 'Direcci&oacute;n de la empresa' className = 'form-control' name = 'direccion' id='direccionProveedor' required/>
                            </div> 
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'text' placeholder = 'Nombre de la empresa' className = 'form-control' name = 'nombreEmpresa' id='nombreEmpresaProveedor' required/>
                            </div>
                        </div>
                        <p></p>
                        <div className='row justify-content-center'>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                	        Tel&eacute;fono de la empresa:
                            </div>
                            <div className='col-lg-4 col-md-6 col-sm-6 col-6'>
                            Email de la empresa:
                            </div>
                        </div>



                        <div className='row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'tel' placeholder = 'tel&eacute;fono de la empresa' className = 'form-control' name = 'telefono' id='telefonoProveedor' required/>
                            </div>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6'>
                                <input type = 'email' placeholder = 'Email de la empresa' className = 'form-control' name = 'correo' id='correoProveedor' required/>
                            </div>
                          </div>
                          <p></p>
                          
                        <div className='row justify-content-center'>
                            <div className='col-lg-6 text-center '>
                                    <input type = 'file' accept=".png" className='form-control-file border' name = 'identificacion' onChange = {this.handleFile} required/> <span className='text-nowrap'>Identificaci&oacute;n en formato png</span>
                            </div>                   
                        </div>
                        <p></p>
                        
                        <div className='row justify-content-center'>
                            <div className='col-lg-4'>
                	        Contrase&ntilde;a:
                            </div>
                            <div className='col-lg-4'>
                            <span className='text-nowrap'>Repita la contrase&ntilde;a</span>
                            </div>
                        </div>

                        <div className='row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 '>
                                <input type = 'password' placeholder = 'Contrase&ntilde;a' className = 'form-control' name = 'password' id='passwordProveedor' required/>
                                {errors.password && <p className = ''>{errors.password}</p>}
                            </div> 
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 '>
                                <input type = 'password' placeholder = 'Repita la contrase&ntilde;a' className = 'form-control' name = 'password2' id='password2Proveedor' required/>
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

export default FormularioProveedor