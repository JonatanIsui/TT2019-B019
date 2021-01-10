import React from 'react'
import FormularioUsuario from '../components/FormularioUsuario'


class RegistroUsuario extends React.Component{
    render(){
        return(
            <div className = ''>
                <div className = ''>
                    <FormularioUsuario/>
                </div>

                <div className ='row justify-content-center'>
                    <div className = 'col-lg-4 text-center'>
                        <a href="/"><button type="button" className='btn btn-light'>Cancelar</button></a>
                    </div>
                </div>
                <p> </p>
            </div>
        )
    }
}

export default RegistroUsuario