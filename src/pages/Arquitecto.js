import React from 'react'
import Boton from '../components/Boton'
import { withRouter } from "react-router";

class Arquitecto extends React.Component{



    render(){
        return(
            <div className = ''>
                <h1 className = ''>Nav Bar arquitecto</h1>
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
                    url = '/'
                />
            </div>
        )
    }
}

export default withRouter(Arquitecto)