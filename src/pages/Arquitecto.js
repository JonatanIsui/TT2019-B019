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
    idArquitecto = 0
    actualizar = values =>{
        return(
            <table className = 'table table-hover table-dark'>
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
        ReactDOM.render(<FormularioMedidas
            arquitecto = {this.idArquitecto}
        />,document.getElementById("div"))

    }
    render(){
        if(localStorage.getItem('arquitecto')==='true'){
            const id = JSON.parse(atob(this.props.match.params.id))
            this.idArquitecto = id.id
            return(
                <div>
                <nav className="navbar navbar-expand-sm bg-light">
                    <div className='container-fluid'>
                    <ul className="navbar-nav">

                        <li className="nav-item">
                            <button className = 'btn btn-light' onClick={this.handleFormularioMedidas}>Nueva medidas</button>
                        </li>

                        <li className="nav-item">
                            <Boton
                                text = 'Consultas guardadas'
                                url = '/'
                            />
                        </li>

                        <li className="nav-item">
                            <button className = 'btn btn-light' onClick={this.handleDiccionario}>Diccionario</button>
                        </li>
                        
                        <li className="nav-item">
                            <button className = 'btn btn-light' onClick={this.logetOut}>Cerrar sesion</button>
                        </li>
                    </ul>
                    </div>
                </nav> 
                
                <div className=''>
                <div className='' id = 'div'></div>
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