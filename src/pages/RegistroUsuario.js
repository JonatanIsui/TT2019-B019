import React from 'react'
import FormularioUsuario from '../components/FormularioUsuario'
import Boton from '../components/Boton'

class RegistroUsuario extends React.Component{
    render(){
        return(
            <div className = ''>
                <div className = ''>
                    <FormularioUsuario/>
                </div>
                <div className = ''>
                    <Boton
                        text = 'Cancelar'
                        url = '/'
                    />
                </div>
            </div>
        )
    }
}

export default RegistroUsuario