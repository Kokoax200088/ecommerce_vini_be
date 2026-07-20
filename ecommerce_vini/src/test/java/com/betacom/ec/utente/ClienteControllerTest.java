package com.betacom.ec.utente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.betacom.ec.controllers.ClienteController;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.output.ClienteDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.models.Cliente;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc //chiediamo di fare una simulazione di mvc al sistema di test
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteControllerTest {
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private ClienteController clienteController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order (1)
	public void createCliente (){
		log.debug("createCliente");
		
		ClienteRequest req = new ClienteRequest();
		req.setNome("Mario");
		req.setCognome("Rossi");
		req.setDataNascita("21/11/2005");
		req.setEmail("mario.rossi@tiscali.net");
		req.setIdRuolo(1); //ROLE USER
		req.setPassword("abete1");
		
		req.setIndirizzo("via Roma, 1 Torino TO");
		
		try {
			ResponseEntity<ResponseDTO> response = clienteController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			
//			here I just check if can get the clienteDTO object, everything else is part of other stuff I tried
			ResponseEntity<Object> responseEntity = clienteController.getById(1);
//			ClienteDTO cliente= (ClienteDTO) responseEntity.getBody();
//			Assertions.assertThat(cliente.getIndirizzo()).isEqualTo("via Roma, 1 Torino TO");
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order (2)
	@WithMockUser(roles = "ADMIN")
	public void updateCliente() {
		log.debug("update cliente test");
		
		ClienteRequest req = new ClienteRequest();
		req.setId(1);
		req.setIndirizzo("indirizzo updated");
		
		try {
			MvcResult result = mockMvc.perform(patch("/rest/api/cliente/update")
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
			ResponseEntity<Object> response = clienteController.list(null);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			List<ClienteDTO> listSocio = (List<ClienteDTO>) response.getBody(); //non ho capito il warning
			Assertions.assertThat(listSocio.size()).isGreaterThan(0);
			
//			listSocio.forEach(item -> log.debug(item.toString()));
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order (4)
	@WithMockUser(roles = "ADMIN")
	public void deleteClienteTest() throws Exception {
		log.debug("deleteClienteTest");
		
		mockMvc.perform(delete("/rest/api/cliente/delete" + "/1")
				).andExpect(status().isOk());
		
		assertThrows(EcommerceVinoException.class, () -> clienteController.getById(1));
	}
}
