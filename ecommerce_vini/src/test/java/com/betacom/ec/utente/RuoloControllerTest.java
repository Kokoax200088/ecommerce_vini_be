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
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.api.Assertions;

import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.RuoloDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc //chiediamo di fare una simulazione di mvc al sistema di test
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RuoloControllerTest {
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private RuoloController ruoloController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	public void createRuoloUser() { //crea user
		log.debug("createRuolo User");
		
		RuoloRequest req = new RuoloRequest();
		req.setNome("user");
		req.setCanManage(false);
		req.setCanBuy(true);
		req.setCanSell(false);
		
		try {
			ResponseEntity<ResponseDTO> response = ruoloController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(2)
	public void createRuoloAdmin() { //crea admin
		log.debug("createRuolo Admin");
		
		RuoloRequest req = new RuoloRequest();
		req.setNome("admin");
		req.setCanManage(true);
		req.setCanBuy(false);
		req.setCanSell(false);
		
		try {
			ResponseEntity<ResponseDTO> response = ruoloController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(3)
	public void createRuoloSeller() { //crea seller
		log.debug("createRuolo Seller");
		
		RuoloRequest req = new RuoloRequest();
		req.setNome("seller");
		req.setCanManage(false);
		req.setCanBuy(false);
		req.setCanSell(true);
		
		try {
			ResponseEntity<ResponseDTO> response = ruoloController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(4)
	public void getRuolo() throws Exception {
		log.debug("GetRuolo Test");
		
		try {
			ResponseEntity<Object> response = ruoloController.getById(1);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			RuoloDTO dto = (RuoloDTO)response.getBody();
			
			Assertions.assertThat(dto.getNome()).isEqualTo("user");

		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(5)
	public void createRuoloToBeDeleted() { //crea toBeDeleted
		log.debug("createRuolo toBeDeleted");
		
		RuoloRequest req = new RuoloRequest();
		req.setNome("deleting");
		req.setCanManage(false);
		req.setCanBuy(false);
		req.setCanSell(true);
		
		try {
			ResponseEntity<ResponseDTO> response = ruoloController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(6)
	public void deleteRuolo() { //delete ruolo
		log.debug("deleteRuolo");
		
		try {
			ResponseEntity<ResponseDTO> response = ruoloController.delete(4);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(7)
	public void listAllRuolo() { //listAll ruolo
		log.debug("listAllRuolo");
		
		try {
			ResponseEntity<Object> response = ruoloController.listAll();
			assertEquals(HttpStatus.OK, response.getStatusCode());
			
			@SuppressWarnings("unchecked") //il warning che dà è inutile, l'ho soppresso
			List<RuoloDTO> listRuolo = (List<RuoloDTO>) response.getBody();
			Assertions.assertThat(listRuolo.size()).isGreaterThan(2);
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
	@Test
	@Order(8)
	@WithMockUser(roles = "ADMIN")
	public void createRuoloError() {
		log.debug("createRuoloControllerError");
		
		RuoloRequest ruoloRequest = new RuoloRequest();
		ruoloRequest.setNome("evvove");
		ruoloRequest.setCanManage(null);
		//gli altri non li metto tanto è test di error
		
		try {
			mockMvc.perform(post("/rest/api/ruolo/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(ruoloRequest))  //trasformo il mio oggetto di richiesta in json
					).andExpect(status().isBadRequest());
//					.andExpect(jsonPath("$.msg").exists()); //per verificare il return contenga il tag msg
			
		} catch (Exception e) {
			throw new AssertionError("Errore in SocioController" + e.getMessage());
		}
	}
	
}
