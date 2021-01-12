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
import com.tt2.entity.Diccionario;
import com.tt2.entity.Material;
import com.tt2.entity.Proveedor;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.entity.Usuario;
import com.tt2.model.ArchivoModel;
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

class PruebaIntegracionArquitecto {

	final private static int port = 8080;
	final private static String baseUrl = "http://localhost:";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired 
	private ArquitectoBean beanArquitecto;
	
	@Autowired
	private ConsulBean consulBean;
	
	@Test
	void pruebaRegistroArquitecto() {
		
		Arquitecto nuevoArqui = new Arquitecto();
		nuevoArqui.setApellido("arquiNnueeevo");
		nuevoArqui.setDireccion("testdirection");
		nuevoArqui.setNombre("LUISSSSS");
		nuevoArqui.setTelefono("44521313");
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setArquitecto(nuevoArqui);
		nuevoUsuario.setCorreo("correo@correo.com");
		nuevoUsuario.setPassword("12345");
		

		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		

		HttpEntity<Usuario> request = new HttpEntity<Usuario>(nuevoUsuario, headers);
		

		
		ResponseEntity<Usuario> responseEntity = this.restTemplate
				.postForEntity(baseUrl + port + "/arquitecto/registroArquitecto", request ,Usuario.class);
		
		Usuario respuestUsuario = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertEquals(respuestUsuario.getCorreo(),nuevoUsuario.getCorreo());
		
	}
	
	@Test
	void pruebaGetDiccionario() {
		
		List<String> expectedIDList = Stream.of(
				"test","palabra 1","palabra 2","pruebaaaaaaaaaaa"
				).collect(Collectors.toList());
		
		ResponseEntity<List<Diccionario>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/arquitecto/diccionario",
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
			assertTrue(listaRespuestaUsuarios.size() == 2);
			
			assertTrue(listaRespuestaUsuarios.stream().anyMatch((usuario) -> {
				return expectedIDList.contains(usuario.getCorreo());
			}));
			
	}
	
	
	@Test
	void pruebaGetUsuario() {
		
		Arquitecto nuevoArqui = new Arquitecto();
		nuevoArqui.setApellido("123");
		nuevoArqui.setDireccion("123");
		nuevoArqui.setNombre("123");
		nuevoArqui.setTelefono("123");
		nuevoArqui.setId(17);
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setArquitecto(nuevoArqui);
		nuevoUsuario.setCorreo("12345@12345.com");
		nuevoUsuario.setPassword("12345");
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		

		HttpEntity<Arquitecto> request = new HttpEntity<Arquitecto>(nuevoArqui, headers);
		

		
		ResponseEntity<Usuario> responseEntity = this.restTemplate
				.postForEntity(baseUrl + port + "/arquitecto/perfilUsuario", request ,Usuario.class);
		
		Usuario respuestUsuario = responseEntity.getBody();
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertEquals(respuestUsuario.getCorreo(),nuevoUsuario.getCorreo());
		
		
		
		
	}
	
	
	@Test
	void pruebaEliminaPerfil() {
		
		Arquitecto nuevoArqui = new Arquitecto();
		nuevoArqui.setApellido("123");
		nuevoArqui.setDireccion("123");
		nuevoArqui.setNombre("123");
		nuevoArqui.setTelefono("123");
		nuevoArqui.setId(51);
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setArquitecto(nuevoArqui);
		nuevoUsuario.setCorreo("12345@12345.com");
		nuevoUsuario.setPassword("12345");
		nuevoUsuario.setId(50);
		
		String resultadoEsperado = "Su perfil ha sido eliminado correctamente";
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		
		
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(nuevoUsuario, headers);
		
		
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange(baseUrl + port + "/arquitecto/eliminarPerfil", HttpMethod.POST, request,String.class);
		
		
			
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(responseEntity.getBody().contains(resultadoEsperado));
		
	}
	
	

}