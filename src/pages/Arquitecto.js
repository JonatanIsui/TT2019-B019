import React from 'react'
import { withRouter } from "react-router";
import Login from '../pages/Login'
import ArquitectoService from '../service/ArquitectoService'
import ReactDOM from 'react-dom';
import FormularioMedidas from '../components/FormularioMedidas'
import MostrarProveedores from '../components/MostrarProveedores'
import MostartConsulta from '../components/MostrarConsulta'
import PerfilArquitecto from '../components/PerfilArquitecto'

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
                        <th className = ''>Art&iacute;culo</th>
                        <th className = ' '>Definici&oacute;n</th>
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
                ReactDOM.render(<div class="text-center"><h2><p>En este momento no hay definiciones agregadas</p></h2></div>,document.getElementById("div"))
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

    handleProveedores=async (e) =>{
        e.preventDefault()
        let proveedores={}
        proveedores = await this.ArquitectoService.allProveedores()
        if(proveedores.length!==0){
            ReactDOM.render(<MostrarProveedores
                proveedores = {proveedores}
            />,document.getElementById("div"))
        }else{
            ReactDOM.render(<p>En este momento no se pueden mostrar los proveedores</p>,document.getElementById("div"))
        }
    }

    handleVerConsultaGuardada=async(e)=>{
        e.preventDefault()
        let aux={}
        let consultas={}
        aux["arquitecto"]=this.idArquitecto
        consultas=await this.ArquitectoService.consultasGuardas(aux)
        if(consultas.length!==0){
            ReactDOM.render(<MostartConsulta
                consultas={consultas}
            />,document.getElementById("div"))
        }else{
            ReactDOM.render(<div class="text-center"><h2><p>No se a encontrado ninguna consulta</p></h2></div>,document.getElementById("div"))
        }
    }

    perfil=async(e)=>{
        e.preventDefault()
        let perfil={}
        let aux={}
        aux["id"]=this.idArquitecto
        perfil=await this.ArquitectoService.perfilUsuario(aux)
        ReactDOM.render(<PerfilArquitecto
            usuario={perfil}
        />,document.getElementById("div"))
    }

    render(){
        if(localStorage.getItem('arquitecto')==='true'){
            const id = JSON.parse(atob(this.props.match.params.id))
            this.idArquitecto = id.id
            return(
                <div>
                    <nav className="navbar navbar navbar-expand-md bg-light text-muted">
                        <div className='container-fluid'>
                            <h1 className = ''>Bienvenido {id.nombre}</h1>
                            <ul className="navbar-nav ">

                                <li className="nav-item">
                                    <button className = 'btn btn-light' onClick={this.handleFormularioMedidas}>Nueva consulta</button>
                                </li>

                                <li className="nav-item">
                                <button className = 'btn btn-light' onClick={this.handleVerConsultaGuardada}>Consultas guardadas</button>
                                </li>

                                <li className="nav-item">
                                    <button className = 'btn btn-light' onClick={this.handleProveedores}>Proveedores</button>
                                </li>

                                <li className="nav-item">
                                    <button className = 'btn btn-light' onClick={this.handleDiccionario}>Diccionario</button>
                                </li>
                                <li className="nav-item">
                                    <button className = 'btn btn-light' onClick={this.perfil}>Perfil</button>
                                </li>
                                <li className="nav-item">
                                    <button className = 'btn btn-light' onClick={this.logetOut}>Cerrar sesi&oacute;n</button>
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
                    <div className = ''>Inicie sesi&oacute;n para ver esta pagina</div>
                    <Login/>
                </div>
            )
        }
    }
}

export default withRouter(Arquitecto)