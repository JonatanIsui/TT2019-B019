import React from 'react';
import { withRouter } from "react-router";
import Material from '../components/Material'
import ProveedorService from '../service/ProveedorService'
import FormularioLogin from '../components/FormularioLogin'
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
            alert('El archivo se subio con exito')
        }catch(e){
            console.log(e)
        }
    }

    handleFormato = async e =>{
        try{
            e.preventDefault()
            this.res = await this.proveedorService.formato()
            var a = document.createElement("a")
            a.href="data:application/pdf;base64,"+this.res
            a.download="formato.xlsx"
            a.click()
        }catch(e){
            console.log(e)
        }
    }
    
    logetOut = e =>{
        localStorage.removeItem('proveedor')
        window.location ='/'
    }
    render(){
    const id = JSON.parse(atob(this.props.match.params.id))
    if(localStorage.getItem('proveedor')==='true'){
        return(
            <div className = ''>
                <h1 className = ''>Bienvenido {id.nombreEncargado}</h1>
                <div>
                    <Material
                        id = {id.id}
                    />
                    <button className = '' onClick={this.handleFormato}>Formato excel</button>
                    <button className = '' onClick={this.logetOut}>Cerrar sesion</button>
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
export default withRouter(Proveedor)