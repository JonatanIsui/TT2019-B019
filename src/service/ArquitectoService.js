import axios from 'axios'
class ArquitectoService{
    baseUrl = 'http://localhost:8080/arquitecto/'
    constructor(){
        this.res = []
    }
    addArquitecto = async (usuario) => {
        try{
            const resultado = await axios.post(this.baseUrl+'registroArquitecto',usuario)
            const data = await resultado.data
            this.res = data
        }catch(e) {
            console.log(e)
        }
        return this.res
    }

    diccionario = async () =>{
        try{
            const resultado = await axios.post(this.baseUrl+'diccionario')
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

    consulta = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'consulta',e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        this.res
    }
}

export default ArquitectoService