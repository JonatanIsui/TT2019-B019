import React from 'react'
import InformacionUsuario from '../components/InformacionUsuario'
import { withRouter } from "react-router";

class EspecificacionUsuarios extends React.Component{

    state = {
        errors : {},
        sending : false
    }
    render(){
        return(
            <div className = ''>
                <InformacionUsuario/>
            </div>
        )
    }

}
export default withRouter(EspecificacionUsuarios)