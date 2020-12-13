import React from 'react'
import Boton from '../components/Boton'
import { withRouter } from "react-router";
import FormularioLogin from '../components/FormularioLogin'
import ArquitectoService from '../service/ArquitectoService'
import ReactDOM from 'react-dom';
import FormularioMedidas from '../components/FormularioMedidas'

class Arquitecto extends React.Component{
    //Este metodo tiene la tabla que se renderiza
    ArquitectoService = new ArquitectoService()
    res = []
    actualizar = values =>{
        return(
            <table className = ''>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Articulo</th>
                        <th className = ' '>Definicion</th>
                    </tr>
                </thead>
                <tbody className = '' >
                {
                    values.map((item)=>{                                                
                        return(
                            <tr className = '' key = {item.id}>
                                <td className = '' >{item.nombre}</td>
                                <td className = '' >{item.definicion}</td>
                            </tr>
                        )                        
                    })
                }
                </tbody>
            </table>                             
        )
    }
    logetOut = e =>{
        localStorage.removeItem('arquitecto')
        window.location ='/'
    }
    handleDiccionario = async(e) =>{
        e.preventDefault()
        try{
            this.res = await this.ArquitectoService.diccionario()
            if(Object.keys(this.res.length)!==0 && typeof this.res !== 'string'){
                ReactDOM.render(this.actualizar(this.res)       
                ,document.getElementById("div")
                )
            }else{
                ReactDOM.render(<p>En este momento no hay definiciones agregadas</p>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    }

    handleFormularioMedidas = async (e) =>{
        e.preventDefault()
        ReactDOM.render(<FormularioMedidas/>,document.getElementById("div"))

    }
    render(){
        if(localStorage.getItem('arquitecto')==='true'){
            const id = JSON.parse(atob(this.props.match.params.id))
            return(
                <div className = ''>
                    <h1 className = ''>Nav Bar arquitecto</h1>
                    <h1 className = ''>Bienvenido {id.nombre}</h1>
                    <button className = '' onClick={this.handleFormularioMedidas}>Nueva medidas</button> 
                    <Boton
                        text = 'Consultas guardadas'
                        url = '/'
                    />
                    <button className = '' onClick={this.handleDiccionario}>Diccionario</button>
                    <button className = '' onClick={this.logetOut}>Cerrar sesion</button>
                    <div className='' id = 'div'>

                    </div>
                </div>
            )
        }else{
            return(
                <div className = ''>
                    <div className = ''>Inicie sesion para ver esta pagina</div>
                    <FormularioLogin/>
                </div>
            )
        }
    }
}

export default withRouter(Arquitecto)