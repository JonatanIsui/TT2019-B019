import axios from 'axios'
export class UsuarioService{
    baseUrl = 'http://localhost:8080/index/'
    constructor(){
        this.res = [];
    }

    login(usuario){
        axios.post(this.baseUrl+'login',usuario).then(resultado =>{
            console.log(resultado.data)
            this.res = resultado.data
        }).catch(e => {
            console.log(e)
        })
        return this.res
    }

    recuperarPassword(email){
        axios.get(this.baseUrl+'recuperarPassword/'+email+'/').then(resultado =>{
            console.log(resultado.data)
            this.res = resultado.data
        }).catch(e =>{
            console.log(e)  
        })
        return this.res
    }
}
