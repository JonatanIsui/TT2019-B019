import React from 'react'
import InformacionUsuario from '../components/InformacionUsuario'
import { withRouter } from "react-router";
import Login from '../pages/Login'
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
                    <Login/>
                </div>
            )
        }
    }

}
export default withRouter(EspecificacionUsuarios)