import React from 'react'
import Boton from '../components/Boton'
import { withRouter } from "react-router";

class Arquitecto extends React.Component{
    render(){
        const id = JSON.parse(atob(this.props.match.params.id))
        console.log(id)
        return(
            <div className = ''>
                <h1 className = ''>Nav Bar arquitecto</h1>
                <h1 className = ''>Bienvenido {id.nombre}</h1>
                <Boton
                    text = 'Nueva consulta'
                    url = '/'
                />
                <Boton
                    text = 'Consultas guardadas'
                    url = '/'
                />
                <Boton
                    text = 'Diccionario'
                    url = '/Diccionario'
                />
            </div>
        )
    }
}

export default withRouter(Arquitecto)