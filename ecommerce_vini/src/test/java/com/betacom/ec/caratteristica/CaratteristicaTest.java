package com.betacom.ec.caratteristica;

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

import com.betacom.ec.dto.input.CaratteristicaReq;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CaratteristicaTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	public void createCaratteristicaTest() throws Exception {
		log.debug("createCaratteristicaTest");
		CaratteristicaReq req = new CaratteristicaReq();
		req.setNome("Corposo");
		req.setDescrizione("testDescrizione");
		mockMvc.perform(post("/rest/api/caratteristica/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void listCaratteristicaTest() throws Exception {
		log.debug("listCaratteristicaTest");
		mockMvc.perform(get("/rest/api/caratteristica/list")
				).andExpect(status().isOk());
	}
}
