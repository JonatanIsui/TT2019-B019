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
            <div className="row justify-content-center">
                <div className="text-center">
                    <table className = 'table table-hover table-dark table-responsive'>
                        <thead className = ''>
                            <tr className = ''>
                                <th className = ''>Nombre</th>
                                <th className = ''>Categor&iacute;a</th>
                                <th className = ''>Descripci&oacute;n</th>
                                <th className = ''>Costo</th>
                                <th className =''>Clave del pruducto</th>
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
                                        <td className = ''>${item.costo}</td>
                                        <td className = ''>{item.clave}</td>
                                        <td className = 'btn btn-light'><button className = '' onClick = {this.handleEliminarMaterial} name = {item.id}>Eliminar</button></td>
                                        <td className = 'btn btn-light'><button className = '' onClick ={this.handleChageMaterial}  name = {item.id} >Modificar material</button></td>
                                    </tr>
                                )                        
                            })
                        }
                        </tbody>
                    </table>  
                </div>
            </div>                           
        )
    }

    formulario = (proveedor) =>{
        return(
            <Fragment>
            <form className = '' onSubmit = {this.handleSubmit} id = {proveedor}>
                <div className="row justify-content-center">
                    <div className="col-lg-6 col-md-6 col-sm-6 col-6">
                        Nombre:
                        <div className="input-group mb-3 ">
                            <select className="custom-select" name = 'nombre' id="nombre">
                            <option value = 'ladrillo rojo'>Ladrillo rojo</option>
                                <option value = 'ladrillo block ligero'>Ladrillo block ligero</option>
                                <option value = 'ladrillo block pesado'>Ladrillo block pesado</option>
                                <option value = 'cemento'>Cemento</option>
                                <option value = 'mortero'>Mortero</option>
                                <option value = 'grava'>Grava</option>
                                <option value = 'arena'>Arena</option>
                                <option value = 'varilla'>Varilla</option>
                                <option value = 'alambre'>Alambre</option>
                                <option value = 'varilla armex'>Varilla armex</option>
                            </select>
                        </div>


                    </div>
                    <div className="col-lg-6 col-md-6 col-sm-6 col-6">
                        Categor&iacute;a:<input className = 'form-control' type = 'text' name = 'categoria' id='categoriaAddMaterial' placeholder = 'categoria' required/>
                    </div>
                </div>
                <div className="row justify-content-center">
                        <div className="col-lg-6 col-md-6 col-sm-6 col-6">
                            Descripci&oacute;n:
                            <div className="input-group mb-3 ">
                                <select className="custom-select" name = 'descripcion' id="descripcion">
                                    <option value = 'Ladrillo recocido de 12x10x24 cm'>Ladrillo recocido de 12x10x24 cm</option>
                                    <option value = 'Block ligero 12x20x40 cm'>Block ligero 12x20x40 cm</option>
                                    <option value = 'Block pesado 12x20x40 cm'>Block pesado 12x20x40 cm</option>
                                    <option value = 'Saco de cemento 50k'>Saco de cemento 50k</option>
                                    <option value = 'Saco de mortero 50k'>Saco de mortero 50k</option>
                                    <option value = 'Bote de 19 L de grava de 3/4'>Bote de 19 L de grava de 3/4</option>
                                    <option value = 'Bote de arena de 19 L'>Bote de arena de 19 L</option>
                                    <option value = 'Varilla de 3/8 de 12 m de largo'>Varilla de 3/8 de 12 m de largo</option>
                                    <option value = 'Metros de alambre recocido'>Metros de alambre recocido</option>
                                    <option value = 'Tramo de 6 m'>Tramo de 6 m</option>
                                </select>
                            </div>
                        </div>
                        <div className="col-lg-6 col-md-6 col-sm-6 col-6">
                            Costo:<input className = 'form-control' type = 'text' name = 'costo' id='costoAddMaterial' placeholder = 'costo' required/>
                        </div>
                </div>
                <div className = "row justify-content-center">
                    <div className="col-lg-6 text-center">
                        <p></p>
                        <input className = 'form-control' type = 'text' name = 'clave' id='claveAddMaterial' placeholder = 'clave del producto' required/>
                    </div>   
                </div>
                <div className="row justify-content-center">
                    <div className="col-lg-3 text-center">
                        <input className = 'btn btn-light' type = 'submit'/>
                    </div>
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
                    <div className="row">
                        <div className="col-lg-6 text-center">
                            Nombre:<p className='' id='nombreMaterial'>Nombre del material : {material.nombre}</p>
                        </div>
                        <div className="col-lg-6 text-center">
                            Categor&iacute;a:<input className = 'form-control' type = 'text' name = 'categoria' placeholder = {material.categoria}/>
                        </div>
                    </div>
                    <div className="row">
                            <div className="col-lg-6 text-center">
                                Descripci&oacute;n:
                                    <div className="input-group mb-3 ">
                                        <select className="custom-select" name = 'descripcion' id="descripcion">
                                            <option value = 'Ladrillo recocido de 12x10x24 cm'>Ladrillo recocido de 12x10x24 cm</option>
                                            <option value = 'Block ligero 12x20x40 cm'>Block ligero 12x20x40 cm</option>
                                            <option value = 'Block pesado 12x20x40 cm'>Block pesado 12x20x40 cm</option>
                                            <option value = 'Saco de cemento 50k'>Saco de cemento 50k</option>
                                            <option value = 'Saco de mortero 50k'>Saco de mortero 50k</option>
                                            <option value = 'Bote de 19 L de grava de 3/4'>Bote de 19 L de grava de 3/4</option>
                                            <option value = 'Bote de arena de 19 L'>Bote de arena de 19 L</option>
                                            <option value = 'Varilla de 3/8 de 12 m de largo'>Varilla de 3/8 de 12 m de largo</option>
                                            <option value = 'Metros de alambre recocido'>Metros de alambre recocido</option>
                                            <option value = 'Tramo de 6 m'>Tramo de 6 m</option>
                                        </select>
                                    </div>
                            </div>
                            <div className="col-lg-6 text-center">
                                Costo:<input className = 'form-control' type = 'text' name = 'costo' placeholder = {material.costo}/>
                            </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className="col-lg-3 text-center">
                            <input className = 'btn btn-light' type = 'submit' value='Cambiar'/>
                        </div>
                    </div>
                </form>
            </Fragment>
        )
    }

    handleSubmitChage = async (e) =>{
        try{
            e.preventDefault()
            this.elements=document.forms['actualizar'].elements
            this.aux = document.getElementById('nombreMaterial').innerHTML.split(':')[1]
            await this.setState({
                id : e.target.id,
                nombre: this.aux.substr(1,this.aux.length),
                descripcion: document.getElementById('descripcion').value,
            })
            for(this.i=0;this.i<(this.elements.length)-1;this.i++){
                if(this.elements[this.i].value.replace(/\s/g,'').length === 0){
                    await this.setState(
                        {[this.elements[this.i].name] : this.elements[this.i].placeholder}
                    )
                }else{
                    await this.setState({
                        [this.elements[this.i].name] : this.elements[this.i].value  
                    })
                }
            }
            this.res = await this.ProveedorService.modificar(this.state)
            if(Object.keys(this.res).length !== 0){
                ReactDOM.render(this.actualizar(this.res),document.getElementById("div"))
            }else{
                ReactDOM.render(<div className="col text=center"><h4><p>En este momento no cuenta con un cat&aacute;logo</p></h4></div>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    }

    handleCatalogo =(idMaterial)=> async (e) =>{
        try{
            this.material = {
                id : idMaterial
            }
            this.res = await this.ProveedorService.catalogo(this.material)
            if(Object.keys(this.res).length !== 0){
                ReactDOM.render(this.actualizar(this.res),document.getElementById("div"))
            }else{
                ReactDOM.render(<div className="text-center"><h4><p>En este momento no cuenta con un cat&aacute;logo</p></h4></div>,document.getElementById("div"))
            }
        }catch(e){
            console.log(e)
        }
    } 
    
    handleAddMaterial = (id)=>(e) =>{
        try{
            e.preventDefault()
            ReactDOM.render(this.formulario(id),document.getElementById("div"))
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

    handleSubmit = async (e) =>{
        try{
            e.preventDefault()
            this.proveedor = {
                id : e.target.id
            }
            await this.setState({
                proveedor : this.proveedor,
                nombre: document.getElementById('nombre').value,
                categoria: document.getElementById('categoriaAddMaterial').value,
                descripcion: document.getElementById('descripcion').value,
                costo: document.getElementById('costoAddMaterial').value,
                clave: document.getElementById('claveAddMaterial').value
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
    handleEliminarCatalogo=(id)=>async (e)=>{
        try{
            let aux={}
            aux["id"]=id
            let res={}
            if(window.confirm("Esta seguro de eliminar su catalogo?")){
                res = await this.ProveedorService.eliminarCatalogo(aux)
                alert(res)
                window.location.reload()
            }
        }catch(e){
            console.log(e)
        }
    }
    render(){
        const {id} = this.props
        return(
            <Fragment>
                <button onClick ={this.handleCatalogo(id)} className = 'btn btn-light'>Ver cat&aacute;logo</button>
                <button onClick ={this.handleAddMaterial(id)} className = 'btn btn-light'>Agregar material</button>
                <button onClick ={this.handleEliminarCatalogo(id)} className = 'btn btn-light'>Eliminar cat&aacute;logo</button>
            </Fragment>
        )
    }
}
export default Material