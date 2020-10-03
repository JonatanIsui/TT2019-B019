import React from 'react';
import { Link } from 'react-router-dom';
class Nuevacontra extends React.Component{
    constructor(){
        super();
        this.state={
            email:"",
            erro:null,
            sending:false
        }
    }
    render(){
        return(<div>
            <h3>Recupera tu contraseña</h3>
            <form>
                <div>
                    <input value={this.state.value} placeholder="Correo electrónico" type="email" id="email" name="email"/>
                </div>
                <div>
                    <input type="submit" value="Enviar"/>
                </div>
            </form>
            <button><Link to="/">Cancelar</Link></button>
            <p>Se le enviará un correo con la liga de recuperación.</p>
        </div>
    )};
}
export default Nuevacontra