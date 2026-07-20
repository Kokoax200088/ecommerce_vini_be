package com.betacom.ec.utente;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ClienteDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.VenditoreDTO;
import com.betacom.ec.exception.EcommerceVinoException;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VenditoreControllerTest {
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private VenditoreController venditoreController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order (1)
	public void createVenditore (){
		log.debug("createCliente");
		
		VenditoreRequest req = new VenditoreRequest();
		req.setNome("Mario");
		req.setCognome("Rossi");
		req.setDataNascita("21/11/2005");
		req.setEmail("mario.rossi@tiscali.net");
		req.setIdRuolo(3); //ROLE SELLER
		req.setPassword("abete1");
		
		req.setPartitaIva("IT3435315N34");
		
		try {
			ResponseEntity<ResponseDTO> response = venditoreController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			
//			here I just check if can get the clienteDTO object, everything else is part of other stuff I tried
			ResponseEntity<Object> responseEntity = venditoreController.getById(1);
//			ClienteDTO cliente= (ClienteDTO) responseEntity.getBody();
//			Assertions.assertThat(cliente.getIndirizzo()).isEqualTo("via Roma, 1 Torino TO");
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order (2)
	public void updateVenditore() {
		log.debug("update venditore test");
		
		ClienteRequest req = new ClienteRequest();
		req.setId(1);
		req.setIndirizzo("indirizzo updated");
		
		try {
			MvcResult result = mockMvc.perform(patch("/rest/api/venditore/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(req))
					).andExpect(status().isOk()).andReturn();
			
			String json = result.getResponse().getContentAsString();
			ResponseDTO dto = objectMapper.readValue(json, ResponseDTO.class);
			
//			ResponseEntity<ResponseDTO> response = clienteController.update(req);
//			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(3)
	public void listTest() {
		log.debug("test cliente list");
		
		try {
			ResponseEntity<Object> response = venditoreController.list(null);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			List<VenditoreDTO> listSocio = (List<VenditoreDTO>) response.getBody(); //non ho capito il warning
			Assertions.assertThat(listSocio.size()).isGreaterThan(0);
			
//			listSocio.forEach(item -> log.debug(item.toString()));
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order (4)
	public void deleteVenditoreTest() throws Exception {
		log.debug("deleteVenditoreTest");
		
		mockMvc.perform(delete("/rest/api/venditore/delete" + "/1")
				).andExpect(status().isOk());
		
		assertThrows(EcommerceVinoException.class, () -> venditoreController.getById(1));
	}
	
	@Test
	@Order (5)
	public void createVenditorePlus (){
		log.debug("createCliente");
		
		VenditoreRequest req = new VenditoreRequest();
		req.setNome("Mario");
		req.setCognome("Kart");
		req.setDataNascita("21/11/2005");
		req.setEmail("mario.rossi@tiscali.net");
		req.setIdRuolo(3); //ROLE SELLER
		req.setPassword("abete1");
		
		req.setPartitaIva("IT3435365T34");
		
		try {
			ResponseEntity<ResponseDTO> response = venditoreController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			
//			here I just check if can get the clienteDTO object, everything else is part of other stuff I tried
			ResponseEntity<Object> responseEntity = venditoreController.getById(2);
//			ClienteDTO cliente= (ClienteDTO) responseEntity.getBody();
//			Assertions.assertThat(cliente.getIndirizzo()).isEqualTo("via Roma, 1 Torino TO");
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
}
