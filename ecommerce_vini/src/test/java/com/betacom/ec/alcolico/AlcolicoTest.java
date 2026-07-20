package com.betacom.ec.alcolico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.betacom.ec.dto.input.AlcolicoReq;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlcolicoTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	public void createAlcolicoTest() throws Exception {
		log.debug("createAlcolicoTest");
		AlcolicoReq req = new AlcolicoReq();
		req.setId_venditore(1);
		req.setNome("TestVino");
		req.setId_tipologia_alcolico(1);
		req.setId_colore(1);
		req.setPrezzo(10.0);
		mockMvc.perform(post("/rest/api/alcolico/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void listAlcolicoTest() throws Exception {
		log.debug("listAlcolicoTest");
		mockMvc.perform(get("/rest/api/alcolico/list")
				).andExpect(status().isOk());
	}
}
