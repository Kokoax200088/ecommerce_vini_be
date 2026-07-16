package com.betacom.ec.carrello;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.betacom.ec.controllers.CarrelloController;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelloTest {
private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private CarrelloController carrelloController;
	
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	@Order(1)
	public void createCarrello() {
		log.debug("createCarrello Test");
		
		CarrelloReq req = new CarrelloReq();
		req.setId_cliente(1);
		req.setListaBox(null);
		req.setListaDegustazione(null);
		req.setListaProdotti(null);
		req.setQuantità(0);
		req.setTotale(0.0);
		
		try {
			ResponseEntity<ResponseDTO> response = carrelloController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			new AssertionError("Errore: " + e.getMessage());
		}
	}
}
