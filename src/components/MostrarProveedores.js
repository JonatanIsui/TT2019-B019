import React, { Fragment } from 'react'

class MostrarProveedores extends React.Component{
    render(){
        return(<Fragment>
            <div className="container-fluid">
                <div className="row justify-content-center "> 
                    <div className="col-lg-6 text-center col-xs-3 col-md-8 col-sm-10">
                        <table className = 'table table-hover table-dark table-responsive'>
                            <thead className = ''>
                                <tr className = ''>
                                    <th className = ''>Proveedor</th>
                                    <th className = ''>Tel&eacute;fono</th>
                                    <th className = ''>Correo electr&oacute;nico</th>
                                    <th className = ''>Direccion</th>
                                </tr>
                            </thead>
                            <tbody className = '' >
                                {
                                    this.props.proveedores.map((proveedor)=>{
                                        return(
                                            <tr className = '' key = {proveedor.id}>
                                                <td className = '' >{proveedor.proveedor.nombreEmpresa}</td>
                                                <td className = ''>{proveedor.proveedor.telefono}</td>
                                                <td className = ''>{proveedor.correo}</td>
                                                <td className = ''>{proveedor.proveedor.direccion}</td>
                                            </tr>
                                        )
                                    })
                                }
                            </tbody>
                        </table> 
                    </div>
                </div>
            </div>
            
        </Fragment>)
    }
}
export default MostrarProveedores