import React from 'react'
import FormularioProveedor from '../components/FormularioProveedor'

class RegistroProveedor extends React.Component{

    render(){
        return(
            <div className = ''>
                <div className = ''>
                    <FormularioProveedor/>
                </div>

                <div className ='row row justify-content-center'>
                    <div className = 'col-lg-4 text-center'>
                        <a href="/"><button type="button" className='btn btn-light'>Cancelar</button></a>
                    </div>
                </div>

                
            </div>
        )
    }

}

export default RegistroProveedor