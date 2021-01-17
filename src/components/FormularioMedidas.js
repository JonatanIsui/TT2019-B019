import React, { Fragment } from 'react'
import ReactDOM from 'react-dom'
import ArquitectoService from '../service/ArquitectoService'

class FormularioMedidas extends React.Component{
    state = {}
    num=0
    dim = {}
    ancho = 0
    largo = 0
    pisos = 0
    ladrillo = 0
    arquitecto = 0
    ArquitectoService = new ArquitectoService()
    res={}
    etiquetas = ['agua','Arena','Grava','Saco de cemento','Saco de Mortero','Varilla','Ladrillo Rojo','Ladrillo Block Ligero','Ladrillo Block Pesado','Alambre','Varilla armex']
    nombresObjeto=["",'arena','grava','saco','sacoMortero','varilla','ladrilloRojo','ladrilloBlockLigero','ladrilloBloackPesado','alambre','varillaArmex']
    descripciones=["Botes de 19 L","arenaDesc","gravaDesc","sacoDesc","sacoMorteroDesc","varillaDesc","ladrilloRojoDesc","ladrilloBlockLigeroDesc","ladrilloBloackPesadoDesc","alambreDesc","varillaArmexDesc"]
    nombresObjetoCosto=["",'arenaCosto','gravaCosto','sacoCosto','sacoMorteroCosto','varillaCosto','ladrilloRojoCosto','ladrilloBlockLigeroCosto','ladrilloBloackPesadoCosto','alambreCosto','varillaArmexCosto']
    enviar=false
    totalTerreno=0
    metros2=0
    numHabitaciones = e =>{
        this.div = document.getElementById('habitacion')
        for(let i = 0; i<e; i++){
            document.getElementById('habitacion').insertAdjacentHTML("beforebegin",
                "<div class='row justify-content-center'>"+
                    "<div class = 'col-lg-8 text-center'>"+
                        "<h3>Habitaci&oacute;n "+(i+1)+"</h3>"+
                    "</div>"+
                "</div>"+

                "<div class='row justify-content-center'>"+
                    "<div class = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>"+
                        "Ancho en metros de la habitaci&oacute;n*:<input name = 'anchoHabitacion"+(i+1)+"' type = 'number' min='2' max='4' placeholder = 'ancho en metros de la habitacion*' step = '0.01' required class = 'form-control'/>"+
                    "</div>"+
                    "<div class = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>"+
                        "Largo en metros de la habitaci&oacute;n*:<input name = 'largoHabitacion"+(i+1)+"'  type = 'number' min='2' max='2.8' placeholder = 'largo en metros de la habitacion*' step = '0.01' required class = 'form-control'/>"+
                    "</div>"+
                "</div>"
           )
        }
    }
    materia = (consulta) =>{
        document.getElementById('0').insertAdjacentHTML("beforebegin",
            "<td className = ''>"+this.etiquetas[0]+"</td>"+
            "<td className = ''>"+this.descripciones[0]+"</td>"+
            "<td className = ''>"+consulta.agua+"</td>"+
            "<td className = ''> de 0 a 15 mil litros de agua son $44.94</td>"+
            "<td className = ''>$44.94</td>"  
        )
        for(let i = 1;i<this.nombresObjeto.length;i++){
            document.getElementById(i.toString()).insertAdjacentHTML("beforebegin",
                "<td className = ''>"+this.etiquetas[i]+"</td>"+
                "<td className = ''>"+consulta[this.descripciones[i]]+"</td>"+
                "<td className = ''>"+consulta[this.nombresObjeto[i]]+
                "<td className = ''> $"+(consulta[this.nombresObjetoCosto[i]]).toFixed(2)+"</td>"+
                "<td className = ''>$"+(consulta[this.nombresObjeto[i]]*consulta[this.nombresObjetoCosto[i]]).toFixed(2)+"</td>"
        )}
        let totalpromedio = consulta["total"]
        document.getElementById('total').insertAdjacentHTML("beforebegin",
            "<td className = '' colspan='3'>Promedio costo de construcion</td>"+
            "<td className = ''>$"+totalpromedio.toFixed(2)+"</td>"
        )
        ReactDOM.render(this.mostrarProveedor(consulta),document.getElementById('datosProveedor'))
        ReactDOM.render(this.herramientas(),document.getElementById('11'))
    }

    herramientas=()=>{
        return(
            <Fragment>
                <div className='row justify-content-center'>  
                <div className = 'col-lg-10 text-center'>
                <table className = 'table table-hover table-dark'>
                    <thead className = ''>
                        <tr className = ''>
                            <th className = ''>Herramientas recomendadas</th>
                            <th className = ''>Maquinaria recomendada</th>
                            </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Pala</td>
                            <td>Apisonadores para compactación o bailarinas</td>
                        </tr>
                        <tr>
                            <td>Pico</td>
                            <td>Excavadora</td>
                        </tr>
                        <tr>
                            <td>Marro o  mazo</td>
                            <td>Retroexcavadora</td>
                        </tr>
                        <tr>
                            <td>Cizallas</td>
                            <td>Mezcladora de Cemento</td>
                        </tr>
                        <tr>
                            <td>Cincel</td>
                        </tr>
                        <tr>
                            <td>Maceta</td>
                        </tr>
                        <tr>
                            <td>Paleta</td>
                        </tr>
                        <tr>
                            <td>Llana</td>
                        </tr>
                        <tr>
                            <td>Nivel</td>
                        </tr>
                        <tr>
                            <td>Flex&oacute;metro</td>
                        </tr>
                        <tr>
                            <td>Carretilla</td>
                        </tr>
                    </tbody>
                </table>
                </div>
                </div>
            </Fragment>
        )
    }
    consulta = (nombre) =>{
        return(
            <div className='row justify-content-center'>  
                <div className = 'col-lg-10 text-center'>
                <table className = 'table table-hover table-dark'>
                    <thead className = ''>
                        <tr className = ''>
                            <th className = ''>Material</th>
                            <th className = ''>Descripcion</th>
                            <th className = ''>Cantidad</th>
                            <th className = ''>Costo promedio</th>
                            <th className = ''>Total</th>
                        </tr>
                    </thead>
                    <tbody className = '' >
                        <tr className = '' id = '0'>
                        </tr>
                        <tr className = '' id = '1'>
                        </tr>
                        <tr className = '' id = '2'>
                        </tr>
                        <tr className = '' id = '3'>
                        </tr>
                        <tr className = '' id = '4'>
                        </tr>
                        <tr className = '' id = '5'>
                        </tr>
                        <tr className = '' id = '6'>
                        </tr>
                        <tr className = '' id = '7'>
                        </tr>
                        <tr className = '' id = '8'>
                        </tr>
                        <tr className = '' id = '9'>
                        </tr>
                        <tr className = '' id = '10'>
                        </tr>
                        <tr className = '' id = 'total'>
                        </tr>
                    </tbody>
                </table>
                </div>
                <div className = 'col-lg-10 text-center' id='11'>
                </div>
                <div className = 'col-lg-10 text-center' id='datosProveedor'>
                </div>
                <div className='container-fluid'>
                    <div className='row justify-content-center'>
                        <div className = 'col-lg-4 text-center'>
                            <button className = 'btn btn-light' name={nombre} onClick={this.handleEliminarConsulta}>Eliminar consulta</button>
                        </div>
                    </div>
                </div>
            </div>                  
        )
    }
    mostrarProveedor=(proveedor)=>{
        return(
        <Fragment>
            <table className = 'table table-hover table-dark'>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Proveedor recomendado</th>
                        <th className = ''>Correo</th>
                        <th className = ''>Telefono</th>
                        <th className = ''>Direccion</th>
                    </tr>
                </thead>
                <tbody className = '' >
                    { 
                        proveedor.proveedorConsulta.map((item)=>{   
                        return(
                            <tr className = '' key = {item.nombreProveedor}>
                                <td>{item.nombreProveedor}</td>
                                <td>{item.correoProveedor}</td>
                                <td>{item.telefonoProveedor}</td>
                                <td>{item.direccionProveedor}</td>
                            </tr>
                            )
                        })
                    }
                </tbody>
            </table>
        </Fragment>)
    }

    habitacion = () =>{
        return(
            <Fragment>
                <form onSubmit = {this.handleNumHabitaciones}>
                            
                            <div id = 'habitacion'>
                            </div>

                        <div className='row justify-content-center'>
                            <div className = 'col-lg-8 text-center'>
                                <h3>Cocina</h3>
                            </div>
                        </div>
                        <div className='row justify-content-center'>
            	            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>
                                Ancho en metros de la cocina*:<input name = 'anchococina' className = 'form-control' type = 'number' min='2' max='4' placeholder = 'ancho en metros de la cocina*' step = '0.01' required />
                            </div>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>
                                Largo en metros de la cocina*:<input name = 'largococina' className = 'form-control' type = 'number' min='2' max='4' placeholder = 'largo en metros de la cocina*' step = '0.1' required/>
                            </div>
                        </div>


                        <div className='row justify-content-center'>
                            <div className = 'col-lg-8 text-center'>
                                <h3>Cuarto de lavado</h3>
                            </div>
                        </div>

                        <div className = 'row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>
                                Ancho en metros del cuarto del lavado*:<input name = 'ancholavado' className = 'form-control' type = 'number' min='1' max='3' placeholder = 'ancho en metros del cuarto del lavado*' step = '0.01' required/>
                            </div>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>
                                Largo en metros del cuarto del lavado*:<input name = 'largolavado' className = 'form-control' type = 'number' min='1' max='3' placeholder = 'largo en metros del cuarto del lavado*' step = '0.01' required/>
                            </div>
                        </div>


                        <div className='row justify-content-center'>
                            <div className = 'col-lg-8 text-center'>
                                <h3>Ba&ntilde;o</h3>
                            </div>
                        </div>

                        <div className = 'row justify-content-center'>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>
                                Ancho en metros del ba&ntilde;o*:<input name = 'anchobano' className = 'form-control' type = 'number' min='2' max='2.5' placeholder = 'ancho en metros del baño*' step = '0.01' required/>
                            </div>
                            <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>
                                Largo en metros del ba&ntilde;o*:<input name = 'largobano' className = 'form-control' type = 'number' min='2' max='2.5' placeholder = 'largo en metros del baño*' step = '0.01' required/>
                            </div>
                        </div>
                    
                    <div className='row justify-content-center'>
                        <div className = 'col-lg-3 text-center'>
                            <p></p>
                            <input type='submit' className = 'btn btn-light' value='confirmar'/>
                        </div>
                    </div>
                </form>
            </Fragment>
        )
    }

    handleSubmit = async (e) =>{
        e.preventDefault()
        document.getElementById('ancho')
        document.getElementById('largo')
        this.ancho = document.getElementById('ancho').value
        this.largo = document.getElementById('largo').value
        this.pisos = document.getElementById('pisos').value
        this.num = document.getElementById('habitaciones').value
        this.ladrillo = document.getElementById('tipoladrillo').value
        await ReactDOM.render(
            this.habitacion(), document.getElementById('div')
        )
        this.numHabitaciones(this.num)
    }

    handleNumHabitaciones = async (e) =>{
        e.preventDefault()
        let tam=document.getElementsByTagName('input').length
        for(let i=0;i<tam-1;i++){
            this.dim[document.getElementsByTagName('input')[i].name]=document.getElementsByTagName('input')[i].value
        }
        this.dim['anchoterreno']=this.ancho
        this.dim['largoterreno']=this.largo
        this.dim['pisos'] = this.pisos
        this.dim['tipoladrillo'] = this.ladrillo
        this.dim['idArquitecto'] = this.arquitecto
        this.totalTerreno = (this.dim['anchoterreno'])*(this.dim['largoterreno'])
        this.metros2 = ((this.dim['anchobano'])*(this.dim['largobano']))+((this.dim['ancholavado'])*(this.dim['largolavado']))+
                        ((this.dim['anchococina'])*(this.dim['largococina']))+((this.dim['anchoHabitacion1'])*(this.dim['largoHabitacion1']))
        if(this.dim['anchoHabitacion2']!=null)
        this.metros2= this.metros2+(this.dim['anchoHabitacion2']*this.dim['largoHabitacion2'])
        console.log(this.totalTerreno)
        if(this.totalTerreno>=40 && this.totalTerreno<=71){
            console.log(this.metros2)
            let resto = this.totalTerreno-this.metros2
            console.log(resto)
            this.dim['anchoSala']=Math.floor(Math.sqrt(resto))
            this.dim['largoSala']=Math.floor(Math.sqrt(resto))
            if((this.dim['anchoSala']*this.dim['largoSala'])>=(this.dim['anchoHabitacion1']*this.dim['largoHabitacion1'])){
                this.metros2=this.metros2 + (this.dim['largoSala']*this.dim['anchoSala'])
                if(this.totalTerreno>=this.metros2){
                    this.enviar=true
                    console.log(this.dim['largoSala'])
                }else{
                    console.log(this.metros2)
                    alert('La suma de las medidas ingresadas de las habitaciones sobrepasa los metros cuadrados del terreno, favor de ingresar las medidas correctas.')
                    this.enviar=false   
                }
            }else{
                alert('Las medidas de la Sala comedor son deben ser de al menos el tama\u00f1o de una habitaci\u00F3n')
                this.enviar=false
            }
        }else{
            alert('Las metros cuadrados del terreno sobrepasan las medidas de una casa habitaci\u00f3n de inter\u00e9s  social, por favor ingresa medidas de entre  40 y 71 metros cuadrados.')
            this.enviar=false
        }
        console.log(this.enviar)
        let cancelar = true
        do{
            let confirmar = prompt("Por favor ingrese el nombre de la consulta");
            if(confirmar != null){
                if(confirmar.trim()!==""){
                    this.dim['nombre']=confirmar
                    if(this.enviar)
                        this.res = await this.ArquitectoService.consulta(this.dim)
                    console.log(this.res)
                    if(this.res.length!==0){
                        cancelar=false
                        ReactDOM.render(this.consulta(this.res.nombre),document.getElementById('div'))
                        this.materia(this.res)
                    }else{
                        alert("Estamos teniendo problemas para generar tu consulta, el nombre de la consulta ya existe")   
                    }
                }else{
                    alert("No se permiten espacios en blanco.")
                }
            }else{
                cancelar = false
                window.location.reload()
            }
        }while(cancelar)    }
    handleEliminarConsulta= async (e)=>{
        e.preventDefault()
        let delConsulta={}
        delConsulta["arquitecto"]=this.props.arquitecto
        delConsulta["nombre"]=e.target.name
        if(window.confirm("Esta seguro que deseas eliminar la consulta")){
            this.res = await this.ArquitectoService.eliminarConsulta(delConsulta)
            if(this.res==="True"){
                window.location.reload()
            }else{
                alert("En este momento estamos teniendo problemas para eliminar tu consulta")
            }
        }
    }
    render(){
        const{arquitecto} = this.props
        this.arquitecto = arquitecto
        return(<Fragment>
            <div id='medidasTerreno'>   
                <form className = '' onSubmit = {this.handleSubmit}>
                    <div className='row justify-content-center'>
                        <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>
                            Ancho en metros en metros del terreno*:<input id = 'ancho' className = 'form-control' type = 'number' min='4' max='18' step = '0.01' placeholder = 'ancho en metros en metros del terreno*' required/>
                        </div>
                        <div className = 'col-lg-4 col-md-6 col-sm-6 col-6 text-center'>
                            Largo en metros en metros del terreno*<input id = 'largo' className = 'form-control' type = 'number' min='4' max='18' step = '0.01' placeholder = 'largo en metros en metros del terreno*' required/>
                        </div>
                    </div>
                    <p></p>

                    <div className='row justify-content-center'>
                        <div className = 'col-lg-3 col-md-4 col-sm-4 col-4 text-center'>
                            <p className =''>N&uacute;mero de cuartos</p>
                            <select className= 'custom-select' id = 'habitaciones'>
                                <option value = '1'>1</option>
                                <option value = '2'>2</option>
                            </select>
                        </div>
                        <div className='col-lg-3 col-md-4 col-sm-4 col-4 text-center'>
                            <p className = '' >N&uacute;mero de pisos</p>
                            <select className= 'custom-select' id = 'pisos'>
                                <option value = '1'>1</option>
                                <option value = '2'>2</option>
                            </select>    
                        </div>
                        <div className='col-lg-3 col-md-4 col-sm-4 col-4 text-center'>
                            <p className = '' >Tipo de ladrillo</p>
                            <select className= 'custom-select' id = 'tipoladrillo'>
                                <option value = '1'>Rojo</option>
                                <option value = '2'>block</option>
                            </select>    
                        </div>
                    </div>

                    <p></p>
                    <div className='row justify-content-center'>
                        <div className = 'col-lg-3 text-center'>
                            <input className = 'btn btn-light' type = 'submit' value = 'confirmar'/>
                        </div>
                    </div>
                    <p> </p>
                </form>
            </div>
            <div className = '' id ='medidas'>
                <div className = '' id = 'habitacion'>
                </div>
            </div>
        </Fragment>)
    }
    
}

export default FormularioMedidas