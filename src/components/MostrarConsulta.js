import React, { Fragment } from 'react'
import ReactDOM from 'react-dom';
import ArquitectoService from '../service/ArquitectoService'
class MostartConsulta extends React.Component{
    ArquitectoService = new ArquitectoService()
    etiquetas = ['Botes de agua de 19L','Arena','Grava','Saco de cemento','Saco de Mortero','Varilla','Ladrillo Rojo','Ladrillo Block Ligero','Ladrillo Block Pesado','Alambre','Varilla armex']
    nombresObjeto=["",'arena','grava','saco','sacoMortero','varilla','ladrilloRojo','ladrilloBlockLigero','ladrilloBloackPesado','alambre','varillaArmex']
    descripciones=["Botes de 19 L","arenaDesc","gravaDesc","sacoDesc","sacoMorteroDesc","varillaDesc","ladrilloRojoDesc","ladrilloBlockLigeroDesc","ladrilloBloackPesadoDesc","alambreDesc","varillaArmexDes"]
    nombresObjetoCosto=["",'arenaCosto','gravaCosto','sacoCosto','sacoMorteroCosto','varillaCosto','ladrilloRojoCosto','ladrilloBlockLigeroCosto','ladrilloBloackPesadoCosto','alambreCosto','varillaArmexCosto']
    handleEliminarConsulta=(consulta)=>async e=>{
        e.preventDefault()
        let delConsulta={}
        let consultas={}
        let aux={}
        let res = {}
        delConsulta["arquitecto"]=consulta.arquitecto.id
        aux["arquitecto"]=consulta.arquitecto.id
        delConsulta["nombre"]=consulta.nombre
        if(window.confirm("Esta seguro que deseas eliminar la consulta")){
            res = await this.ArquitectoService.eliminarConsulta(delConsulta)
            if(res==="True"){
                consultas=await this.ArquitectoService.consultasGuardas(aux)
                if(consultas.length!==0){
                    ReactDOM.render(<MostartConsulta
                        consultas={consultas}
                    />,document.getElementById("div"))
                }else{
                    ReactDOM.render(<p>No se a encontrado ninguna consulta</p>,document.getElementById("div"))
                }
            }
        }
    }

    handleAtras=(id) =>async (e)=>{
        e.preventDefault()
        let consultas = {}
        let aux={}
        aux["arquitecto"]=id
        consultas=await this.ArquitectoService.consultasGuardas(aux)
        if(consultas.length!==0){
            ReactDOM.render(<MostartConsulta
                consultas={consultas}
            />,document.getElementById("div"))
        }else{
            ReactDOM.render(<p>No se a encontrado ninguna consulta</p>,document.getElementById("div"))
        }
    }
    herramientas=()=>{
        return(
            <Fragment>
                <div className='row justify-content-center'>  
                <div className = 'col-lg-10 text-center'>
                <table className = 'table table-hover table-dark'>
                    <thead className = ''>
                        <tr className = ''>
                            <th className = ''>Herramientas recomendadas</th>
                            <th className = ''>Maquinaria recomendada</th>
                            </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Pala</td>
                            <td>Apisonadores para compactación o bailarinas</td>
                        </tr>
                        <tr>
                            <td>Pico</td>
                            <td>Excavadora</td>
                        </tr>
                        <tr>
                            <td>Marro o  mazo</td>
                            <td>Retroexcavadora</td>
                        </tr>
                        <tr>
                            <td>Cizallas</td>
                            <td>Mezcladora de Cemento</td>
                        </tr>
                        <tr>
                            <td>Cincel</td>
                        </tr>
                        <tr>
                            <td>Maceta</td>
                        </tr>
                        <tr>
                            <td>Paleta</td>
                        </tr>
                        <tr>
                            <td>Llana</td>
                        </tr>
                        <tr>
                            <td>Nivel</td>
                        </tr>
                        <tr>
                            <td>Flex&oacute;metro</td>
                        </tr>
                        <tr>
                            <td>Carretilla</td>
                        </tr>
                    </tbody>
                </table>
                </div>
                </div>
            </Fragment>
        )
    }
    consulta = (consulta) =>{
        return(
            <div className='row justify-content-center'>  
                <div className = 'col-lg-10 text-center'>
                <table className = 'table table-hover table-dark'>
                    <thead className = ''>
                        <tr className = ''>
                            <th className = ''>Material</th>
                            <th className=''>Descripcion</th>
                            <th className = ''>Cantidad</th>
                            <th className = ''>Costo promedio</th>
                            <th className = ''>Total</th>
                        </tr>
                    </thead>
                    <tbody className = '' >
                        <tr className = '' id = '0'>
                        </tr>
                        <tr className = '' id = '1'>
                        </tr>
                        <tr className = '' id = '2'>
                        </tr>
                        <tr className = '' id = '3'>
                        </tr>
                        <tr className = '' id = '4'>
                        </tr>
                        <tr className = '' id = '5'>
                        </tr>
                        <tr className = '' id = '6'>
                        </tr>
                        <tr className = '' id = '7'>
                        </tr>
                        <tr className = '' id = '8'>
                        </tr>
                        <tr className = '' id = '9'>
                        </tr>
                        <tr className = '' id = '10'>
                        </tr>
                        <tr className = '' id = 'total'>
                        </tr>
                    </tbody>
                </table>
                </div>
                <div className = 'col-lg-10 text-center' id='11'>
                </div>
                <div className = 'col-lg-10 text-center' id='medidasTerreno'>
                </div>
                <div className = 'col-lg-10 text-center' id='medidasHabitacion'>
                </div>
                <div className = 'col-lg-10 text-center' id='proveedoresSugeridos'>
                </div>
                <div className='container-fluid'>
                    <div className='row justify-content-center'>
                        <div className = 'col-lg-4 text-center'>
                            <button className = 'btn btn-light' onClick={this.handleCorreConsulta(consulta)}>Solicitar por correo</button>
                        </div>
                        <div className = 'col-lg-4 text-center'>
                            <button className = 'btn btn-light' onClick={this.handleAtras(consulta.arquitecto.id)}>Atras</button>
                        </div>
                    </div>
                </div>
            </div>                  
        )
    }

    materia = async (consulta) =>{
        document.getElementById('0').insertAdjacentHTML("beforebegin",
            "<td className = ''>"+this.etiquetas[0]+"</td>"+
            "<td className = ''>"+this.descripciones[0]+"</td>"+
            "<td className = ''>"+consulta.materialConsulta.agua+"</td>"+
            "<td className = ''> de 0 a 15 mil litros de agua son $44.94</td>"+
            "<td className = ''>$44.94</td>"  
        )
        for(let i = 1;i<this.nombresObjeto.length;i++){
            document.getElementById(i.toString()).insertAdjacentHTML("beforebegin",
                "<td className = ''>"+this.etiquetas[i]+"</td>"+
                "<td className = ''>"+consulta.materialConsulta[this.descripciones[i]]+"</td>" +
                "<td className = ''>"+consulta.materialConsulta[this.nombresObjeto[i]]+
                "<td className = ''> $"+(consulta.materialConsulta[this.nombresObjetoCosto[i]]).toFixed(2)+"</td>"+
                "<td className = ''>$"+(consulta.materialConsulta[this.nombresObjeto[i]]*consulta.materialConsulta[this.nombresObjetoCosto[i]]).toFixed(2)+"</td>"
        )}
        let totalpromedio = consulta.totalConsulta
        document.getElementById('total').insertAdjacentHTML("beforebegin",
            "<td className = '' colspan='3'>Promedio costo de construci&oacute;n</td>"+
            "<td className = ''>$"+totalpromedio.toFixed(2)+"</td>"
        )
        await ReactDOM.render(this.datosHabitaciones(consulta),document.getElementById("medidasHabitacion"))
        await ReactDOM.render(this.datosTerreno(consulta),document.getElementById("medidasTerreno"))
        await ReactDOM.render(this.datosProveedor(consulta.proveedorConsulta),document.getElementById("proveedoresSugeridos"))
        ReactDOM.render(this.herramientas(),document.getElementById('11'))
    }

    datosProveedor=(datosProveedor)=>{
        return(<Fragment>
            <table className = 'table table-hover table-dark'>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Proveedor recomendado</th>
                        <th className = ''>Correo</th>
                        <th className = ''>Teléfono</th>
                        <th className = ''>Dirección</th>
                    </tr>
                </thead>
                <tbody className = '' >
                    { 
                        datosProveedor.map((item)=>{   
                        return(
                            <tr className = '' key = {item.nombreProveedor}>
                                <td>{item.nombreProveedor}</td>
                                <td>{item.correoProveedor}</td>
                                <td>{item.telefonoProveedor}</td>
                                <td>{item.direccionProveedor}</td>
                            </tr>
                            )
                        })
                    }
                </tbody>
            </table>
            </Fragment>)
    }
    datosHabitaciones=(consulta)=>{
        return(
           <Fragment>
               <table className = 'table table-hover table-dark'>
                    <thead className = ''>
                        <tr className = ''>
                            <th className = ''>Nombre de la habitaci&oacute;n</th>
                            <th className = ''>Ancho de la habitaci&oacute;n</th>
                            <th className = ''>Largo de la habitaci&oacute;n</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Habitaci&oacute;n 1</td>
                            <td>{consulta.anchoHabitacion1}</td>
                            <td>{consulta.largoHabitacion1}</td>
                        </tr>
                        {consulta.anchoHabitacion2!==0 &&
                            <tr>
                                <td>Habitaci&oacute;n 2</td>
                                <td>{consulta.anchoHabitacion2}</td>
                                <td>{consulta.largoHabitacion2}</td>
                            </tr>
                        }
                        <tr>
                            <td>Cocina</td>
                            <td>{consulta.anchococina}</td>
                            <td>{consulta.largococina}</td>
                        </tr>
                        <tr>
                            <td>Cuarto de Lavado</td>
                            <td>{consulta.anchoLavado}</td>
                            <td>{consulta.largoLavado}</td>
                        </tr>
                        <tr>
                            <td>Baño</td>
                            <td>{consulta.anchobano}</td>
                            <td>{consulta.largobano}</td>
                        </tr>
                        <tr>
                            <td>Sala comedor</td>
                            <td>{consulta.anchoSala}</td>
                            <td>{consulta.largoSala}</td>
                        </tr>
                    </tbody>
               </table>
           </Fragment> 
        )
    }
    datosTerreno(consulta){
        return(
            <Fragment>
                <table className = 'table table-hover table-dark'>
                    <tbody>
                        <tr>
                            <td>Dimenciones del terreno</td>
                            <td>Ancho del terreno: {consulta.anchoterreno} m</td>
                            <td>Largo del terreno: {consulta.largoterreno} m</td>
                        </tr>
                        <tr>
                            <td>N&uacute;mero de cuartos:</td>
                            {consulta.anchoHabitacion2!==0 ?
                                <td>2</td>:<td>1</td>
                            }
                        </tr>
                        <tr>
                            <td>N&uacute;mero de pisos:</td>
                            <td>{consulta.pisos}</td>
                        </tr>
                        <tr>
                            <td>Tipo de ladrillo:</td>
                            <td>{consulta.tipoladrillo}</td>
                        </tr>
                    </tbody>
                </table>
            </Fragment>
        )
    }

    handleCorreConsulta=(consulta)=>async e=>{
        e.preventDefault()
        let res={}
        let aux={}
        aux["nombre"]=consulta.excel
        aux["arquitecto"]=consulta.arquitecto.id
        res = await this.ArquitectoService.consultaPorCorreo(aux)
        if(res==="True"){
            alert("Recibirá un correo con su consulta")
        }
    }
    handleMostarConsulta=(consulta)=>async e=>{
        e.preventDefault()
        await ReactDOM.render(this.consulta(consulta),document.getElementById('div'))
        this.materia(consulta)
    }
    
    render(){
        return(<Fragment>
                <table className = 'table table-hover table-dark table-responsive align-middle'>
                    <thead className = ''>
                        <tr className = ''>
                            <th className = 'text-center align-middle'>Fecha de la consulta</th>
                            <th className = 'text-center align-middle'>Nombre de la consulta</th>
                            <th className = 'text-center align-middle'></th>
                            <th className = 'text-center align-middle'></th>
                        </tr>
                    </thead>
                    <tbody className = '' >
                        {
                            this.props.consultas.map((consulta)=>{
                                return(
                                    <tr className = 'text-center' key = {consulta.id}>
                                        <td className = 'text-center align-middle' >{consulta.fechaConsulta}</td>
                                        <td className = 'text-center align-middle' >{consulta.nombre}</td>
                                        <td className = 'text-center align-middle'><button className = 'btn btn-light' onClick = {this.handleEliminarConsulta(consulta)} >Eliminar</button></td>
                                        <td className = 'text-center align-middle'><button className = 'btn btn-light' onClick = {this.handleMostarConsulta(consulta)} >Mas informaci&oacute;n</button></td>
                                    </tr>
                                )
                            })
                        }
                   </tbody>
                </table>
        </Fragment>)
    }
}
export default MostartConsulta