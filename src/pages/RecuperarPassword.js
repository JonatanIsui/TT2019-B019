import React from 'react'
import FormularioPassword from '../components/FormularioPassword'
import Boton from '../components/Boton'
class RecuperarPassword extends React.Component{
    render(){
        return(
            <div className = ''>
                <div className = ''>  
                    <FormularioPassword/>
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

export default RecuperarPassword