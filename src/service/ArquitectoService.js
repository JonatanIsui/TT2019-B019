import axios from 'axios'
class ArquitectoService{
    baseUrl = 'http://localhost:8080/arquitecto/'
    constructor(){
        this.res = []
    }
    addArquitecto(usuario){
        axios.post(this.baseUrl+'registroArquitecto',usuario).then(resultado =>{
            console.log(resultado.data)
            this.res = resultado.data
        }).catch(e =>{
            console.log(e)
        })
    }
}

export default ArquitectoService