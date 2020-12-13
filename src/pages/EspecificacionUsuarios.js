import React from 'react'
import InformacionUsuario from '../components/InformacionUsuario'
import { withRouter } from "react-router";
import FormularioLogin from '../components/FormularioLogin'
class EspecificacionUsuarios extends React.Component{

    state = {
        errors : {},
        sending : false
    }
    render(){
        if(localStorage.getItem('administrador')==='true'){
            return(
                <div className = ''>
                    <InformacionUsuario/>
                </div>
            )
        }else{
            return(
                <div className = ''>
                    <div className = ''>Inicie sesion para ver esta pagina</div>
                    <FormularioLogin/>
                </div>
            )
        }
    }

}
export default withRouter(EspecificacionUsuarios)