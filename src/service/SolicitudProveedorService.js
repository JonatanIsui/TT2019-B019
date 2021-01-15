import axios from 'axios'
class SolicitudProveedorService{
    baseUrl = 'https://b70ca74c4e1e.ngrok.io/solicitudProveedor/'
    constructor(){
        this.res = []
    }
    addSolicitudProveedor = async (usuario) =>{
        try{
            const respuesta = await axios.post(this.baseUrl+'registroProveedor',usuario)
            const data = await respuesta.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }
}

export default SolicitudProveedorService