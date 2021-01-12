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
import com.tt2.service.ProveedorBean;
import com.tt2.service.UsuarioBean;

@ExtendWith(SpringExtension.class)

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")

class PruebaIntegracionProveedor {

	final private static int port = 8080;
	final private static String baseUrl = "http://localhost:";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired 
	private ProveedorBean beanProveedor;
	
	@Test
	void pruebaGetCatalogo() {
		
		Proveedor nuevoProveedor = new Proveedor();
		nuevoProveedor.setId(7);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		List<String> expectedCatalogoList = Stream.of(
				"grava",
				"arena",
				"cemento",
				"mortero",
				"varilla",
				"ladrillo rojo",
				"adrillo block ligero",
				"ladrillo block pesado"
				).collect(Collectors.toList());
		
		HttpEntity<Proveedor> request = new HttpEntity<Proveedor>(nuevoProveedor, headers);
		
		
		ResponseEntity<List<Material>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/proveedor/catalogo",
						HttpMethod.POST, request,
						new ParameterizedTypeReference<List<Material>>() {
						});
			List<Material> listaRespuestaMaterial = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(listaRespuestaMaterial.size() > 6);
			
			assertTrue(listaRespuestaMaterial.stream().anyMatch((material) -> {
				return expectedCatalogoList.contains(material.getNombre());
			}));
			
	}
	
	@Test
	void pruebaEliminaMaterial() {
		
		Material nuevoMaterial = new Material();
		nuevoMaterial.setId(52);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Material> request = new HttpEntity<Material>(nuevoMaterial, headers);
		
		
		List<String> expectedCatalogoList = Stream.of(
				"grava",
				"arena",
				"cemento",
				"mortero",
				"varilla",
				"ladrillo rojo",
				"adrillo block ligero"
				).collect(Collectors.toList());
		
		ResponseEntity<List<Material>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/proveedor/eliminar",
						HttpMethod.POST, request,
						new ParameterizedTypeReference<List<Material>>() {
						});
		List<Material> listaRespuestaMaterial = responseEntity.getBody();
		assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
		assertTrue(listaRespuestaMaterial.size() == 7 );
		assertTrue(listaRespuestaMaterial.stream().anyMatch((material) -> {
			return expectedCatalogoList.contains(material.getNombre());
		}));

	}
	
	@Test
	void pruebaAgregaMaterial() {
		
		Material nuevoMaterial = new Material();
		nuevoMaterial.setCategoria("1234");
		nuevoMaterial.setClave("1234");
		nuevoMaterial.setCosto(12344);
		nuevoMaterial.setDescripcion("1234");
		nuevoMaterial.setNombre("ladrillo block pesado");
		
		Proveedor nuevo = new Proveedor();
		nuevo.setId(7);
		
		nuevoMaterial.setProveedor(nuevo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Material> request = new HttpEntity<Material>(nuevoMaterial, headers);
		
		
		List<String> expectedCatalogoList = Stream.of(
				"ladrillo block pesado",
				"grava",
				"arena",
				"cemento",
				"mortero",
				"varilla",
				"ladrillo rojo",
				"adrillo block ligero"
				).collect(Collectors.toList());
		
		ResponseEntity<List<Material>> responseEntity=
				this.restTemplate.exchange(baseUrl + port + "/proveedor/agregar",
						HttpMethod.POST, request,
						new ParameterizedTypeReference<List<Material>>() {
						});
		List<Material> listaRespuestaMaterial = responseEntity.getBody();
		assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
		assertTrue(listaRespuestaMaterial.size() == 8 );
		assertTrue(listaRespuestaMaterial.stream().anyMatch((material) -> {
			return expectedCatalogoList.contains(material.getNombre());
		}));
		
	}
		@Test
		void pruebaModificaMaterial() {
			
			Material nuevoMaterial = new Material();
			nuevoMaterial.setId(13);
			nuevoMaterial.setCategoria("Construccion");
			nuevoMaterial.setClave("bl1");
			nuevoMaterial.setCosto(12344);
			nuevoMaterial.setDescripcion("modificado");
			nuevoMaterial.setNombre("ladrillo block ligero");
			
			Proveedor nuevo = new Proveedor();
			nuevo.setId(7);
			
			nuevoMaterial.setProveedor(nuevo);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<Material> request = new HttpEntity<Material>(nuevoMaterial, headers);
			
			
			List<String> expectedCatalogoList = Stream.of(
					"ladrillo block pesado",
					"grava",
					"arena",
					"cemento",
					"mortero",
					"varilla",
					"ladrillo rojo",
					"adrillo block ligero"
					).collect(Collectors.toList());
			
			ResponseEntity<List<Material>> responseEntity=
					this.restTemplate.exchange(baseUrl + port + "/proveedor/modificar",
							HttpMethod.POST, request,
							new ParameterizedTypeReference<List<Material>>() {
							});
			List<Material> listaRespuestaMaterial = responseEntity.getBody();
			assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
			assertTrue(listaRespuestaMaterial.size() == 8 );
			assertTrue(listaRespuestaMaterial.stream().anyMatch((material) -> {
				return expectedCatalogoList.contains(material.getNombre());
			}));

			
			
	}
		
		@Test
		void pruebaArchivoIncorrecto() {
			//se manda un archivo que no corresponde al formato
			ArchivoModel nuevoArchivoModel = new ArchivoModel();
			nuevoArchivoModel.setId(7);
			nuevoArchivoModel.setCatalogo("data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,UEsDBBQABgAIAAAAIQBi7p1oXgEAAJAEAAATAAgCW0NvbnRlbnRfVHlwZXNdLnhtbCCiBAIooAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACslMtOwzAQRfdI/EPkLUrcskAINe2CxxIqUT7AxJPGqmNbnmlp/56J+xBCoRVqN7ESz9x7MvHNaLJubbaCiMa7UgyLgcjAVV4bNy/Fx+wlvxcZknJaWe+gFBtAMRlfX41mmwCYcbfDUjRE4UFKrBpoFRY+gOOd2sdWEd/GuQyqWqg5yNvB4E5W3hE4yqnTEOPRE9RqaSl7XvPjLUkEiyJ73BZ2XqVQIVhTKWJSuXL6l0u+cyi4M9VgYwLeMIaQvQ7dzt8Gu743Hk00GrKpivSqWsaQayu/fFx8er8ojov0UPq6NhVoXy1bnkCBIYLS2ABQa4u0Fq0ybs99xD8Vo0zL8MIg3fsl4RMcxN8bZLqej5BkThgibSzgpceeRE85NyqCfqfIybg4wE/tYxx8bqbRB+QERfj/FPYR6brzwEIQycAhJH2H7eDI6Tt77NDlW4Pu8ZbpfzL+BgAA//8DAFBLAwQUAAYACAAAACEAtVUwI/QAAABMAgAACwAIAl9yZWxzLy5yZWxzIKIEAiigAAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKySTU/DMAyG70j8h8j31d2QEEJLd0FIuyFUfoBJ3A+1jaMkG92/JxwQVBqDA0d/vX78ytvdPI3qyCH24jSsixIUOyO2d62Gl/pxdQcqJnKWRnGs4cQRdtX11faZR0p5KHa9jyqruKihS8nfI0bT8USxEM8uVxoJE6UchhY9mYFaxk1Z3mL4rgHVQlPtrYawtzeg6pPPm3/XlqbpDT+IOUzs0pkVyHNiZ9mufMhsIfX5GlVTaDlpsGKecjoieV9kbMDzRJu/E/18LU6cyFIiNBL4Ms9HxyWg9X9atDTxy515xDcJw6vI8MmCix+o3gEAAP//AwBQSwMEFAAGAAgAAAAhAOIKcpdgAwAANAgAAA8AAAB4bC93b3JrYm9vay54bWysVW1vnDgQ/n7S/QfEdwLmbQGFVOFNjdS0UbKX3odIlQMm+AKYMya7UdT/fmOzbJKmOu2lt2Jt7Bkenpl5xhx/2Hat9kD4SFkf6+jI0jXSl6yi/V2s/7EujEDXRoH7CresJ7H+SEb9w8nvvx1vGL+/ZexeA4B+jPVGiCEyzbFsSIfHIzaQHiw14x0WsOR35jhwgquxIUR0rWlblm92mPb6jBDxQzBYXdOSZKycOtKLGYSTFgugPzZ0GBe0rjwErsP8fhqMknUDQNzSlopHBaprXRmd3fWM49sWwt4iT9tyuHz4IwsGe3kTmN68qqMlZyOrxRFAmzPpN/Ejy0ToVQq2b3NwGJJrcvJAZQ33rLj/Tlb+Hst/BkPWL6MhkJbSSgTJeyeat+dm6yfHNW3J9SxdDQ/DZ9zJSrW61uJR5BUVpIr1FSzZhrza4NOQTLQFq+0ge6WbJ3s5X3CtIjWeWrEGIS/w0Bm+H9qe9ARhnLaC8B4LkrJegA53cf2q5hR22jBQuHZJ/p4oJ9BYoC+IFUZcRvh2vMCi0Sbexvqf0c3SCONNfpV+Ob9Zr+Gyb15IE7/tg/8gTlzKiE0IeaY13/8YPrDj0SLAC8E1uD/LPkERrvADlAQKX+069gxyjpxvfckj9O0pCcIEWVlqpJl/arhBbhmJn/mGvQqyNAxCN0ic7xAM96OS4Uk0u2pL6Fh3vZ+YzvF2sSArmmj1TOPJ2v0MOf8wLLbvMmB5rl1TshmfdSGX2vYr7Su2ifXQtyCox2Vpr3xYbpTxK61EA8IKAuky730k9K4BxshzHUXalsxi/QnZXu6HkICVjVaG6xSZkXh5ZuS5X2R+GDqWkyhG5gtK6gQFamrWeqX6j+wvjOCkloeryrGu8Ui+gp9VSNVwearEbQkil5NyDJFlh9KDbMWnUagZ9EUlO9c6XVmha1i540F5QtsIXMc2Ujezc2+VZ3niyfLID0D0fxyDSubR8mWRLBvMxZrj8h6+R5ekTvAIepoDAr4vySZekFgOUHQLVBguCkFLie8aXlY43gplae4Vz2Rl+PU7D6HAVE8TLCZoUNmbah3Jsdjt7jfreWNXpletF11mMu+7p//N8Qqib8mBzsX1gY7p5/P1udLGTwMwVYLlqGRhLmU5+QcAAP//AwBQSwMEFAAGAAgAAAAhAIE+lJfzAAAAugIAABoACAF4bC9fcmVscy93b3JrYm9vay54bWwucmVscyCiBAEooAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKxSTUvEMBC9C/6HMHebdhUR2XQvIuxV6w8IybQp2yYhM3703xsqul1Y1ksvA2+Gee/Nx3b3NQ7iAxP1wSuoihIEehNs7zsFb83zzQMIYu2tHoJHBRMS7Orrq+0LDppzE7k+ksgsnhQ45vgoJRmHo6YiRPS50oY0as4wdTJqc9Adyk1Z3su05ID6hFPsrYK0t7cgmilm5f+5Q9v2Bp+CeR/R8xkJSTwNeQDR6NQhK/jBRfYI8rz8Zk15zmvBo/oM5RyrSx6qNT18hnQgh8hHH38pknPlopm7Ve/hdEL7yim/2/Isy/TvZuTJx9XfAAAA//8DAFBLAwQUAAYACAAAACEAJVGW6mgDAADVCwAAGAAAAHhsL3dvcmtzaGVldHMvc2hlZXQxLnhtbJyW247aMBCG7yv1HaLcQ+KQAyBgRSCr7kWlqsdrEwxYm8SpbQ6rqu/ecQLEMS3NrrRsgH/mn2/sCfHk4ZRn1oFwQVkxtVHftS1SpGxNi+3U/vb1sTe0LSFxscYZK8jUfiHCfpi9fzc5Mv4sdoRICxwKMbV3UpZjxxHpjuRY9FlJClA2jOdYwke+dUTJCV5XSXnmeK4bOjmmhV07jHkXD7bZ0JQsWbrPSSFrE04yLIFf7GgpLm552sUux/x5X/ZSlpdgsaIZlS+VqW3l6fhpWzCOVxn0fUI+Tq0Thz8PXoNLmer7m0o5TTkTbCP74OzUzLftj5yRg9Or023/nWyQ73ByoGoDGyvvbUgouHp5jdngjWbh1UwtFx/v6Xpq/3KjcDH3gqQXBsNlz4/ncS8O5qiHkkUSJX6wCJLwtz2brCnssOrK4mQztedonIxsZzap5uc7JUehvbckXn0hGUklgRrIttR4rhh7VoFP8JULjqIKUI44lfRAFiTLwBg6FT/rGp4q4Fwr6O8v1R6rgf7ErRUWZMGyH3Qtd1ASbpw12eB9Jj+z4wdCtzsJ3wbQuJqc8fplSUQKIwsofS9QdVKWgSn8t3Kq7j0YOXyq4c+eo77vBdEQQbyV7oVk+aXaOb/OhAaqTLgez5let0zYlyoTrpfMqB8hdzSI7pf0z4lwbRKR74b/QQXXqiBcL3n+XVSnXqVqS5ZY4tmEs6MFtwoslyix+uFB43+uMiyvip1DMCy8gBk4zDx34hxgZ9OzGOuioS10DbXzli3Ttpbo2uCqOcB+bUCNXecGILhpwACJddFvgyx0DRndLVuiZ7TgVaul5bToYWK600NwQ2/UiXUxMOh17Ya+JZr0g7v0MLbd6SG4oW82s5qsWBdDg17XbuhbouGa+HfpYdy700NwQ29MR6yLkUGva8ic/ZZouCbBXfrwNfQQ3NAb0xHr4tCg17Ub+pZouCbhXfroNfQQ3NAb0xHr4sig17Ub+pZoZCbRXXp1mOv8mwPBDb2xvrEuInN0WqI5Oi3RWJRkeBd/9Bp8CG7wDcJYF5E5Oy3RxG+JJv7o7/j1iaJ+fJV4Sz5ivqWFsDKyqc4DsKO8PjC4fbW7rFSnBPUIXjEJT/3Lpx0cnwk8z9w+/PZtGJOXD+rUcj2Qz/4AAAD//wMAUEsDBBQABgAIAAAAIQCDTWzIVgcAAMggAAATAAAAeGwvdGhlbWUvdGhlbWUxLnhtbOxZW48bNRR+R+I/WPOe5jaTy6opyrVLu9tW3bSIR2/iZNz1jCPb2W2EKqHyxAsSEiBekHjjASGQQALxwo+p1IrLj+DYM8nYG4de2CJAu5FWGec7x8fnHH8+c3z1rYcJQ6dESMrTTlC9UgkQSSd8StN5J7g3HpVaAZIKp1PMeEo6wYrI4K1rb75xFe+pmCQEgXwq93AniJVa7JXLcgLDWF7hC5LCbzMuEqzgUczLU4HPQG/CyrVKpVFOME0DlOIE1I5BBk0Juj2b0QkJrq3VDxnMkSqpByZMHGnlJJexsNOTqkbIlewzgU4x6wQw05SfjclDFSCGpYIfOkHF/AXla1fLeC8XYmqHrCU3Mn+5XC4wPamZOcX8eDNpGEZho7vRbwBMbeOGzWFj2NjoMwA8mcBKM1tcnc1aP8yxFij76tE9aA7qVQdv6a9v2dyN9MfBG1CmP9zCj0Z98KKDN6AMH23ho167N3D1G1CGb2zhm5XuIGw6+g0oZjQ92UJXoka9v17tBjLjbN8Lb0fhqFnLlRcoyIZNdukpZjxVu3ItwQ+4GAFAAxlWNEVqtSAzPIE87mNGjwVFB3QeQ+ItcMolDFdqlVGlDv/1JzTfTETxHsGWtLYLLJFbQ9oeJCeCLlQnuAFaAwvy9Kefnjz+4cnjH5988MGTx9/mcxtVjtw+Tue23O9fffzHF++j377/8vdPPs2mPo+XNv7ZNx8++/mXv1IPKy5c8fSz75798N3Tzz/69etPPNq7Ah/b8DFNiES3yBm6yxNYoMd+cixeTmIcY+pI4Bh0e1QPVewAb60w8+F6xHXhfQEs4wNeXz5wbD2KxVJRz8w348QBHnLOelx4HXBTz2V5eLxM5/7JxdLG3cX41Dd3H6dOgIfLBdAr9ansx8Qx8w7DqcJzkhKF9G/8hBDP6t6l1PHrIZ0ILvlMoXcp6mHqdcmYHjuJVAjt0wTisvIZCKF2fHN4H/U48616QE5dJGwLzDzGjwlz3HgdLxVOfCrHOGG2ww+win1GHq3ExMYNpYJIzwnjaDglUvpkbgtYrxX0m8Aw/rAfslXiIoWiJz6dB5hzGzngJ/0YJwuvzTSNbezb8gRSFKM7XPngh9zdIfoZ4oDTneG+T4kT7ucTwT0gV9ukIkH0L0vhieV1wt39uGIzTHws0xWJw65dQb3Z0VvOndQ+IIThMzwlBN1722NBjy8cnxdG34iBVfaJL7FuYDdX9XNKJEGmrtmmyAMqnZQ9InO+w57D1TniWeE0wWKX5lsQdSd14ZTzUultNjmxgbcoFICQL16n3Jagw0ru4S6td2LsnF36WfrzdSWc+L3IHoN9+eBl9yXIkJeWAWJ/Yd+MMXMmKBJmjKHA8NEtiDjhL0T0uWrEll65mbtpizBAYeTUOwlNn1v8nCt7on+m7PEXMBdQ8PgV/51SZxel7J8rcHbh/oNlzQAv0zsETpJtzrqsai6rmuB/X9Xs2suXtcxlLXNZy/jevl5LLVOUL1DZFF0e0/NJdrZ8ZpSxI7Vi5ECaro+EN5rpCAZNO8r0JDctwEUMX/MGk4ObC2xkkODqHarioxgvoDVUNc3OucxVzyVacAkdIzNsmqnknG7Td1omh3yadTqrVd3VzFwosSrGK9FmHLpUKkM3mkX3bqPe9EPnpsu6NkDLvowR1mSuEXWPEc31IEThr4wwK7sQK9oeK1pa/TpU6yhuXAGmbaICr9wIXtQ7QRRmHWRoxkF5PtVxyprJ6+jq4FxopHc5k9kZACX2OgOKSLe1rTuXp1eXpdoLRNoxwko31wgrDWN4Ec6z0265X2Ss20VIHfO0K9a7oTCj2XodsdYkco4bWGozBUvRWSdo1CO4V5ngRSeYQccYviYLyB2p37owm8PFy0SJbMO/CrMshFQDLOPM4YZ0MjZIqCICMZp0Ar38TTaw1HCIsa1aA0L41xrXBlr5txkHQXeDTGYzMlF22K0R7ensERg+4wrvr0b81cFaki8h3Efx9Awds6W4iyHFomZVO3BKJVwcVDNvTinchG2IrMi/cwdTTrv2VZTJoWwcs0WM8xPFJvMMbkh0Y4552vjAesrXDA7dduHxXB+wf/vUff5RrT1nkWZxZjqsok9NP5m+vkPesqo4RB2rMuo279Sy4Lr2musgUb2nxHNO3Rc4ECzTiskc07TF2zSsOTsfdU27wILA8kRjh982Z4TXE6968oPc+azVB8S6rjSJby7N7VttfvwAyGMA94dLpqQJJdxZCwxFX3YDmdEGbJGHKq8R4RtaCtoJ3qtE3bBfi/qlSisalsJ6WCm1om691I2ienUYVSuDXu0RHCwqTqpRdmE/gisMtsqv7c341tV9sr6luTLhSZmbK/myMdxc3VdrztV9dg2PxvpmPkAUSOe9Rm3Urrd7jVK73h2VwkGvVWr3G73SoNFvDkaDftRqjx4F6NSAw269HzaGrVKj2u+XwkZFm99ql5phrdYNm93WMOw+yssYWHlGH7kvwL3Grmt/AgAA//8DAFBLAwQUAAYACAAAACEAeaGAbKQCAABSBgAADQAAAHhsL3N0eWxlcy54bWykVW1r2zAQ/j7YfxD67sp24ywJtsvS1FDoxqAd7Ktiy4moXowkZ87G/vtOdl4cOrbRfolO59Nzz91zUtKbTgq0Y8ZyrTIcXYUYMVXqiqtNhr8+FcEMI+uoqqjQimV4zyy+yd+/S63bC/a4ZcwhgFA2w1vnmgUhttwySe2VbpiCL7U2kjrYmg2xjWG0sv6QFCQOwymRlCs8ICxk+T8gkprntglKLRvq+JoL7vY9FkayXNxvlDZ0LYBqF01oibpoamLUmWOS3vsij+Sl0VbX7gpwia5rXrKXdOdkTmh5RgLk1yFFCQnji9o780qkCTFsx718OE9rrZxFpW6VAzGBqG/B4lnp76rwn7xziMpT+wPtqABPhEmellpogxxIB53rPYpKNkTcUsHXhvuwmkou9oM79o5e7UOc5NB77ySex2GxcIgLcWIVewLgyFOQzzGjCtigg/20byC9gkkbYPq4f0RvDN1HcTI6QPqEebrWpoLJPvfj6MpTwWoHRA3fbP3qdAO/a+0cqJ+nFacbrajwpQwgJwPKKZkQj376v9UX2F2NVCsL6e6rDMM98k04mlDIwRzwho3HH6MN2G+GRV19iQ+II9oXpE/pkdc7w5/9dRUwOQcItG65cFz9gTBgVt25BaFXwPmr1zfnlAU6UbGatsI9nT5m+Gx/YhVvZXyK+sJ32vUQGT7bD16paOpzsM49WBgvWFFreIZ/3i0/zFd3RRzMwuUsmFyzJJgny1WQTG6Xq1UxD+Pw9tfoAXjD9e/fqzyFi7WwAh4Jcyj2UOLj2Zfh0Wag388o0B5zn8fT8GMShUFxHUbBZEpnwWx6nQRFEsWr6WR5lxTJiHvyymciJFE0PDiefLJwXDLB1VGro0JjL4gE278UQY5KkPOfQf4bAAD//wMAUEsDBBQABgAIAAAAIQBdn1A3VgEAAG0DAAAUAAAAeGwvc2hhcmVkU3RyaW5ncy54bWx0k8tOwzAQRfdI/IPlPc2DCihKUtEi2BSEeO1dZ0gNtieMnSj8PQkVCMXp0mfmXs/1yNmyM5q1QE6hzXkyizkDK7FUtsr5y/PNyQVnzgtbCo0Wcv4Fji+L46PMOc96rXU533lfX0aRkzswws2wBttX3pCM8P2RqsjVBKJ0OwBvdJTG8VlkhLKcSWysz/npOWeNVZ8NrPcgXfAic6rIfHGPZkuQRb7IooHs6Vp4qJCUGBeuwUlStezzBBp0HsfwlkQbmFwR2ACuwYANDe6QPFDg+ypIaR2YbEQ5cGSP+B5o1qhFOUGt89TIqUgr9MBKYMmCaeUJ3Tjek5A4NBwY/rd8IESSMgOD7WChBVXBcEnapXE3j8f3/uVcaZQfbKOqiTcaNT2Am4ifxN1wyTzYphbtT/aasGxkuJgqGUtEQGRATEDagFBAtnWI9D8U9d+l+AYAAP//AwBQSwMEFAAGAAgAAAAhAGMNI55QAQAAbQIAABEACAFkb2NQcm9wcy9jb3JlLnhtbCCiBAEooAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIySTU7DMBSE90jcIfI+cX74k5WkEqCyaSUkikDsLPu1tYgdy3ZJ29twFi6Gk7QhqCxY2jPv88yT88lWVsEHGCtqVaAkilEAitVcqFWBnhfT8AYF1lHFaVUrKNAOLJqU52c504TVBh5NrcE4ATbwJGUJ0wVaO6cJxpatQVIbeYfy4rI2kjp/NCusKXunK8BpHF9hCY5y6ihugaEeiOiA5GxA6o2pOgBnGCqQoJzFSZTgH68DI+2fA50yckrhdtp3OsQdsznrxcG9tWIwNk0TNVkXw+dP8Ot89tRVDYVqd8UAlTlnhBmgrjblbCNs8FCr/ddnBfscj6R2jRW1bu43vhTAb3cn7lOHZ3dV+geABz4c6asclZfs7n4xRWUap3GYpGFyvYgTkl2SNHtrA/yab8P2F/IQ4x/ENFvEFyRLSXw9Ih4BZY5PPkj5DQAA//8DAFBLAwQUAAYACAAAACEAJwHspZEBAAAXAwAAEAAIAWRvY1Byb3BzL2FwcC54bWwgogQBKKAAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACcksFu2zAMhu8D9g6G7o2cbiiGQFZRpBty2LAASXvnZDrRJkuCxBjJ3mbPshcbbaOJs+3UG8mf+vWJoro/tq7oMGUbfCXms1IU6E2ord9V4mn76eaDKDKBr8EFj5U4YRb3+u0btU4hYiKLuWALnyuxJ4oLKbPZYwt5xrJnpQmpBeI07WRoGmvwMZhDi57kbVneSTwS+hrrm3g2FKPjoqPXmtbB9Hz5eXuKDKzVQ4zOGiB+pf5iTQo5NFR8PBp0Sk5FxXQbNIdk6aRLJaep2hhwuGRj3YDLqOSloFYI/dDWYFPWqqNFh4ZCKrL9yWO7FcU3yNjjVKKDZMETY/VtYzLELmZKehW+Qy5qLMzvX84cXFCS+0ZtCKdHprF9r+dDAwfXjb3ByMPCNenWksP8tVlDov+Az6fgA8OIfUEdr5ziDQ/ni/6yXoY2gj+xcI4+W/8jP8VteATCl6FeF9VmDwlr/ofz0M8FteJ5JtebLPfgd1i/9Pwr9CvwPO65nt/Nyncl/+6kpuRlo/UfAAAA//8DAFBLAQItABQABgAIAAAAIQBi7p1oXgEAAJAEAAATAAAAAAAAAAAAAAAAAAAAAABbQ29udGVudF9UeXBlc10ueG1sUEsBAi0AFAAGAAgAAAAhALVVMCP0AAAATAIAAAsAAAAAAAAAAAAAAAAAlwMAAF9yZWxzLy5yZWxzUEsBAi0AFAAGAAgAAAAhAOIKcpdgAwAANAgAAA8AAAAAAAAAAAAAAAAAvAYAAHhsL3dvcmtib29rLnhtbFBLAQItABQABgAIAAAAIQCBPpSX8wAAALoCAAAaAAAAAAAAAAAAAAAAAEkKAAB4bC9fcmVscy93b3JrYm9vay54bWwucmVsc1BLAQItABQABgAIAAAAIQAlUZbqaAMAANULAAAYAAAAAAAAAAAAAAAAAHwMAAB4bC93b3Jrc2hlZXRzL3NoZWV0MS54bWxQSwECLQAUAAYACAAAACEAg01syFYHAADIIAAAEwAAAAAAAAAAAAAAAAAaEAAAeGwvdGhlbWUvdGhlbWUxLnhtbFBLAQItABQABgAIAAAAIQB5oYBspAIAAFIGAAANAAAAAAAAAAAAAAAAAKEXAAB4bC9zdHlsZXMueG1sUEsBAi0AFAAGAAgAAAAhAF2fUDdWAQAAbQMAABQAAAAAAAAAAAAAAAAAcBoAAHhsL3NoYXJlZFN0cmluZ3MueG1sUEsBAi0AFAAGAAgAAAAhAGMNI55QAQAAbQIAABEAAAAAAAAAAAAAAAAA+BsAAGRvY1Byb3BzL2NvcmUueG1sUEsBAi0AFAAGAAgAAAAhACcB7KWRAQAAFwMAABAAAAAAAAAAAAAAAAAAfx4AAGRvY1Byb3BzL2FwcC54bWxQSwUGAAAAAAoACgCAAgAARiEAAAAA");
			
			
			String resultadoEsperado = "El catalogo no se puede agregar verifique que los campos no esten vacios o el nombre del material no este registrado ya";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			
			
			
			HttpEntity<ArchivoModel> request = new HttpEntity<ArchivoModel>(nuevoArchivoModel, headers);
			
			
			ResponseEntity<String> responseEntity = this.restTemplate
					.exchange(baseUrl + port + "/proveedor/archivo", HttpMethod.POST, request,String.class);
			
			
				
				assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
				assertTrue(responseEntity.getBody().contains(resultadoEsperado));
				

				
		}
		
		@Test
		void pruebaFormato() {
			
			String resultadoEsperado = beanProveedor.getFormato();
			

			
			ResponseEntity<String> responseEntity = this.restTemplate
					.exchange(baseUrl + port + "/proveedor/formato", HttpMethod.POST, null,String.class);
			
			
				
				assertEquals(HttpStatus.OK , responseEntity.getStatusCode());
				assertTrue(responseEntity.getBody().equals(resultadoEsperado));
				

				
		}
	
}