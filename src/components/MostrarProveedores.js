import React, { Fragment } from 'react'

class MostrarProveedores extends React.Component{
    render(){
        return(<Fragment>
            <table className = 'table table-hover table-dark'>
                <thead className = ''>
                    <tr className = ''>
                        <th className = ''>Proveedor</th>
                        <th className = ''>Telefono</th>
                        <th className = ''>Correo electronico</th>
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
        </Fragment>)
    }
}
export default MostrarProveedores