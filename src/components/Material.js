import React, { Fragment } from 'react'
import ProveedorService from '../service/ProveedorService'
import ReactDOM from 'react-dom'

class Material extends React.Component{
    ProveedorService = new ProveedorService()
    res = []
    handleCatalogo = async (e) =>{
        try{
            this.res = await this.ProveedorService.catalogo(e.target.id)
            console.log(this.res)
        }catch(e){
            console.log(e)
        }
    } 
    
    render(){
        const {id} = this.props
        return(
            <Fragment>
                <button id = {id} onClick ={this.handleCatalogo} className = ''>Ver catalogo</button>
            </Fragment>
        )
    }
}
export default Material