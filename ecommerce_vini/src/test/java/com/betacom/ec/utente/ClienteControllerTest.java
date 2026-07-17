package com.betacom.ec.utente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.betacom.ec.controllers.ClienteController;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.output.ResponseDTO;
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
			
			Cliente cliente= (Cliente)clienteController.getById(1).getBody();
			Assertions.assertThat(cliente.getUtente().getNome().equals("Mario"));
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
}
