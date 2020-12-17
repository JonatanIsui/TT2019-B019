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
    etiquetas = ['Botes de agua de 19L','Arena','Grava','Saco de cemento','Saco de Mortero','Varilla','Ladrillo Rojo','Ladrillo Block Ligero','Ladrillo Block Pesado']

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
    }
    consulta = () =>{
        return(
            <div className=''>
            <table className = ''>
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
                </tbody>
            </table>
            <div>
                <button >Proveedor recomendado</button>
                <button >Ver todos los proveedores</button>
                <button >Eliminar consulta</button>
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
                        <select className= '' id = 'tipoladrillo'>
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