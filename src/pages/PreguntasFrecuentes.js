import React from 'react'
class PreguntasFrecuentes extends React.Component{
    render(){
        return(
            <div className = 'container-fluid p-3 my-3 bg-dark text-white'>    
                <div className = 'row justify-content-center'>
                    <div className = 'col-lg-12 text-center'>
                    <table className = 'table table-hover table-dark'>
                        <thead className = ''>
                            <tr className = ''>
                                <th className = ''>Pregunta</th>
                                <th className = ''>Respuesta</th>
                            </tr>
                        </thead>
                        <tbody className = '' >
                            <tr className = ''>
                                <td>¿Qué formato se debe utilizar para ingresar mis materiales?</td>
                                <td>R= En la página inicial de proveedor se encuentra un botón llamado Formato excel, el cual te descargará el formato de excel listo para que lo llenes con la informacion de tus materiales.</td>
                            </tr>
                            <tr className = ''>
                                <td>¿Cómo se eligen los proveedores recomendados?</td>
                                <td>R= El sistema calcula los materiales necesarios para despues calcular los costos con cada proveedor, de manera que los proveedores recomendados son aquellos que tienen su costo total mas bajo, con esto se busca ocasionar competencia entre los proveedores y obtener precios justos por los materiales.</td>
                            </tr>
                            <tr className = ''>
                                <td>¿Cuáles son los requisitos para poder ser aceptados como proveedor?</td>
                                <td>R= Únicamente ingresar información verídica, esto para evitar hasta estafas hacia los mismos usuarios, nosotros nos apegamos a la Ley Federal de Protección de Datos Personales en Posesión de lo Particulares.</td>
                            </tr>
                            <tr className = ''>
                                <td>¿Los precios en las consultas son garantizados?</td>
                                <td>R= Nosotros no nos hacemos responsables por los precios indicados por los provedores, y estos pueden cambiarlos en el momento en que deseen.</td>
                            </tr>
                            <tr className = ''>
                                <td>¿Se puede utilizar el sistema sin tener una cuenta?</td>
                                <td>R= No, ya que teniendo una cuenta podemos brindar un mejor servicio a nuestros usuarios.</td>
                            </tr>
                            <tr className = ''>
                                <td>¿Cuál es al diferencia entre el tipo de ladrillo rojo y block?</td>
                                <td>R= El rojo es ladrillo rojo recocido de 10x12x24, mientras que el block puede ser ligero o pesado con medidas de 12x20x40.</td>
                            </tr>
                            <tr className = ''>
                                <td>¿Porque no puedo ingresar las medidas de un terreno mas grande?</td>
                                <td>R= Este prototipo de sistema solo calcula materiales y costos de una casa habitación de interés social, es decir de terrenos de entre  40 y 71 metros cuadrados.</td>
                            </tr>
                            <tr className = ''>
                                <td>¿Puedo calcular los materiales de una casa con mas de 2 habitaciones?</td>
                                <td>R= No, este prototipo de sistema solo permite calcular los materiales y costos de una casa habitación de interés social, las cuales nos tienen mas de 2 habitaciones. Esto es una futura mejora que podría ser incluida en el futuro.</td>
                            </tr>
                            <tr className = ''>
                                <td>¿Cómo puedo ingresar las medidas de mi sala comedor?</td>
                                <td>R= Las  medidas de la sala comedor no son ingresadas, el sistema calcula los metros cuadrados por cuarto y los sobrantes estan destinados a la sala comedor, se toma en cuenta que la sala comedor debe tener como minimo las medidas  de una habitación.</td>
                            </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        )
    }
}
export default PreguntasFrecuentes