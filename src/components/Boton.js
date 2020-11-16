import React from 'react'
import { Link } from 'react-router-dom';

class Boton extends React.Component{
    render(){
        const{text,url} = this.props
        return(
            //Se puede poner la etiqueta img dentro de las etiquetas link
            <Link to = {url}>{text}</Link>
        )
    }
} 

export default Boton