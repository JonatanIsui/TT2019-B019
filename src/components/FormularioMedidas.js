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
    totalpromedio=0
    ArquitectoService = new ArquitectoService()
    res={}
    etiquetas = ['Botes de agua de 19L','Arena','Grava','Saco de cemento','Saco de Mortero','Varilla','Ladrillo Rojo','Ladrillo Block Ligero','Ladrillo Block Pesado']

    numHabitaciones = e =>{
        this.div = document.getElementById('habitacion')
        for(let i = 0; i<e; i++){
            document.getElementById('habitacion').insertAdjacentHTML("beforebegin",
                "<div className='row justify-content-center'>"+
                    "<div className = 'col-lg-8 text-center'>"+
                        "<h3>Habitacion "+(i+1)+"</h3>"+
                    "</div>"+
                "</div>"+

                "<div className='row justify-content-center'>"+
                    "<div className = 'col-lg-4 text-center'>"+
                        "Ancho en metros de la habitacion*:<input name = 'anchoHabitacion"+(i+1)+"' type = 'number' min='1' max='4' placeholder = 'ancho en metros de la habitacion*' step = '0.01' required className = 'form-control'/>"+
                    "</div>"+
                    "<div className = 'col-lg-4 text-center'>"+
                        "Largo en metros de la habitacion*:<input name = 'largoHabitacion"+(i+1)+"'  type = 'number' min='1' max='2.8' placeholder = 'largo en metros de la habitacion*' step = '0.01' required className = 'form-control'/>"+
                    "</div>"+
                "</div>"
           )
        }
    }
    materia = () =>{
        document.getElementById('0').insertAdjacentHTML("beforebegin",
            "<td className = ''>"+this.etiquetas[0]+"</td>"+
            "<td className = ''>"+this.res.agua+"</td>"+
            "<td className = ''> de 0 a 15 mil son $44.94</td>"+
            "<td className = ''>$44.94</td>"  
        )
        for(let i = 1;i<this.etiquetas.length;i++){
            document.getElementById(i.toString()).insertAdjacentHTML("beforebegin",
                "<td className = ''>"+this.etiquetas[i]+"</td>"+
                "<td className = ''>"+Object.values(this.res)[i]+"</td>"+
                "<td className = ''> $"+Object.values(this.res)[i+22]+"</td>"+
                "<td className = ''>$"+Object.values(this.res)[i]*Object.values(this.res)[i+22]+"</td>"
        )}

        for(let i = 1;i<this.etiquetas.length;i++){
            this.totalpromedio = this.totalpromedio+(Object.values(this.res)[i]*Object.values(this.res)[i+22])
        }
        document.getElementById('total').insertAdjacentHTML("beforebegin",
            "<td className = '' colspan='3'>Promedio costo de construcion</td>"+
            "<td className = ''>$"+this.totalpromedio+"</td>"
        )
        this.totalpromedio = 0 
    }
    consulta = () =>{
        return(
            <div className='row justify-content-center'>  
                <div className = 'col-lg-10 text-center'>
                <table className = 'table table-hover table-dark'>
                    <thead className = ''>
                        <tr className = ''>
                            <th className = ''>Material</th>
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
                        <tr className = '' id = 'total'>
                        </tr>
                    </tbody>
                </table>
                </div>
                <div className='container-fluid'>
                    <div className='row justify-content-center'>
                        <div className = 'col-lg-4 text-center'>
                            <button className = 'btn btn-light'>Proveedor recomendado</button>
                        </div>
                        <div className = 'col-lg-4 text-center'> 
                            <button className = 'btn btn-light'>Ver todos los proveedores</button>
                        </div>
                        <div className = 'col-lg-4 text-center'>
                            <button className = 'btn btn-light'>Eliminar consulta</button>
                        </div>
                    </div>
                </div>
            </div>                  
        )
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
            	            <div className = 'col-lg-4 text-center'>
                                Ancho en metros de la cocina*:<input name = 'anchococina' className = 'form-control' type = 'number' min='1' max='4' placeholder = 'ancho en metros de la cocina*' step = '0.01' required />
                            </div>
                            <div className = 'col-lg-4 text-center'>
                                Largo en metros de la cocina*:<input name = 'largococina' className = 'form-control' type = 'number' min='1' max='4' placeholder = 'largo en metros de la cocina*' step = '0.1' required/>
                            </div>
                        </div>


                        <div className='row justify-content-center'>
                            <div className = 'col-lg-8 text-center'>
                                <h3>Cuarto de lavado</h3>
                            </div>
                        </div>

                        <div className = 'row justify-content-center'>
                            <div className = 'col-lg-4 text-center'>
                                Ancho en metros del cuarto del lavado*:<input name = 'ancholavado' className = 'form-control' type = 'number' min='1' max='3' placeholder = 'ancho en metros del cuarto del lavado*' step = '0.01' required/>
                            </div>
                            <div className = 'col-lg-4 text-center'>
                                Largo en metros del cuarto del lavado*:<input name = 'largolavado' className = 'form-control' type = 'number' min='1' max='3' placeholder = 'largo en metros del cuarto del lavado*' step = '0.01' required/>
                            </div>
                        </div>


                        <div className='row justify-content-center'>
                            <div className = 'col-lg-8 text-center'>
                                <h3>Baño</h3>
                            </div>
                        </div>

                        <div className = 'row justify-content-center'>
                            <div className = 'col-lg-4 text-center'>
                                Ancho en metros del baño*:<input name = 'anchobano' className = 'form-control' type = 'number' min='1' max='2.5' placeholder = 'ancho en metros del baño*' step = '0.01' required/>
                            </div>
                            <div className = 'col-lg-4 text-center'>
                                Largo en metros del baño*:<input name = 'largobano' className = 'form-control' type = 'number' min='1' max='2' placeholder = 'largo en metros del baño*' step = '0.01' required/>
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
        for(let i = 1; i< e.target.children.length;i=i+2){
            for(let j = 0; j< e.target.children[i].children.length;j++){
                this.dim[e.target.children[i].children[j].name]=e.target.children[i].children[j].value
            }
        }
        this.dim['anchoterreno']=this.ancho
        this.dim['largoterreno']=this.largo
        this.dim['pisos'] = this.pisos
        this.dim['tipoladrillo'] = this.ladrillo
        this.dim['idArquitecto'] = this.arquitecto
        let confirmar = prompt("Nombre de la consulta");
        if(confirmar != null){
            console.log(confirmar.length)
            this.dim['nombre']=confirmar
            this.res = await this.ArquitectoService.consulta(this.dim)
            ReactDOM.render(this.consulta(),document.getElementById('div'))
            this.materia()
        }else{
            window.location.reload()
        }
    }

    render(){
        const{arquitecto} = this.props
        this.arquitecto = arquitecto
        return(<Fragment>
            
            <div id='medidasTerreno'>   
                <form className = '' onSubmit = {this.handleSubmit}>


                    <div className='row justify-content-center'>
                        <div className = 'col-lg-4 text-center'>
                            Ancho en metros en metros del terreno*:<input id = 'ancho' className = 'form-control' type = 'number' min='4' max='18' step = '0.01' placeholder = 'ancho en metros en metros del terreno*' required/>
                        </div>
                        <div className = 'col-lg-4 text-center'>
                            Largo en metros en metros del terreno*<input id = 'largo' className = 'form-control' type = 'number' min='4' max='18' step = '0.01' placeholder = 'largo en metros en metros del terreno*' required/>
                        </div>
                    </div>
                    <p></p>

                    <div className='row justify-content-center'>
                        <div className = 'col-lg-3 text-center'>
                            <p className =''>Número de cuartos</p>
                            <select className= 'custom-select' id = 'habitaciones'>
                                <option value = '1'>1</option>
                                <option value = '2'>2</option>
                            </select>
                        </div>
                        <div className='col-lg-3 text-center'>
                            <p className = '' >Número de pisos</p>
                            <select className= 'custom-select' id = 'pisos'>
                                <option value = '1'>1</option>
                                <option value = '2'>2</option>
                            </select>    
                        </div>
                        <div className='col-lg-3 text-center'>
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