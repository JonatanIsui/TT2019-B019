package com.tt2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.List;
import java.util.ArrayList;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.hamcrest.Matchers.*;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tt2.dao.ArquitectoDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Administrador;
import com.tt2.entity.Arquitecto;
import com.tt2.entity.Usuario;
import com.tt2.restcontroller.ArquitectoRest;
import com.tt2.service.UsuarioBean;
import com.tt2.entity.Diccionario;



@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class PruebaUsuarioBean {
	
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @Autowired
	  private ObjectMapper objectMapper;
	  @Qualifier("objectMapper")
	  @MockBean
	  private UsuarioBean beanUsuario;
	
	@Test
	public void pruebaIniciarSesionCorrecto() throws Exception {
		//probamos iniciar sesion con contraseña correcta
		Usuario usuarioPrueba = new Usuario();
		usuarioPrueba.setCorreo("correo@prueba.com");
		usuarioPrueba.setPassword("12345");
		
		
		Administrador admin = new Administrador();
		admin.setNombreAdministrador("El Admin");
		
		usuarioPrueba.setAdministrador(admin);
		
		Mockito.when(beanUsuario.iniciarSesion("correo@prueba.com", "12345"))
		.thenReturn(usuarioPrueba);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/index/login")
    			.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
    			.characterEncoding("UTF-8").content("{\"errors\":{},\"correo\":\"isui@isui.com\",\"password\":\"isui\"}");
		
		 mockMvc.perform(builder).andExpect(status().isOk());
			
	}
	
	@Test
public void pruebaIniciarSesionInCorrecto() throws Exception {
		//probamos iniciar sesion con contraseña incorrecta
		Usuario usuarioPrueba = new Usuario();
		usuarioPrueba.setCorreo("correo@prueba.com");
		usuarioPrueba.setPassword("12345");
		
		
		Administrador admin = new Administrador();
		admin.setNombreAdministrador("El Admin");
		
		usuarioPrueba.setAdministrador(admin);
		
		Mockito.when(beanUsuario.iniciarSesion("correo@prueba.com", "12345"))
		.thenReturn(usuarioPrueba);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/index/login")
    			.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
    			.characterEncoding("UTF-8").content("{\"errors\":{},\"correo\":\"isui@isui.com\",\"password\":\"error\"}");
		
		 mockMvc.perform(builder).andExpect(status().isNoContent());
		
	}
	
	@Test
	public void pruebaEliminarUsuario() {
		
	}
	
	
	

}