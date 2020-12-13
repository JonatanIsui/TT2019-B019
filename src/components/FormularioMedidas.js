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
    ladrillo = ''
    ArquitectoService = new ArquitectoService()
    res={}

    numHabitaciones = e =>{
        this.div = document.getElementById('habitacion')
        for(let i = 0; i<e; i++){
            document.getElementById('habitacion').insertAdjacentHTML("beforebegin",
                "<div>"+
                "Habitacion "+(i+1)+""+
                "</div>"+
                "<div>"+
                    "<input name = 'anchoHabitacion"+(i+1)+"' type = 'number' min='1' max='4' placeholder = 'ancho en metros de la habitacion*' step = '0.01' required/>"+
                    "<input name = 'largoHabitacion"+(i+1)+"' className = '' type = 'number' min='1' max='2.8' placeholder = 'largo en metros de la habitacion*' step = '0.01' required/>"+
                "</div>"
           )
        }
    }
    habitacion = () =>{
        return(
            <Fragment>
                <form onSubmit = {this.handleNumHabitaciones}>
                    <div id = 'habitacion'>
                    </div>
                        Cocina
                        <div className = ''>
                            <input name = 'anchococina' className = '' type = 'number' min='1' max='4' placeholder = 'ancho en metros de la cocina*' step = '0.01' required />
                            <input name = 'largococina' className = '' type = 'number' min='1' max='4' placeholder = 'largo en metros de la cocina*' step = '0.1' required/>
                        </div>
                        Cuarto de lavado
                        <div className = ''>
                            <input name = 'ancholavado' className = '' type = 'number' min='1' max='3' placeholder = 'ancho en metros del cuarto del lavado*' step = '0.01' required/>
                            <input name = 'largolavado' className = '' type = 'number' min='1' max='3' placeholder = 'largo en metros del cuarto del lavado*' step = '0.01' required/>
                        </div>
                        Baño
                        <div className = ''>
                            <input name = 'anchobano' className = '' type = 'number' min='1' max='2.5' placeholder = 'ancho en metros del baño*' step = '0.01' required/>
                            <input name = 'largobano' className = '' type = 'number' min='1' max='2' placeholder = 'largo en metros del baño*' step = '0.01' required/>
                        </div>
                    <div>
                        <input type='submit' value='confirmar'/>
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
        this.ladrillo = document.getElementById('ladrillo').value
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
        this.dim['ladrillo'] = this.ladrillo
        this.res = await this.ArquitectoService.consulta(this.dim)
        console.log(this.res)
    }

    render(){
        return(<Fragment>
            <div id='medidasTerreno'>   
                <form className = '' onSubmit = {this.handleSubmit}>
                    <div className = ''>
                        <input id = 'ancho' className = '' type = 'number' min='4' max='18' step = '0.01' placeholder = 'ancho en metros en metros del terreno*' required/>
                    </div>
                    <div className = ''>
                        <input id = 'largo' className = '' type = 'number' min='4' max='18' step = '0.01' placeholder = 'largo en metros en metros del terreno*' required/>
                    </div>
                    <div className = ''>
                        <p className =''>Número de cuartos</p>
                        <select className= '' id = 'habitaciones'>
                            <option value = '1'>1</option>
                            <option value = '2'>2</option>
                        </select>
                    </div>
                    <div>
                        <p className = '' >Número de pisos</p>
                        <select className= '' id = 'pisos'>
                            <option value = '1'>1</option>
                            <option value = '2'>2</option>
                        </select>    
                    </div>
                    <div>
                        <p className = '' >Tipo de ladrillo</p>
                        <select className= '' id = 'ladrillo'>
                            <option value = '1'>Rojo</option>
                            <option value = '2'>block</option>
                        </select>    
                    </div>
                    <div className = ''>
                        <input className = '' type = 'submit' value = 'confirmar'/>
                    </div>
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