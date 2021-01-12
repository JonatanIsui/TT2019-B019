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
import com.tt2.entity.Material;
import com.tt2.entity.Proveedor;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.entity.Usuario;
import com.tt2.model.ArchivoModel;
import com.tt2.service.AdministradorBean;
import com.tt2.service.ArquitectoBean;
import com.tt2.service.Imagenes;
import com.tt2.service.ProveedorBean;
import com.tt2.service.SolicitudProveedorBean;
import com.tt2.service.UsuarioBean;

@ExtendWith(SpringExtension.class)

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")

class PruebaIntegracionUsuario {

	final private static int port = 8080;
	final private static String baseUrl = "http://localhost:";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired 
	private SolicitudProveedorBean beanSolicitud;
	
	@Autowired
	private Imagenes imagenBean;
	
	@Test
	void pruebaIniciaSesion() {
		
		//resultado esperado
		Usuario usuarioEsperado = new Usuario();
		usuarioEsperado.setCorreo("diego@admin.com");
		usuarioEsperado.setPassword("12345");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioEsperado, headers);
		
		ResponseEntity<Usuario> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/index/login",
						HttpMethod.POST, request,
						new ParameterizedTypeReference<Usuario>() {
						});
			Usuario respuestaUsuarios = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(respuestaUsuarios.getCorreo().equals(usuarioEsperado.getCorreo()));
			

			
	}
	
	@Test
	void pruebaRecuperaPass() {
		
		//resultado esperado
		String resEsperado = "Se ha enviado correo para restablecer su contraseña";
		
		String correo = "diego.futbolero2008@hotmail.com";
		
	
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		

		
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange(baseUrl + port + "/index/recuperarPassword/"+correo+"/", HttpMethod.GET, null,String.class);
			
			String respuesta = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertEquals(respuesta, resEsperado);
	}
	
	@Test
	void pruebaCambia() {
		
		//resultado esperado
		Usuario usuarioEsperado = new Usuario();
		usuarioEsperado.setCorreo("am9uYXRhbi5pc3VpQG91dGxvb2suY29t");
		usuarioEsperado.setPassword("prueba");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioEsperado, headers);
		
		
		ResponseEntity<Usuario> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/index/cambioPassword",
						HttpMethod.POST, request,
						new ParameterizedTypeReference<Usuario>() {
						});
		
			Usuario respuesta = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertEquals(respuesta.getPassword(), usuarioEsperado.getPassword());
	}

}
