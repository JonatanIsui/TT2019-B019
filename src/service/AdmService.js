import axios from 'axios'
class AdmService{
    baseUrl = 'http://localhost:8080/administrador/'
    constructor (){
        this.res = []
    }
    
    allArquitectos = async () =>{
        try{
            const resultado = await axios.post(this.baseUrl+'arquitectos')
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    allProveedores = async () =>{
        try {
            const resultado = await axios.post(this.baseUrl+'proveedores')
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    allSolicitudes = async () =>{
        try{
            const resultado = await axios.post(this.baseUrl+'solicitudes')
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    eliminarUsuario = async (e) =>{
        try{
            const resultado = await axios.delete(this.baseUrl+'eliminar/'+e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    rechazarSolicitud = async (e) =>{
        try{
            const resultado = await axios.delete(this.baseUrl+'rechazarProveedor/'+e+'/')
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    aceptarSolicitud = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'aceptarProveedor/'+e+'/')
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }
    
    allDefiniciones = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'diccionario')
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    addDefinicion = async (e) =>{
        try{
            const resultado =  await axios.post(this.baseUrl+'agregarDefinicion',e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    eliminarDefinicion = async (e) =>{
        try{
            const resultado = await axios.delete(this.baseUrl+'eliminarDefinicion/'+e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }
}
export default AdmService