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
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
			new AssertionError("Errore: " + e.getMessage());
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
			new AssertionError("Errore: " + e.getMessage());
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
			new AssertionError("Errore: " + e.getMessage());
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
			new AssertionError("Errore: " + e.getMessage());
		}
	}
	
}
