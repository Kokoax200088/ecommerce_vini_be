package com.betacom.ec.carrello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail; // Importato per gestire i fallimenti nel catch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
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

import com.betacom.ec.controllers.CarrelloController;
import com.betacom.ec.controllers.ClienteController;
import com.betacom.ec.controllers.ProdottoBoxController;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.ProdottoBoxReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.CarrelloDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.ProdottoBox;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.repository.IProdottoBoxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import tools.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelloTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	
	@Test
	@Order(1)
	@WithMockUser(roles = "USER")
	public void list() throws Exception {
		log.debug("list");

		MvcResult result = mockMvc.perform(get("/rest/api/cart/list"))
	            .andExpect(status().isOk())
	            .andReturn();
		  
		String json = result.getResponse().getContentAsString();
		
		List<CarrelloDTO> lS = objectMapper.readValue(
	            json,
	            new TypeReference<List<CarrelloDTO>>() {}
	    );
		
		assertFalse(lS.isEmpty());
		
		lS.forEach(s -> log.debug(s.toString()));

	}



	@Test
	@Order(2)
	@WithMockUser(roles = "USER")
	public void getbyid() throws Exception {
		mockMvc.perform(get("/rest/api/cart/getById")
	            .param("id", "1"))
	        .andExpect(status().isBadRequest())
	        .andExpect(jsonPath("$.msg").exists());

	}
}