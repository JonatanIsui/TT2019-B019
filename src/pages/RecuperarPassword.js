import React from 'react'
import FormularioPassword from '../components/FormularioPassword'
class RecuperarPassword extends React.Component{
    render(){
        return(
            <div className = ''>
                <div className = ''>  
                    <FormularioPassword/>
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

export default RecuperarPassword