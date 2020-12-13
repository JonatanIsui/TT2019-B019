import axios from 'axios'

class ProveedorService{
    baseUrl = 'http://localhost:8080/proveedor/'
    constructor(){
        this.res =[]
    }

    catalogo = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'catalogo',e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    eliminar = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'eliminar',e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    modificar = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'modificar',e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    agregar = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'agregar',e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    archivo = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'archivo',e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    formato = async(e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'formato')
            const data = await resultado.data
            this.res =data
        }catch(e){
            console.log(e)
        }
        return this.res
    }
}
export default ProveedorService