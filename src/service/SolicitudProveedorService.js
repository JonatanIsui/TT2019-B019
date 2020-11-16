import axios from 'axios'
class SolicitudProveedorService{
    baseUrl = 'http://localhost:8080/solicitudProveedor/'
    constructor(){
        this.res = []
    }
    addSolicitudProveedor(usuario){
        axios.post(this.baseUrl+'registroProveedor',usuario).then(resultado =>{
            console.log(resultado.data)
            this.res = resultado.data
        }).catch(e =>{
            console.log(e)
        })
        return this.res
    }
}

export default SolicitudProveedorService