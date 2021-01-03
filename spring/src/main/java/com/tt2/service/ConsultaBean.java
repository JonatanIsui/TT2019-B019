package com.tt2.service;
import org.springframework.stereotype.Service;

import com.tt2.entity.Consulta;
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
	    private ConsultaModel consulta = new ConsultaModel();

	    public void varillasLozas(double ancho, double largo){
	        double varillasAncho, varillasLargo, res=0;
	        double espacio=.20;
	        double largoVarilla=12;
	        double numVarillasAncho, numVarillasLargo;
	        double auxRes;
	        varillasAncho = (ancho/espacio)+1;
	        varillasLargo = (largo/espacio)+1;
	        
	        numVarillasAncho = (varillasAncho*ancho)/largoVarilla;
	        numVarillasLargo = (varillasLargo*largo)/largoVarilla;
	        
	        auxRes = numVarillasAncho + numVarillasLargo;
	        
	        res = auxRes*3;
	        
	        varillas = res;
	    }
	    
	    public void coladoLozas(double ancho, double largo){
	        double aguaC = 2.5;
	        double arenaC = 7;
	        double gravaC = 8;
	        double sacoC = 1;
	        double lozaCimentacion=.20;
	        double lozaEntrepiso=.12;
	        double lozaTapa=.10;
	        double m3Cimentacion, m3Entrepiso, m3Tapa;
	        
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
	    
	    public void castillos(int pisos){
	        double aguaC = 2.5;
	        double arenaC = 7;
	        double gravaC = 8;
	        double sacoC = 1;
	        double altura = 6;
	        double mcubicos;
	        
	        mcubicos= .30*.15*altura;
	        
	        agua = agua+((aguaC * mcubicos)*4);
	        arena = arena+((arenaC * mcubicos)*4);
	        grava = grava+((gravaC * mcubicos)*4);
	        saco = saco+((sacoC * mcubicos)*4);
	        
	        varillas = varillas+(2*4);
	        
	        
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
	        
	        mcubicosH = .30*.15*ancho;
	        mcubicosV = .30*.15*largo;
	        //Cadenas horizontales
	        agua = agua+((aguaC * mcubicosH)*6);
	        arena = arena+((arenaC * mcubicosH)*6);
	        grava = grava+((gravaC * mcubicosH)*6);
	        saco = saco+((sacoC * mcubicosH)*6);
	        
	        //Cadenas Verticales
	        agua = agua+((aguaC * mcubicosV)*6);
	        arena = arena+((arenaC * mcubicosV)*6);
	        grava = grava+((gravaC * mcubicosV)*6);
	        saco = saco+((sacoC * mcubicosV)*6);
	        
	        //Calculo de varillas para las cadenas horizontales
	        varillasH = ((4*ancho)/largoVarilla)*6;
	        varillas = varillas+varillasH;
	        
	        //Calculo de varillas para las cadenas verticales
	        varillasV = ((4*largo)/largoVarilla)*6;
	        varillas = varillas+varillasV;
	        
	        
	        
	    }
	    
	    public void paredesPerimetro(double ancho, double largo, int pisos,int ladrillo){
	        System.out.println(ancho);
	        System.out.println(largo);
	        System.out.println(pisos);
	        System.out.println(ladrillo);
	    	double aguaC = 2.5;
	        double arenaC = 7;
	        double gravaC = 8;
	        double sacoMorteroC = 1;
	        double espesorMortero = .01;
	        double altura = 6;
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
	        
	        //Calculo de ladrillos con mortero del frente y detras de la casa
	        m2Largo=(altura-espesorMortero-(largoCastillo*4))*(largo-espesorMortero-(largoCastillo*3));
	        m2LadrilloMortero=(largoLadrillo+espesorMortero)*(anchoLadrillo+espesorMortero);
	        numLadrillos=m2Largo/m2LadrilloMortero;
	        
	        ladrillos=numLadrillos*2;
	       
	        //Calculo solo de mortero del frente y detras de la casa
	        m2Mortero=(m2Largo-((largoLadrillo*anchoLadrillo)*numLadrillos))*2;
	        
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
	        m2MorteroA=(m2Ancho-((largoLadrillo*anchoLadrillo)*numLadrillos))*2;
	        
	        agua = agua + (aguaC * m2MorteroA);
	        arena = arena + (arenaC * m2MorteroA);
	        grava = grava + (gravaC * m2MorteroA);
	        sacoMortero = sacoMortero + (sacoMorteroC * m2MorteroA);
	        
	    }
	    
	    public void cuartos(double anchoCuarto, double largoCuarto,  int pisos,int ladrillo){
	        double alturaPisoTecho=2.7;
	        double aguaC = 2.5;
	        double arenaC = 7;
	        double gravaC = 8;
	        double sacoMorteroC = 1;
	        double espesorMortero = .01;
	        double altura = 6;
	        double largoCastillo = .30;
	        double anchoCastillo = .15;
	        
	        //calculo de castillos, castillos en las esquinas  y cada 2.5m, ademas de a los costados de la puerta
	        
	        
	        //pared con puerta
	        
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
    public void imprimir(){
    	consulta.setAgua(Math.ceil(agua));
    	consulta.setArena(Math.ceil(arena));
    	consulta.setGrava(Math.ceil(grava));
    	consulta.setSaco(Math.ceil(saco));
    	consulta.setSacoMortero(Math.ceil(sacoMortero));
    	consulta.setVarilla(Math.ceil(varillas));
    	consulta.setLadrilloRojo(Math.ceil(ladrillos));
        System.out.println("Materiales: \n"
                + "Agua: "+Math.ceil(agua)
                + "\nArena: "+Math.ceil(arena)
                + "\nGrava: "+Math.ceil(grava)
                + "\nSacos: "+Math.ceil(saco)
                + "\nSacos de mortero: "+Math.ceil(sacoMortero)
                + "\nVarillas: "+Math.ceil(varillas)
                + "\nLadrillos: "+Math.ceil(ladrillos));
        agua = 0;
        arena=0;
        grava=0;
        saco=0;
        sacoMortero=0;
        varillas=0;
        ladrillos=0;
    }
    
	public ConsultaModel getConsulta() {
		return consulta;
	}
    
    
}