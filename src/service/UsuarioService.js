import axios from 'axios'
export class UsuarioService{
    baseUrl = 'http://localhost:8080/index/'
    constructor(){
        this.res = [];
    }

    login = async (usuario) => {
        try{
            const respuesta = await axios.post(this.baseUrl+'login',usuario)
            const data = await respuesta.data
            console.log(data)
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    recuperarPassword = async (email) =>{
        try{
            const respuesta = await axios.get(this.baseUrl+'recuperarPassword/'+email+'/')
            const data = await respuesta.data
            console.log(data)
            this.res = data
        }catch(e){
            console.log(e)  
        }
        return this.res
    }

    cambioPassword = async (usuario) => {
        try{
            const respuesta = await axios.post(this.baseUrl+'cambioPassword',usuario)
            const data = await respuesta.data
            console.log(data)
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }
}
