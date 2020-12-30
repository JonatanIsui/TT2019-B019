import React from 'react'
import MostrarUsuarios from '../components/MostrarUsuarios'
import Diccionario from '../components/Diccionario'
import { withRouter } from 'react-router'
import FormularioLogin from '../components/FormularioLogin'

class Administrador extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    logetOut = e =>{
        localStorage.removeItem('administrador')
        window.location ='/'
    }
    render(){
        if(localStorage.getItem('administrador')==='true'){
        //Se optiene el objeto con lo datos del login
            const id = JSON.parse(atob(this.props.match.params.id))
            return(
                <div className = 'container-fluid p-3 my-3 bg-dark text-white'>    
                    <div className = 'row justify-content-center'>
                        <div className = 'col-lg-12 text-center'>
                            <h1 className = ''>Bienvenido {id.nombreAdministrador}</h1>
                        </div>
                    </div>
                    <div className = 'row justify-content-center'>
                        <div className = 'col-lg-12 text-center'>
                            <MostrarUsuarios/>
                            <Diccionario/>
                        </div>
                    </div>
                    <div className = 'row justify-content-center'>
                        <div className = 'col-lg-4 text-center'>
                            <button className = 'btn btn-light' onClick={this.logetOut}>Cerrar sesion</button>
                        </div>
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
export default withRouter(Administrador)