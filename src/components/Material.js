import React, { Fragment } from 'react'
import ProveedorService from '../service/ProveedorService'
import ReactDOM from 'react-dom'

class Material extends React.Component{
    
    state = {
        errors: {}
    }
    ProveedorService = new ProveedorService()
    res = []

    actualizar = values =>{
        return(
            <table className = ''>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Nombre</th>
                        <th className = ''>Categoria</th>
                        <th className = ''>Descripcion</th>
                        <th className = ''>Costo</th>
                        <th className = ''></th>
                        <th className = ''></th>
                    </tr>
                </thead>
                <tbody className = '' >
                {
                    values.map((item)=>{                                                
                        return(
                            <tr className = '' key = {item.id}>
                                <td className = '' >{item.nombre}</td>
                                <td className = ''>{item.categoria}</td>
                                <td className = ''>{item.descripcion}</td>
                                <td className = ''>{item.costo}</td>
                                <td className = ''><button className = '' onClick = {this.handleEliminarMaterial} name = {item.id}>Eliminar</button></td>
                                <td className = ''><button className = '' onClick ={this.handleChageMaterial}  name = {item.id} >Modificar material</button></td>
                            </tr>
                        )                        
                    })
                }
                </tbody>
            </table>                             
        )
    }

    formulario = (proveedor) =>{
        return(
            <Fragment>
                <form className = '' onSubmit = {this.handleSubmit} id = {proveedor}>
                    <div className = ''>
                        <input className = '' type = 'text' name = 'nombre' placeholder = 'nombre' onChange = {this.handleChage} required/>
                    </div>
                    <div className = ''>
                        <input className = '' type = 'text' name = 'categoria' placeholder = 'categoria' onChange = {this.handleChage} required/>
                    </div>
                    <div className = ''>
                        <input className = '' type = 'text' name = 'descripcion' placeholder = 'descripcion' onChange = {this.handleChage} required/>
                    </div>
                    <div className = ''>
                        <input className = '' type = 'text' name = 'costo' placeholder = 'costo' onChange = {this.handleChage} required/>
                    </div>
                    <div className = ''>
                        <input className = '' type = 'submit'/>
                    </div>
                </form>
            </Fragment>
        )
    }

    formularioActualizar = (material) =>{
        return(
            <Fragment>
                <div className = '' id = 'alerta'></div>
                <form className = '' onSubmit = {this.handleSubmitChage} id = {material.id} name = 'actualizar'>
                    <div className = ''>
                        <input className = '' type = 'text' id = 'nombre' name = 'nombre' placeholder = {material.nombre}/>
                    </div>
                    <div className = ''>
                        <input className = '' type = 'text' id = 'categoria' name = 'categoria' placeholder = {material.categoria}/>
                    </div>
                    <div className = ''>
                        <input className = '' type = 'text' id = 'descripcion' name = 'descripcion' placeholder = {material.descripcion}/>
                    </div>
                    <div className = ''>
                        <input className = '' type = 'text' id = 'costo' name = 'costo' placeholder = {material.costo}/>
                    </div>
                    <div className = ''>
                        <input className = '' type = 'submit' value='Cambiar'/>
                    </div>
                </form>
            </Fragment>
        )
    }

    handleSubmitChage = async (e) =>{
        try{
            e.preventDefault()
            this.elements=document.forms['actualizar'].elements
            await this.setState({
                id : e.target.id
            })
            for(this.i=0;this.i<(this.elements.length)-1;this.i++){
                if(this.elements[this.i].value.replace(/\s/g,'').length === 0){
                    await this.setState(
                        {[this.elements[this.i].id] : this.elements[this.i].placeholder}
                    )
                }else{
                    await this.setState({
                        [this.elements[this.i].id] : this.elements[this.i].value  
                    })
                }
            }
            this.res = await this.ProveedorService.modificar(this.state)
            if(Object.keys(this.res).length !== 0){
                ReactDOM.render(this.actualizar(this.res),document.getElementById("div"))
            }else{
                ReactDOM.render(<p>En este momento no cuenta con un catalogo</p>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    }

    handleCatalogo = async (e) =>{
        try{
            this.material = {
                id : e.target.name
            }
            this.res = await this.ProveedorService.catalogo(this.material)
            if(Object.keys(this.res).length !== 0){
                ReactDOM.render(this.actualizar(this.res),document.getElementById("div"))
            }else{
                ReactDOM.render(<p>En este momento no cuenta con un catalogo</p>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    } 
    
    handleAddMaterial = (e) =>{
        try{
            e.preventDefault()
            ReactDOM.render(this.formulario(e.target.name),document.getElementById("div"))
        }catch(e){
            console.log(e)
        }
    }

    handleChageMaterial = async (e) =>{
        try{
            e.preventDefault()
            this.material = this.res.find(element => (element.id === parseInt(e.target.name)))
            if(Object.keys(this.material).length !==0){
                ReactDOM.render(this.formularioActualizar(this.material),document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    }

    handleEliminarMaterial = async (e) =>{
        try{
            e.preventDefault()
            this.material = {
                id : e.target.name
            }
            if(window.confirm("Esta seguro que desea eliminar este material de su catalogo")){
                this.res = await this.ProveedorService.eliminar(this.material)
                if(Object.keys(this.res).length !== 0 ){
                    ReactDOM.render(this.actualizar(this.res),document.getElementById("div"))
                }else{
                    ReactDOM.render(<p>En estos momentos no cuenta con un catalogo</p>,document.getElementById("div"))
                }
            }
    
        }catch(e){
            console.log(e)
        }
    }

    handleChage = (e) =>{
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    handleSubmit = async (e) =>{
        try{
            e.preventDefault()
            this.proveedor = {
                id : e.target.id
            }
            await this.setState({
                proveedor : this.proveedor
            })
            this.res = await this.ProveedorService.agregar(this.state)
            if(Object.keys(this.res).length !== 0){
                ReactDOM.render(this.actualizar(this.res),document.getElementById("div"))
            }else{
                ReactDOM.render(<p>En estos momentos no se puede agregar el material</p>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    }

    render(){
        const {id} = this.props
        return(
            <Fragment>
                <button name = {id} onClick ={this.handleCatalogo} className = ''>Ver catalogo</button>
                <button name = {id} onClick ={this.handleAddMaterial} className = ''>Agregar material</button>
            </Fragment>
        )
    }
}
export default Material