import React from 'react';
import { withRouter } from "react-router";
import Boton from '../components/Boton'
import Material from '../components/Material'
import ProveedorService from '../service/ProveedorService'
class Proveedor extends React.Component{
    state = {
        errors : {},
        sending : false
    }
    proveedorService = new ProveedorService()
    res = []
    handleFile = async e =>{
        e.preventDefault()
        const reader = new FileReader();
        if(Object.keys(e.target.files).length){
            await this.setState({
                [e.target.name] : e.target.files[0]
            })
            reader.readAsDataURL(this.state.catalogo)
            reader.onload = async t =>{
                await this.setState({
                    [e.target.name] : t.target.result
                })
            }
        }
    }
    
    handleSubmit = async e =>{
        try{
            e.preventDefault()
            await this.setState({
                id : e.target.id
            })
            this.res = await this.proveedorService.archivo(this.state)
            console.log(this.state)
        }catch(e){
            console.log(e)
        }
    }
    
    render(){
    const id = JSON.parse(atob(this.props.match.params.id))
    return(
        <div className = ''>
            <h1 className = ''>Bienvenido {id.nombreEncargado}</h1>
            <div>
                <Material
                    id = {id.id}
                />
                <Boton
                    text = 'Cerrar sesion'
                    url = '/'
                />
            </div>
            <div id = 'div'>
            </div>  
            <div className = ''>
                <div className = ''>
                    <form  id = {id.id} className = '' onSubmit={this.handleSubmit}>
                        <h1 className = ''>Subir catalogo, solo formato .xlsx</h1>
                        <input type="file" name = 'catalogo' className = '' onChange={this.handleFile} accept=".xlsx" required/>
                        <input type="submit" className = '' value = 'Cargar'/>
                    </form>
                </div>
            </div>  
        </div>
    )}
}
export default withRouter(Proveedor)