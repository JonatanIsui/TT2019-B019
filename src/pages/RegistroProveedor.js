import React from 'react'
import FormularioProveedor from '../components/FormularioProveedor'
import Boton from '../components/Boton'

class RegistroProveedor extends React.Component{

    render(){
        return(
            <div className = ''>
                <div className = ''>
                    <FormularioProveedor/>
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

export default RegistroProveedor