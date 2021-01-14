package com.tt2.service;
import org.springframework.stereotype.Service;
import com.tt2.model.ConsultaModel;

@Service("consultaBean")
public class ConsultaBean {
	private double agua=0;
    private double arena=0;
    private double grava=0;
    private double saco=0;
    private double sacoMortero=0;
    private double varillas=0;
    private double ladrillos=0;
    private double alambre=0;
    private double blockligero=0;
    private double blockpesado=0;
    private double varillaArmex=0;
    private ConsultaModel consulta=new ConsultaModel();
    
    public void varillasLozas(double ancho, double largo, int pisos){
        double varillasAncho, varillasLargo, res=0;
        double espacio=.20;
        double largoVarilla=12;
        double numVarillasAncho, numVarillasLargo;
        double auxRes;
        
        //se le restan 30 cm debido a que es el espacio de las cadenas
        ancho=ancho-.30;
        largo=largo-.30;
        
        varillasAncho = (ancho/espacio)+1;
        varillasLargo = (largo/espacio)+1;
        
        numVarillasAncho = (varillasAncho*largo)/largoVarilla;
        numVarillasLargo = (varillasLargo*ancho)/largoVarilla;
        
        auxRes = numVarillasAncho + numVarillasLargo;
        
        //se multiplica por 2 o por 3 ya que es el numero de lozas
        if(pisos==1){
            res = auxRes*2;
        }else if(pisos==2){
            res = auxRes*3;
        }
            
        
        varillas = res;
    }
    
    public void coladoLozas(double ancho, double largo, int pisos){
        double aguaC = 2.5;
        double arenaC = 7;
        double gravaC = 8;
        double sacoC = 1;
        double lozaCimentacion=.20;
        double lozaEntrepiso=.12;
        double lozaTapa=.10;
        double m3Cimentacion, m3Entrepiso, m3Tapa;
        
        //se le restan 30 cm debido a que es el espacio de las cadenas
        ancho=ancho-.30;
        largo=largo-.30;
        
        if(pisos==1){
            m3Cimentacion = ancho*largo*lozaCimentacion;
            m3Tapa = ancho*largo*lozaTapa;
            
            agua = aguaC * m3Cimentacion;
            arena = arenaC * m3Cimentacion;
            grava = gravaC * m3Cimentacion;
            saco = sacoC * m3Cimentacion;
            
            agua = agua+(aguaC * m3Tapa);
            arena = arena+(arenaC * m3Tapa);
            grava = grava+(gravaC * m3Tapa);
            saco = saco+(sacoC * m3Tapa);
            
        }else if(pisos==2){
            
            System.out.println("Dos pisos");
            
            m3Cimentacion = ancho*largo*lozaCimentacion;
            m3Entrepiso = ancho*largo*lozaEntrepiso;
            m3Tapa = ancho*largo*lozaTapa;
            
            agua = aguaC * m3Cimentacion;
            arena = arenaC * m3Cimentacion;
            grava = gravaC * m3Cimentacion;
            saco = sacoC * m3Cimentacion;

            agua = agua+(aguaC * m3Entrepiso);
            arena = arena+(arenaC * m3Entrepiso);
            grava = grava+(gravaC * m3Entrepiso);
            saco = saco+(sacoC * m3Entrepiso);

            agua = agua+(aguaC * m3Tapa);
            arena = arena+(arenaC * m3Tapa);
            grava = grava+(gravaC * m3Tapa);
            saco = saco+(sacoC * m3Tapa);
        }
        
        
    }
    
    public void castillos(int pisos){
        double aguaC = 2.5;
        double arenaC = 7;
        double gravaC = 8;
        double sacoC = 1;
        double altura;
        double mcubicos;
        double distanciaAnillo=.20;
        double alambreC,numAlambre;
        
        if(pisos==1){
            
            altura= 3;
            mcubicos= .30*.15*altura;
            
            agua = agua+((aguaC * mcubicos)*4);
            arena = arena+((arenaC * mcubicos)*4);
            grava = grava+((gravaC * mcubicos)*4);
            saco = saco+((sacoC * mcubicos)*4);

            varillas = varillas+(1*4);
            
            numAlambre= altura/distanciaAnillo;
            //se multiplica por .50 ya es el perimetro de los anillos, que son de 10x15
            alambreC=numAlambre*.50;
            
            alambre = alambre+alambreC;
            
        }else if(pisos==2){
            
            altura= 6;
            mcubicos= .30*.15*altura;
            
            agua = agua+((aguaC * mcubicos)*4);
            arena = arena+((arenaC * mcubicos)*4);
            grava = grava+((gravaC * mcubicos)*4);
            saco = saco+((sacoC * mcubicos)*4);

            varillas = varillas+(2*4);
            
            numAlambre= (altura/distanciaAnillo)*4;
            //se multiplica por .50 ya es el perimetro de los anillos, que son de 10x15
            alambreC=numAlambre*.50;
            
            alambre = alambre+alambreC;
        }
        
        
        
        
        
    }
    
    public void cadenas(double ancho, double largo,int pisos){
        double aguaC = 2.5;
        double arenaC = 7;
        double gravaC = 8;
        double sacoC = 1;
        double mcubicosH;
        double mcubicosV;
        double varillasH,varillasV;
        double largoVarilla = 12;
        int numCadenas=0;
        double distanciaAnillo=.20;
        double alambreC,numAlambreH,numAlambreV;
        
        mcubicosH = .30*.15*ancho;
        mcubicosV = .30*.15*largo;
        
        if(pisos==1){
            numCadenas=4;
        }else if(pisos==2){
            numCadenas=6;
        }
        //Cadenas horizontales
        agua = agua+((aguaC * mcubicosH)*numCadenas);
        arena = arena+((arenaC * mcubicosH)*numCadenas);
        grava = grava+((gravaC * mcubicosH)*numCadenas);
        saco = saco+((sacoC * mcubicosH)*numCadenas);
        
        //Cadenas Verticales
        agua = agua+((aguaC * mcubicosV)*numCadenas);
        arena = arena+((arenaC * mcubicosV)*numCadenas);
        grava = grava+((gravaC * mcubicosV)*numCadenas);
        saco = saco+((sacoC * mcubicosV)*numCadenas);
        
        //Calculo de varillas para las cadenas horizontales
        varillasH = ((4*ancho)/largoVarilla)*numCadenas;
        varillas = varillas+varillasH;
        
        //Calculo de varillas para las cadenas verticales
        varillasV = ((4*largo)/largoVarilla)*numCadenas;
        varillas = varillas+varillasV;
        
        //Calculo de alambron en las cadenas
        numAlambreH= (ancho/distanciaAnillo)*numCadenas;
        numAlambreV= (largo/distanciaAnillo)*numCadenas;
        
        //se multiplica por .50 ya es el perimetro de los anillos, que son de 10x15
        alambreC=(numAlambreH+numAlambreV)*.50;
            
        alambre = alambre+alambreC;
        
    }
    
    public void paredesPerimetro(double ancho, double largo, int pisos,int ladrillo){
        double aguaC = 2.5;
        double arenaC = 7;
        double gravaC = 8;
        double sacoC = 1;
        double sacoMorteroC = 1;
        double espesorMortero = .01;
        double medidaVarilla = 12;
        double altura = 0;
        double largoCastillo = .30;
        double anchoCastillo = .15;
        double m2Largo;
        double m2LadrilloMortero;
        double m2Ancho;
        double m2LadrilloMorteroA;
        double numLadrillos;
        double numLadrillosA;
        double m2Mortero, m2MorteroA;
        double largoLadrillo = 0, anchoLadrillo = 0, espesorLadrillo = 0;
        double numCastillosA,numCastillosL,numCastillosTotal,auxCastillos,m3Castillos;
        double alambreC;
        
        if(pisos==1){
            altura=3;
        }else if(pisos==2){
            altura=6;
        }
        
        if(ladrillo==1){
            largoLadrillo = .10;
            anchoLadrillo = .24;
            espesorLadrillo = .12;
        }else{
            if(ladrillo==2){
                largoLadrillo = .10;
                anchoLadrillo = .24;
                espesorLadrillo = .12;
            }
        }
        
        numCastillosL=(largo/2.5)*2;
        largo=largo-(numCastillosL*.30);
        
        numCastillosA=(ancho/2.5)*2;
        ancho=ancho-(numCastillosA*.30);
        
        //calculo de varillas de los castillos del perimetro
        numCastillosTotal=numCastillosL+numCastillosA;
        auxCastillos=(((numCastillosTotal)*altura)/medidaVarilla)*4;
        
        varillas= varillas + auxCastillos;
        
        m3Castillos = (largoCastillo*anchoCastillo*altura)*numCastillosTotal;
        
        agua = agua + (aguaC * m3Castillos);
        arena = arena + (arenaC * m3Castillos);
        grava = grava + (gravaC * m3Castillos);
        saco = saco + (sacoC * m3Castillos);
        
        //calculo de alambron en los castillos
        alambreC=((numCastillosTotal*altura)/.20)*.50;
        
        alambre= alambre + alambreC;
        
        //Calculo de ladrillos con mortero del frente y detras de la casa
        m2Largo=(altura-espesorMortero-(largoCastillo*4))*(largo-espesorMortero-(largoCastillo*3));
        m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
        numLadrillos=m2Largo/m2LadrilloMortero;
        
        ladrillos=numLadrillos*2;
       
        //Calculo solo de mortero del frente y detras de la casa
        m2Mortero=((m2Largo-((largoLadrillo*anchoLadrillo)*numLadrillos))*2)*espesorLadrillo;
        
        agua = agua + (aguaC * m2Mortero);
        arena = arena + (arenaC * m2Mortero);
        grava = grava + (gravaC * m2Mortero);
        sacoMortero = sacoMortero + (sacoMorteroC * m2Mortero);
        
        //Calculo de ladrillos con mortero de los lados izquierdo y derecho de la casa
        m2Ancho=(altura-espesorMortero-(anchoCastillo*4))*(ancho-espesorMortero-(largoCastillo*3));
        m2LadrilloMorteroA=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
        numLadrillosA=m2Ancho/m2LadrilloMorteroA;
        
        ladrillos = ladrillos + (numLadrillosA*2);
        
        //Calculo solo de mortero de los lados izquierdo y derecho de la casa
        m2MorteroA=((m2Ancho-((largoLadrillo*anchoLadrillo)*numLadrillos))*2)*espesorLadrillo;
        
        agua = agua + (aguaC * m2MorteroA);
        arena = arena + (arenaC * m2MorteroA);
        grava = grava + (gravaC * m2MorteroA);
        sacoMortero = sacoMortero + (sacoMorteroC * m2MorteroA);
        
    }
    
    public void cuartos(int pisos,double anchoCuarto1,double largoCuarto1,double anchoCuarto2,
               double largoCuarto2,double anchoBano,double largoBano,double anchoCocina,
               double largoCocina,double anchoLavado,double largoLavado,int ladrillo){
        
        double alturaPisoTecho=2.7;
        double largoVarilla=12;
        double aguaC = 2.5;
        double arenaC = 7;
        double gravaC = 8;
        double sacoC = 1;
        double sacoMorteroC = 1;
        double espesorMortero = .01;
        double altura = 3;
        double largoCastillo = .30;
        double anchoCastillo = .15;
        double largoLadrillo = .10;
        double anchoLadrillo = .24;
        double espesorLadrillo = .12;
        double m2Cuarto1,m2LadrilloMortero,m2Sobrantes,numLadrillos,m2Cuarto2,m2ParedComp;
        double m2Mortero, auxVarillas,m3Castillo,alambreC,alambreCa,auxCadena,m3Cadena,m2Bano;
        ///////calcular el material de los castillos y restar la cadena del techo
        if(ladrillo==1){
            largoLadrillo = .10;
            anchoLadrillo = .24;
            espesorLadrillo = .12;
        }else if(ladrillo==2){
            largoLadrillo = .10;
            anchoLadrillo = .24;
            espesorLadrillo = .12;
        }
        if(pisos==2){
                //pared por si es de dos pisos
            m2Cuarto1=anchoCuarto1*alturaPisoTecho;
            m2Sobrantes=m2Cuarto1-((.15*alturaPisoTecho)*3)-(.30*anchoCuarto1);
            m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
            numLadrillos=m2Sobrantes/m2LadrilloMortero;

            ladrillos = ladrillos + (numLadrillos);

                //3 Castillos de la pared, uno a la mitad y dos a cada lado de la puerta
            auxVarillas= (4*3*alturaPisoTecho)/largoVarilla;

            varillas = varillas + auxVarillas;

            m3Castillo=largoCastillo*anchoCastillo*alturaPisoTecho;

            agua = agua + (aguaC * m3Castillo);
            arena = arena + (arenaC * m3Castillo);
            grava = grava + (gravaC * m3Castillo);
            saco = saco + (sacoC * m3Castillo);
                //calculo de alambron en los castillos
            alambreC=((3*alturaPisoTecho)/.20)*.50;

            alambre= alambre + alambreC;
                //Cadena
            auxCadena = (4*anchoCuarto1)/largoVarilla;

            varillas = varillas + auxCadena;

            m3Cadena = largoCastillo*anchoCastillo*anchoCuarto1;

            agua = agua + (aguaC * m3Cadena);
            arena = arena + (arenaC * m3Cadena);
            grava = grava + (gravaC * m3Cadena);
            saco = saco + (sacoC * m3Cadena);

                //calculo de alambron en la cadena
            alambreCa=((anchoCuarto1)/.20)*.50;

            alambre= alambre + alambreCa;

                //Calculo solo de mortero 
            m2Mortero=(m2Sobrantes-((largoLadrillo*anchoLadrillo)*numLadrillos))*espesorLadrillo;

            agua = agua + (aguaC * m2Mortero);
            arena = arena + (arenaC * m2Mortero);
            grava = grava + (gravaC * m2Mortero);
            sacoMortero = sacoMortero + (sacoMorteroC * m2Mortero);
        }
        //pared cuarto 1 con puerta
        m2Cuarto1=anchoCuarto1*alturaPisoTecho;
        m2Sobrantes=m2Cuarto1-((.15*alturaPisoTecho)*3)-(.825*2.03)-(.30*anchoCuarto1);
        m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
        numLadrillos=m2Sobrantes/m2LadrilloMortero;
        
        ladrillos = ladrillos + (numLadrillos);
        
            //3 Castillos de la pared, uno a la mitad y dos a cada lado de la puerta
        auxVarillas= (4*3*alturaPisoTecho)/largoVarilla;
        
        varillas = varillas + auxVarillas;
        
        m3Castillo=largoCastillo*anchoCastillo*alturaPisoTecho;
        
        agua = agua + (aguaC * m3Castillo);
        arena = arena + (arenaC * m3Castillo);
        grava = grava + (gravaC * m3Castillo);
        saco = saco + (sacoC * m3Castillo);
            //calculo de alambron en los castillos
        alambreC=((3*alturaPisoTecho)/.20)*.50;
        
        alambre= alambre + alambreC;
            //Cadena
        auxCadena = (4*anchoCuarto1)/largoVarilla;
        
        varillas = varillas + auxCadena;
        
        m3Cadena = largoCastillo*anchoCastillo*anchoCuarto1;
        
        agua = agua + (aguaC * m3Cadena);
        arena = arena + (arenaC * m3Cadena);
        grava = grava + (gravaC * m3Cadena);
        saco = saco + (sacoC * m3Cadena);
        
            //calculo de alambron en la cadena
        alambreCa=((anchoCuarto1)/.20)*.50;
        
        alambre= alambre + alambreCa;
        
            //Calculo solo de mortero 
        m2Mortero=(m2Sobrantes-((largoLadrillo*anchoLadrillo)*numLadrillos))*espesorLadrillo;
        
        agua = agua + (aguaC * m2Mortero);
        arena = arena + (arenaC * m2Mortero);
        grava = grava + (gravaC * m2Mortero);
        sacoMortero = sacoMortero + (sacoMorteroC * m2Mortero);
        
        //pared cuarto 2 con puerta
        m2Cuarto2=anchoCuarto2*alturaPisoTecho;
        m2Sobrantes=m2Cuarto2-((.15*alturaPisoTecho)*3)-(.825*2.03)-(.30*anchoCuarto2);
        m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
        numLadrillos=m2Sobrantes/m2LadrilloMortero;
        
        ladrillos = ladrillos + (numLadrillos);
        
            //3 Castillos de la pared, uno a la mitad y dos a cada lado de la puerta
        auxVarillas= (4*3*alturaPisoTecho)/largoVarilla;
        
        varillas = varillas + auxVarillas;
        
        m3Castillo=largoCastillo*anchoCastillo*alturaPisoTecho;
        
        agua = agua + (aguaC * m3Castillo);
        arena = arena + (arenaC * m3Castillo);
        grava = grava + (gravaC * m3Castillo);
        saco = saco + (sacoC * m3Castillo);
            //calculo de alambron en los castillos
        alambreC=((3*alturaPisoTecho)/.20)*.50;
        
        alambre= alambre + alambreC;
            //Cadena
        auxCadena = (4*anchoCuarto2)/largoVarilla;
        
        varillas = varillas + auxCadena;
        
        m3Cadena = largoCastillo*anchoCastillo*anchoCuarto2;
        
        agua = agua + (aguaC * m3Cadena);
        arena = arena + (arenaC * m3Cadena);
        grava = grava + (gravaC * m3Cadena);
        saco = saco + (sacoC * m3Cadena);
        
            //calculo de alambron en la cadena
        alambreCa=((anchoCuarto2)/.20)*.50;
        
        alambre= alambre + alambreCa;
        
            //Calculo solo de mortero 
        m2Mortero=(m2Sobrantes-((largoLadrillo*anchoLadrillo)*numLadrillos))*espesorLadrillo;
        
        agua = agua + (aguaC * m2Mortero);
        arena = arena + (arenaC * m2Mortero);
        grava = grava + (gravaC * m2Mortero);
        sacoMortero = sacoMortero + (sacoMorteroC * m2Mortero);
        
        
        //Pared compartida entre Cuarto1 y Cuarto2
        m2ParedComp=alturaPisoTecho*largoCuarto2;
        m2Sobrantes=m2ParedComp-(.15*alturaPisoTecho)-(.30*largoCuarto2);
        m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
        numLadrillos=m2Sobrantes/m2LadrilloMortero;
        
        ladrillos = ladrillos + (numLadrillos);
        
            //3 Castillos de la pared, uno a la mitad y dos a cada lado de la puerta
        auxVarillas= (4*alturaPisoTecho)/largoVarilla;
        
        varillas = varillas + auxVarillas;
        
        m3Castillo=largoCastillo*anchoCastillo*alturaPisoTecho;
        
        agua = agua + (aguaC * m3Castillo);
        arena = arena + (arenaC * m3Castillo);
        grava = grava + (gravaC * m3Castillo);
        saco = saco + (sacoC * m3Castillo);
            //calculo de alambron en los castillos
        alambreC=((alturaPisoTecho)/.20)*.50;
        
        alambre= alambre + alambreC;
            //Cadena
        auxCadena = (4*largoCuarto1)/largoVarilla;
        
        varillas = varillas + auxCadena;
        
        m3Cadena = largoCastillo*anchoCastillo*largoCuarto1;
        
        agua = agua + (aguaC * m3Cadena);
        arena = arena + (arenaC * m3Cadena);
        grava = grava + (gravaC * m3Cadena);
        saco = saco + (sacoC * m3Cadena);
        
            //calculo de alambron en la cadena
        alambreCa=((largoCuarto1)/.20)*.50;
        
        alambre= alambre + alambreCa;

            //Calculo solo de mortero 
        m2Mortero=(m2Sobrantes-((largoLadrillo*anchoLadrillo)*numLadrillos))*espesorLadrillo;
        
        agua = agua + (aguaC * m2Mortero);
        arena = arena + (arenaC * m2Mortero);
        grava = grava + (gravaC * m2Mortero);
        sacoMortero = sacoMortero + (sacoMorteroC * m2Mortero);
        
        //Pared bano con puerta
        m2Bano=largoBano*alturaPisoTecho;
        m2Sobrantes=m2Bano-(.15*alturaPisoTecho)-(.825*2.03)-(.30*largoBano);
        m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
        numLadrillos=m2Sobrantes/m2LadrilloMortero;
        
        ladrillos = ladrillos + (numLadrillos);
        
            //3 Castillos de la pared, uno a la mitad y dos a cada lado de la puerta
        auxVarillas= (4*2*alturaPisoTecho)/largoVarilla;
        
        varillas = varillas + auxVarillas;
        
        m3Castillo=largoCastillo*anchoCastillo*alturaPisoTecho;
        
        agua = agua + (aguaC * m3Castillo);
        arena = arena + (arenaC * m3Castillo);
        grava = grava + (gravaC * m3Castillo);
        saco = saco + (sacoC * m3Castillo);
            //calculo de alambron en los castillos
        alambreC=((2*alturaPisoTecho)/.20)*.50;
        
        alambre= alambre + alambreC;
            //Cadena
        auxCadena = (4*largoBano)/largoVarilla;
        
        varillas = varillas + auxCadena;
        
        m3Cadena = largoCastillo*anchoCastillo*largoBano;
        
        agua = agua + (aguaC * m3Cadena);
        arena = arena + (arenaC * m3Cadena);
        grava = grava + (gravaC * m3Cadena);
        saco = saco + (sacoC * m3Cadena);
        
            //calculo de alambron en la cadena
        alambreCa=((largoBano)/.20)*.50;
        
        alambre= alambre + alambreCa;
        
            //Calculo solo de mortero 
        m2Mortero=(m2Sobrantes-((largoLadrillo*anchoLadrillo)*numLadrillos))*espesorLadrillo;
        
        agua = agua + (aguaC * m2Mortero);
        arena = arena + (arenaC * m2Mortero);
        grava = grava + (gravaC * m2Mortero);
        sacoMortero = sacoMortero + (sacoMorteroC * m2Mortero);
        
        //Pared bano atras
        m2Bano=largoBano*alturaPisoTecho;
        m2Sobrantes=m2Bano-(.15*alturaPisoTecho)-(.30*largoBano);
        m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
        numLadrillos=m2Sobrantes/m2LadrilloMortero;
        
        ladrillos = ladrillos + (numLadrillos);
        
            //3 Castillos de la pared, uno a la mitad y dos a cada lado de la puerta
        auxVarillas= (4*2*alturaPisoTecho)/largoVarilla;
        
        varillas = varillas + auxVarillas;
        
        m3Castillo=largoCastillo*anchoCastillo*alturaPisoTecho;
        
        agua = agua + (aguaC * m3Castillo);
        arena = arena + (arenaC * m3Castillo);
        grava = grava + (gravaC * m3Castillo);
        saco = saco + (sacoC * m3Castillo);
            //calculo de alambron en los castillos
        alambreC=((2*alturaPisoTecho)/.20)*.50;
        
        alambre= alambre + alambreC;
            //Cadena
        auxCadena = (4*largoBano)/largoVarilla;
        
        varillas = varillas + auxCadena;
        
        m3Cadena = largoCastillo*anchoCastillo*largoBano;
        
        agua = agua + (aguaC * m3Cadena);
        arena = arena + (arenaC * m3Cadena);
        grava = grava + (gravaC * m3Cadena);
        saco = saco + (sacoC * m3Cadena);
        
            //calculo de alambron en la cadena
        alambreCa=((largoBano)/.20)*.50;
        
        alambre= alambre + alambreCa;
        
            //Calculo solo de mortero 
        m2Mortero=(m2Sobrantes-((largoLadrillo*anchoLadrillo)*numLadrillos))*espesorLadrillo;
        
        agua = agua + (aguaC * m2Mortero);
        arena = arena + (arenaC * m2Mortero);
        grava = grava + (gravaC * m2Mortero);
        sacoMortero = sacoMortero + (sacoMorteroC * m2Mortero);
        
        //Pared ancho bano
        m2Bano=anchoBano*alturaPisoTecho;
        m2Sobrantes=m2Bano-(.15*alturaPisoTecho)-(.30*anchoBano);
        m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
        numLadrillos=m2Sobrantes/m2LadrilloMortero;
        
        ladrillos = ladrillos + (numLadrillos);
        
            //3 Castillos de la pared, uno a la mitad y dos a cada lado de la puerta
        auxVarillas= (4*3*alturaPisoTecho)/largoVarilla;
        
        varillas = varillas + auxVarillas;
        
        m3Castillo=largoCastillo*anchoCastillo*alturaPisoTecho;
        
        agua = agua + (aguaC * m3Castillo);
        arena = arena + (arenaC * m3Castillo);
        grava = grava + (gravaC * m3Castillo);
        saco = saco + (sacoC * m3Castillo);
            //calculo de alambron en los castillos
        alambreC=((3*alturaPisoTecho)/.20)*.50;
        
        alambre= alambre + alambreC;
            //Cadena
        auxCadena = (4*anchoBano)/largoVarilla;
        
        varillas = varillas + auxCadena;
        
        m3Cadena = largoCastillo*anchoCastillo*anchoBano;
        
        agua = agua + (aguaC * m3Cadena);
        arena = arena + (arenaC * m3Cadena);
        grava = grava + (gravaC * m3Cadena);
        saco = saco + (sacoC * m3Cadena);
        
            //calculo de alambron en la cadena
        alambreCa=((anchoBano)/.20)*.50;
        
        alambre= alambre + alambreCa;
        
            //Calculo solo de mortero 
        m2Mortero=(m2Sobrantes-((largoLadrillo*anchoLadrillo)*numLadrillos))*espesorLadrillo;
        
        agua = agua + (aguaC * m2Mortero);
        arena = arena + (arenaC * m2Mortero);
        grava = grava + (gravaC * m2Mortero);
        sacoMortero = sacoMortero + (sacoMorteroC * m2Mortero);
    }
    
    public void escalera(){
        double aguaC = 2.5;
        double arenaC = 7;
        double gravaC = 8;
        double sacoMorteroC = 1;
        double sacoC = 1;
        double largoLadrillo = .10;
        double anchoLadrillo = .24;
        double espesorLadrillo = .12;
        double espesorMortero = .01;
        double espacioVarilla = .10;
        double largoVarilla = 12;
        double largoEscalera8 = 1.6;
        double largoEscalera7 = 1.4;
        double anchoEscalera = .7;
        double numEscalones = 15;
        double largoDescanso = .7;
        double anchoDescanso = 1.4;
        double m2Subida8, m2Descanso, m2Subida7;
        double numVarillasSubida8, numVarillasDescanso, numVarillasSubida7, numVarillas;
        double m3Escalon;
        double m3LadrilloMortero;
        double numLadrillosMortero;
        
        numVarillasSubida8 = (((anchoEscalera/espacioVarilla)*largoEscalera8) + ((largoEscalera8/espacioVarilla)*anchoEscalera))/12;
        numVarillasDescanso = (((anchoDescanso/espacioVarilla)*largoDescanso) + ((largoDescanso/espacioVarilla)*anchoDescanso))/12;
        numVarillasSubida7 = (((anchoEscalera/espacioVarilla)*largoEscalera7) + ((largoEscalera7/espacioVarilla)*anchoEscalera))/12;
        
        numVarillas  = numVarillasSubida8 + numVarillasDescanso + numVarillasSubida7;
        
        varillas = varillas + numVarillas;
        
        m2Subida8 = anchoEscalera * largoEscalera8;
        m2Descanso = largoDescanso * anchoDescanso;
        m2Subida7 = anchoEscalera * largoEscalera7;
        
        agua = agua + (aguaC*m2Subida8) + (aguaC*m2Descanso) + (aguaC*m2Subida7);
        arena = arena + (arenaC*m2Subida8) + (arenaC*m2Descanso) + (arenaC*m2Subida7);
        grava = grava + (gravaC*m2Subida8) + (gravaC*m2Descanso) + (gravaC*m2Subida7);
        saco = saco + (sacoC*m2Subida8) + (sacoC*m2Descanso) + (sacoC*m2Subida7);

        //ladrillo de escaleras
        m3Escalon = ((.18*.20)/2)*.70;
        m3LadrilloMortero = (largoLadrillo+(espesorMortero*2))*(anchoLadrillo+(espesorMortero*2))*(espesorLadrillo+(espesorMortero*2));
        numLadrillosMortero = m3Escalon/m3LadrilloMortero;
        System.out.println(""+numLadrillosMortero);
    }
    
    public void cimentacion(double ancho, double largo){
        double varillasAncho, varillasLargo, res=0;
        double espacio=.20;
        double largoVarilla=12;
        double altura = .80;
        double numVarillasAncho, numVarillasLargo;
        double aguaC = 2.5;
        double arenaC = 7;
        double gravaC = 8;
        double sacoC = 1;
        double concretoPobre =.05;
        double m3Cimentacion;
        double numCastillosL,numCastillosA,numCastillosTotal,auxCastillos;
        double m3lozaCimentacionA,m3lozaCimentacionL,m3Castillos;
        double auxAlambre;
        
        m3Cimentacion=ancho*largo*concretoPobre;
        
        agua= agua + (aguaC + m3Cimentacion);
        arena= arena + (arenaC + m3Cimentacion);
        grava= grava + (gravaC + m3Cimentacion);
        saco= saco + (sacoC + m3Cimentacion);
        
        varillasAncho = (ancho/espacio)+1;
        varillasLargo = (largo/espacio)+1;
        
        numVarillasAncho = (varillasAncho*largo)/largoVarilla;
        numVarillasLargo = (varillasLargo*ancho)/largoVarilla;
        
        varillas = varillas + numVarillasAncho + numVarillasLargo;
        
        numCastillosL=(largo/2.5)*2;
        numCastillosA=(ancho/2.5)*2;
        numCastillosTotal=numCastillosL+numCastillosA;
        auxCastillos=(((numCastillosTotal)*altura)/largoVarilla)*4;
        
        varillas= varillas + auxCastillos;
        
        auxAlambre=(((numCastillosTotal)*altura)/.20)*.50;
        
        alambre= alambre+auxAlambre;
        
        m3lozaCimentacionA=(.15*.30*ancho)*2;
        m3lozaCimentacionL=(.15*.30*largo)*2;
        
        m3Castillos= (.15*.30*.65)*numCastillosTotal;
        
        agua= agua + (aguaC * m3lozaCimentacionA) + (aguaC * m3lozaCimentacionL) + (aguaC * m3Castillos);
        arena= arena + (arenaC * m3lozaCimentacionA) + (arenaC * m3lozaCimentacionL) + (arenaC * m3Castillos);
        grava= grava + (gravaC * m3lozaCimentacionA) + (gravaC * m3lozaCimentacionL) + (gravaC * m3Castillos);
        saco= saco + (sacoC * m3lozaCimentacionA) + (sacoC * m3lozaCimentacionL) + (sacoC * m3Castillos);
    }
    	public void imprimir(){
    	consulta.setAgua(Math.ceil(agua));
    	consulta.setArena(Math.ceil(arena));
    	consulta.setGrava(Math.ceil(grava));
    	consulta.setSaco(Math.ceil(saco));
    	consulta.setSacoMortero(Math.ceil(sacoMortero));
    	consulta.setVarilla(Math.ceil(varillas));
    	consulta.setLadrilloRojo(Math.ceil(ladrillos));
    	consulta.setLadrilloBloackPesado(Math.ceil(blockpesado));
    	consulta.setLadrilloBlockLigero(Math.ceil(blockligero));
        consulta.setAlambre(Math.ceil(alambre));
        consulta.setVarillaArmex(Math.ceil(varillaArmex));
        System.out.println("Materiales: \n"
        + "Agua: "+Math.ceil(agua)
        + "\nArena: "+Math.ceil(arena)
        + "\nGrava: "+Math.ceil(grava)
        + "\nSacos: "+Math.ceil(saco)
        + "\nSacos de mortero: "+Math.ceil(sacoMortero)
        + "\nVarillas: "+Math.ceil(varillas)
        + "\nAlambre: "+Math.ceil(alambre)
        + "\nLadrillos: "+Math.ceil(ladrillos));
        agua = 0;
        arena=0;
        grava=0;
        saco=0;
        sacoMortero=0;
        varillas=0;
        ladrillos=0;
        alambre=0;
        blockligero=0;
        blockpesado=0;
    }
    
	public ConsultaModel getConsulta() {
		return consulta;
	}
    
    
}