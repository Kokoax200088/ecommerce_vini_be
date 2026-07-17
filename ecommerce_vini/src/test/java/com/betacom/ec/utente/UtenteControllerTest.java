package com.betacom.ec.utente;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;

import com.betacom.ec.controllers.UtenteController;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.models.Utente;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteControllerTest {
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private UtenteController utenteController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order (1)
	public void createUtenteUser(){
		log.debug("createUtente user role");
		
		UtenteRequest req = new UtenteRequest();
		req.setNome("Luigi");
		req.setCognome("Mangino");
		req.setDataNascita("21/11/2005");
		req.setEmail("luigi.mangino@tiscali.net");
		req.setIdRuolo(2); //ROLE ADMIN
		req.setPassword("abete1");
		
		try {
			ResponseEntity<ResponseDTO> response = utenteController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			
			Utente utente= (Utente)utenteController.getById(1).getBody();
			Assertions.assertThat(utente.getNome().equals("Mario"));
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
}
