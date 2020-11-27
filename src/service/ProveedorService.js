import axios from 'axios'

class ProveedorService{
    baseUrl = 'http://localhost:8080/proveedor/'
    constructor(){
        this.res =[]
    }

    catalogo = async (e) =>{
        try{
            const resultado = await axios.post(this.baseUrl+'catalogo/'+e)
            const data = await resultado.data
            this.res = data
        }catch(e){
            console.log(e)
        }
        return this.res
    }

}
export default ProveedorService