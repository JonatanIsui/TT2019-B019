package com.tt2.integracion;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.catalina.mapper.Mapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tt2.entity.Arquitecto;
import com.tt2.entity.Consulta;
import com.tt2.entity.Diccionario;
import com.tt2.entity.Material;
import com.tt2.entity.Proveedor;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.entity.Usuario;
import com.tt2.model.ArchivoModel;
import com.tt2.model.ConsultaAux;
import com.tt2.model.ConsultaModel;
import com.tt2.model.MedidasModel;
import com.tt2.service.AdministradorBean;
import com.tt2.service.ArquitectoBean;
import com.tt2.service.ConsulBean;
import com.tt2.service.Imagenes;
import com.tt2.service.ProveedorBean;
import com.tt2.service.SolicitudProveedorBean;
import com.tt2.service.UsuarioBean;

@ExtendWith(SpringExtension.class)

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")

class PruebaIntegracionConsulta {

	final private static int port = 8080;
	final private static String baseUrl = "http://localhost:";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired 
	private ArquitectoBean beanArquitecto;
	
	@Autowired
	private ConsulBean consulBean;
	
	
	@Test
	void pruebaGetConsultas() {
		
		List<String> expectedIDList = Stream.of(
				"pruebaFRONT","consulta 2"
				).collect(Collectors.toList());
		
		ConsultaAux aux = new ConsultaAux();
		aux.setArquitecto(1);
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<ConsultaAux> request = new HttpEntity<ConsultaAux>(aux, headers);
		
		ResponseEntity<List<Consulta>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/arquitecto/consultasGuardas",
						HttpMethod.POST, request,
						new ParameterizedTypeReference<List<Consulta>>() {
						});
		
			List<Consulta> listaConsulta = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(listaConsulta.size() >1);
			System.out.println("-----------------------------------------------------");
			System.out.println(listaConsulta.size());
			System.out.println("-----------------------------------------------------");
			assertTrue(listaConsulta.stream().anyMatch((Consulta) -> {
				return expectedIDList.contains(Consulta.getNombre());
			}));
			
	}
	
	
	@Test
	void pruebaConsulta() {
		//nueva consulta
		MedidasModel nuevoMedidasModelo = new MedidasModel();
		nuevoMedidasModelo.setAnchoHabitacion1(2);
		nuevoMedidasModelo.setAnchobano(2);
		nuevoMedidasModelo.setAnchococina(2);
		nuevoMedidasModelo.setAncholavado(2);
		nuevoMedidasModelo.setAnchoterreno(4);
		nuevoMedidasModelo.setLargoHabitacion1(2);
		nuevoMedidasModelo.setLargobano(2);
		nuevoMedidasModelo.setLargococina(2);
		nuevoMedidasModelo.setLargolavado(2);
		nuevoMedidasModelo.setLargoterreno(4);
		nuevoMedidasModelo.setNombre("test desde Junit");
		nuevoMedidasModelo.setPisos(1);
		nuevoMedidasModelo.setTipoladrillo(1);
		nuevoMedidasModelo.setIdArquitecto(1);
		
		//modelo esperado
		ConsultaModel esperado = new ConsultaModel();
		esperado.setTotal(59141.94);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		

		HttpEntity<MedidasModel> request = new HttpEntity<MedidasModel>(nuevoMedidasModelo, headers);
		

		
		ResponseEntity<ConsultaModel> responseEntity = this.restTemplate
				.postForEntity(baseUrl + port + "/arquitecto/consulta", request ,ConsultaModel.class);
		
		ConsultaModel recibido = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(recibido.getTotal(),esperado.getTotal());

	}

	@Test
	void pruebaEliminaConsulta() {
		
		ConsultaAux aux = new ConsultaAux();
		aux.setArquitecto(1);
		aux.setNombre("elimina");
		
		

		
		String resultadoEsperado = "True";
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		
		
		HttpEntity<ConsultaAux> request = new HttpEntity<ConsultaAux>(aux, headers);
		
		
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange(baseUrl + port + "/arquitecto/elimarConsulta", HttpMethod.POST, request,String.class);
		
		
			
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(responseEntity.getBody().contains(resultadoEsperado));
		
	}
	@Test
	void pruebaEnviaConsulta() {
		
		ConsultaAux aux = new ConsultaAux();
		aux.setArquitecto(1);
		aux.setNombre("C:\\Users\\jonat\\Documents\\GitHub\\TT2019-B019\\spring\\Archivos\\Consultas\\1\\pruebaFRONT.xls");
		

		String resultadoEsperado = "True";
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		
		
		HttpEntity<ConsultaAux> request = new HttpEntity<ConsultaAux>(aux, headers);
		
		
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange(baseUrl + port + "/arquitecto/enviarConsulta", HttpMethod.POST, request,String.class);
		
		
			
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(responseEntity.getBody().contains(resultadoEsperado));
		
	}
	
}
