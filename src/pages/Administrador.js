import React from 'react'
import Boton from '../components/Boton'
import MostrarUsuarios from '../components/MostrarUsuarios'
import Diccionario from '../components/Diccionario'
import { withRouter } from 'react-router'

class Administrador extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    render(){
        //Se optiene el objeto con lo datos del login
        const id = JSON.parse(atob(this.props.match.params.id))
        return(
            <div className = ''>
                <h1 className = ''>Bienvenido {id.nombreAdministrador}</h1>
                <div className = ''>
                    <MostrarUsuarios/>
                    <Diccionario/>
                    <Boton
                        text = 'Cerrar sesion'
                        url = '/'
                    />
                </div>
                <div id = 'div' className = ''>
                </div>
            </div>
        )}
    }
export default withRouter(Administrador)