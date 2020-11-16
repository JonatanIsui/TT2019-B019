import React from 'react'
import FormularioRecuperarPassword from '../components/FormularioRecuperarPassword'
import Boton from '../components/Boton'

class CambioPassword extends React.Component{
    render(){
        return(
            <div className = ''>
                <div className = ''>
                    <FormularioRecuperarPassword/>
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

export default CambioPassword