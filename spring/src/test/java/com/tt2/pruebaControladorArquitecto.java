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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tt2.dao.ArquitectoDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Arquitecto;
import com.tt2.entity.Usuario;
import com.tt2.restcontroller.ArquitectoRest;
import com.tt2.service.ArquitectoBean;
import com.tt2.entity.Diccionario;


@SpringBootTest
@AutoConfigureMockMvc

class pruebaControladorArquitecto {
	
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @Autowired
	  private ObjectMapper objectMapper;
	  
	  @MockBean
	  private ArquitectoBean beanArquitecto;
	
	@Test
	public void pruebaAgregarArquitecto() throws Exception {
		
		  Usuario usuarioPrueba = new Usuario();
		  Arquitecto arquitectoPrueba = new Arquitecto();
		  arquitectoPrueba.setNombre("PRUEBA");
		  arquitectoPrueba.setApellido("APELLIDO PRUEBA");
		  arquitectoPrueba.setTelefono("123456789");
		  arquitectoPrueba.setDireccion("DIRECCION DE PRUEBA");
		  
		  
		  usuarioPrueba.setArquitecto(arquitectoPrueba);
		  usuarioPrueba.setCorreo("usuario@prueba.com");
		  usuarioPrueba.setPassword("prueba");
		
		  Mockito.when(beanArquitecto.registroArquitecto(Mockito.any(Usuario.class)))
		  .thenReturn(usuarioPrueba);
		  
		  MockHttpServletRequestBuilder builder = 
				  MockMvcRequestBuilders.post("/arquitecto/registroArquitecto")
				  .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				  .characterEncoding("UTF-8").content(this.objectMapper.writeValueAsBytes(usuarioPrueba));
		  
		  mockMvc.perform(builder).andExpect(status().isOk())
		  .andExpect(jsonPath("$.correo", is("usuario@prueba.com")))
		  .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(usuarioPrueba)));
		
	}
	
	@Test
	public void pruebaDiccionario() throws Exception{

		ArrayList<Diccionario> listaDiccionario = new ArrayList<Diccionario>();
		Diccionario dic1= new Diccionario();
		dic1.setDefinicion("definicion uno 1");
		dic1.setNombre("nombre uno 1");
		Diccionario dic2= new Diccionario();
		dic2.setDefinicion("definicion dos 22");
		dic2.setNombre("nombre dos 22");
		
		listaDiccionario.add(dic1);
		listaDiccionario.add(dic2);
		
		Mockito.when(beanArquitecto.getDiccionario()).thenReturn(listaDiccionario);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/arquitecto/diccionario").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].nombre", is("nombre uno 1"))).andExpect(jsonPath("$[0].definicion", is("definicion uno 1")))
		.andExpect(jsonPath("$[1].nombre", is("nombre dos 22"))).andExpect(jsonPath("$[1].definicion", is("definicion dos 22")));
		
	}
	

}
