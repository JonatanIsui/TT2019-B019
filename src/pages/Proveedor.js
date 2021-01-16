import React from 'react';
import { withRouter } from "react-router";
import Material from '../components/Material'
import ProveedorService from '../service/ProveedorService'
import Login from '../pages/Login'
import PerfilProveedor from '../components/PerfilProveedor'
import ReactDOM from 'react-dom';

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
            alert(this.res+'. Puedes ver tu catálogo actual dando clic en ver catálogo')
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

    perfilProveedor=(id)=>async e=>{
        e.preventDefault()
        let perfil={}
        let aux={}
        aux["id"]=id.id
        perfil=await this.proveedorService.perfilUsuario(aux)
        ReactDOM.render(<PerfilProveedor
            usuario={perfil}
        />,document.getElementById("div"))
        console.log(perfil)
    }
    render(){
    const id = JSON.parse(atob(this.props.match.params.id))
    if(localStorage.getItem('proveedor')==='true'){
        return(
            <div className = 'container-fluid p-3 my-3 bg-dark text-white'>
        
                <div className="row justify-content-center">
                    <h1 className = ''>Bienvenido {id.nombreEmpresa}</h1>
                </div>
                <div className="row justify-content-center ">
                    <Material
                            id = {id.id}
                        />
                </div>
                <div className="row justify-content-center">
                    <div className='col-lg-4 text-center'>
                        <button className = 'btn btn-light' onClick={this.handleFormato}>Formato excel</button>
                    </div>
                    <div className='col-lg-4 text-center'>
                        <button className = 'btn btn-light' onClick={this.perfilProveedor(id)}>Mi perfil</button>
                    </div>
                    <div className='col-lg-4 text-center'>
                        <button className = 'btn btn-light' onClick={this.logetOut}>Cerrar sesi&oacute;n</button>
                    </div>
                </div>
                <div id = 'div'></div>
                <form  id = {id.id} className = '' onSubmit={this.handleSubmit}>
                    <div className="row justify-content-center text-center">
                        
                            <h1 className = ''>Subir cat&aacute;logo, solo formato .xlsx</h1>
                        
                    </div>
                    <div className="row justify-content-center">
                        <div className='col-lg-4 text-center'>
                            <input type="file" name = 'catalogo' className = 'btn btn-light' onChange={this.handleFile} accept=".xlsx" required/>
                        </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className='col-lg-4 text-center'>
                            <p></p>
                            <input type="submit" className = 'btn btn-light' value = 'Cargar'/>
                        </div>
                    </div>
                </form>
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
export default withRouter(Proveedor)