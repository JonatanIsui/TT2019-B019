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
import com.tt2.entity.Diccionario;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.entity.Usuario;
import com.tt2.service.AdministradorBean;
import com.tt2.service.ArquitectoBean;
import com.tt2.service.UsuarioBean;

@ExtendWith(SpringExtension.class)

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")

//@AutoConfigureMockMvc


class PruebaIntegracionAdmin {
	
	final private static int port = 8080;
	final private static String baseUrl = "http://localhost:";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired 
	private AdministradorBean beanAdmin;

	@Test
	void pruebaGetProveedores() {
		
		List<String> expectedIDList = Stream.of(
				"diego.futbolero2008@hotmail.com","a@a.com"
				).collect(Collectors.toList());
		
		ResponseEntity<List<Usuario>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/administrador/proveedores",
						HttpMethod.POST, null,
						new ParameterizedTypeReference<List<Usuario>>() {
						});
			List<Usuario> listaRespuestaUsuarios = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(listaRespuestaUsuarios.size() > 2);
			
			assertTrue(listaRespuestaUsuarios.stream().anyMatch((usuario) -> {
				return expectedIDList.contains(usuario.getCorreo());
			}));
			
	}
	
	@Test
	void pruebaGetArquitectos() {
		
		List<String> expectedCorreoList = Stream.of(
				"arquitecto@arquitecto.com",
				"12345@12345.com",
				"1234@1234.com",
				"jonatan@isui.com",
				"prueba@nav.com",
				"jonatan@jonatan.com",
				"isui@isui.com",
				"solicitud@prueba.com"
				).collect(Collectors.toList());
		
		ResponseEntity<List<Usuario>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/administrador/arquitectos",
						HttpMethod.POST, null,
						new ParameterizedTypeReference<List<Usuario>>() {
						});
			List<Usuario> listaRespuestaUsuarios = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(listaRespuestaUsuarios.size() > 7);
			
			assertTrue(listaRespuestaUsuarios.stream().anyMatch((usuario) -> {
				return expectedCorreoList.contains(usuario.getCorreo());
			}));
			
	}
	
	@Test
	void pruebaGetSolicitudes() {
		
		List<String> expectedCorreoList = Stream.of(
				"proveedor@prueba.com"
				).collect(Collectors.toList());
		
		ResponseEntity<List<SolicitudProveedor>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/administrador/solicitudes",
						HttpMethod.POST, null,
						new ParameterizedTypeReference<List<SolicitudProveedor>>() {
						});
			List<SolicitudProveedor> listaRespuestaSolicitudes = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(listaRespuestaSolicitudes.size() > 0);
			
			assertTrue(listaRespuestaSolicitudes.stream().anyMatch((solicitudProveedor) -> {
				return expectedCorreoList.contains(solicitudProveedor.getCorreo());
			}));
			
	}
	
	@Test
	void pruebaEliminaUsuario() {
		
		String resultadoEsperado = "Usuario eliminado con exito";
		
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange(baseUrl + port + "/administrador/eliminar/57", HttpMethod.DELETE, null,String.class);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertTrue(responseEntity.getBody().contains(resultadoEsperado));
		

	}
	@Test
	void rechazarProveedor() {
		
		String resultadoEsperado = "Solicitud rechazada";
		
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange(baseUrl + port + "/administrador/rechazarProveedor/53", HttpMethod.DELETE, null,String.class);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.equals(responseEntity));
		assertTrue(responseEntity.getBody().contains(resultadoEsperado));
	}
	
	@Test
	void aceptarProveedor() {
		
		String resultadoEsperado = "Proveedor registrado";
		
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange(baseUrl + port + "/administrador/aceptarProveedor/59", HttpMethod.POST, null,String.class);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertTrue(responseEntity.getBody().contains(resultadoEsperado));
	}
	
	
	@Test
	void pruebaGetDiccionario() {
		
		List<String> expectedIDList = Stream.of(
				"palabra 1","palabra 2"
				).collect(Collectors.toList());
		
		ResponseEntity<List<Diccionario>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/administrador/diccionario",
						HttpMethod.POST, null,
						new ParameterizedTypeReference<List<Diccionario>>() {
						});
			List<Diccionario> listaRespuestaDiccionario = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(listaRespuestaDiccionario.size() > 1);
			
			assertTrue(listaRespuestaDiccionario.stream().anyMatch((diccionario) -> {
				return expectedIDList.contains(diccionario.getNombre());
			}));
			
	}
	@Test
	void agregarDefinicion() {
		
		Diccionario defNuevo = new Diccionario();
		defNuevo.setDefinicion("agregado desde test");
		defNuevo.setNombre("test");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		

		HttpEntity<Diccionario> request = new HttpEntity<Diccionario>(defNuevo, headers);
		
		String resultadoEsperado = "Definicion agregada con exito";
		
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity(baseUrl + port + "/administrador/agregarDefinicion", request ,String.class);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertTrue(responseEntity.getBody().contains(resultadoEsperado));
	}
	
	@Test
	void pruebaEliminaDefinicion() {
		
		String resultadoEsperado = "Definicion eliminada";
		
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange(baseUrl + port + "/administrador/eliminarDefinicion/53", HttpMethod.DELETE, null,String.class);
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertTrue(responseEntity.getBody().contains(resultadoEsperado));
		

	}
	
	
	
}
