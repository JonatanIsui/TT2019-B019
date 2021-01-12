import axios from 'axios'
class SolicitudProveedorService{
    baseUrl = 'http://localhost:8080/solicitudProveedor/'
    constructor(){
        this.res = []
    }
    addSolicitudProveedor = async (usuario) =>{
        try{
            const respuesta = await axios.post(this.baseUrl+'registroProveedor',usuario)
            console.log(respuesta)
            const data = await respuesta.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }
}

export default SolicitudProveedorService