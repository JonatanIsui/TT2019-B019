import axios from 'axios';
export class UsuarioService{
    baseUrl="http://localhost:8080/inicio/";
    constructor(){
        this.res=[];
    }
    //Metodo  direccion peticion  Promesa 
    getAll(){
        axios.get(this.baseUrl+"all")
        .then(resultado=>{
            this.res=resultado.data;
        }).catch(console.log)
        return this.res;
    }
    autorizarLogin(email,password){
        axios.get(this.baseUrl+"usuario/"+password+"/"+email+"/").then(resultado=>{
            this.res=resultado.data;
        }).catch()
        return this.res;
    }
    usuarioNuevo(usuario){
        axios.post(this.baseUrl+"save",usuario).then(resultado=>{
            this.res=resultado.data;
        }).catch()

    }
}