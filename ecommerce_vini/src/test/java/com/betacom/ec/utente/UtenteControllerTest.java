package com.betacom.ec.utente;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;

import com.betacom.ec.controllers.UtenteController;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.UtenteDTO;
import com.betacom.ec.exception.EcommerceVinoException;

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
	public void createUtenteAdmin(){
		log.debug("createUtente admin role");
		
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
			
			UtenteDTO utente= (UtenteDTO)utenteController.getById(1).getBody();
			Assertions.assertThat(utente.getNome().equals("Luigi"));
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order (2)
	public void createUtenteAdmin2(){
		log.debug("createUtente admin role2");
		
		UtenteRequest req = new UtenteRequest();
		req.setNome("Luigi");
		req.setCognome("Mangino2");
		req.setDataNascita("21/11/2005");
		req.setEmail("luigi.mangino2@tiscali.net");
		req.setIdRuolo(2); //ROLE ADMIN
		req.setPassword("abete1");
		
		try {
			ResponseEntity<ResponseDTO> response = utenteController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			
			UtenteDTO utente= (UtenteDTO)utenteController.getById(1).getBody();
			Assertions.assertThat(utente.getNome().equals("Luigi"));
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(3)
	@WithMockUser(roles = "ADMIN")
	public void updateUtente() {
		log.debug("update utente test");
		
		UtenteRequest req = new UtenteRequest();
		req.setId(1);
		req.setCognome("manginUPDATE");
		
		try {
			MvcResult result =  mockMvc.perform(patch("/rest/api/utente/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(req))
					).andExpect(status().isOk()).andReturn();		
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(4)
	@WithMockUser(roles = "ADMIN")
	public void deleteUtente() {
		log.debug("delete utente test");
		
		try {
			mockMvc.perform(delete("/rest/api/utente/delete" + "/2")
					).andExpect(status().isOk());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
		
		assertThrows(EcommerceVinoException.class, () -> utenteController.getById(2));
	}
}
