package com.betacom.ec.ordinebox;

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

import com.betacom.ec.dto.input.OrdineBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineBoxTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	public void createOrdineBoxTest() throws Exception {
		log.debug("createOrdineBoxTest");
		OrdineBoxRequest req = new OrdineBoxRequest();
		req.setOrdineId(1);
		req.setBoxId(1);
		req.setStatusId(1);
		req.setCantinaId(1);
		req.setQuantita(1);
		mockMvc.perform(post("/rest/api/ordine-box/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void listOrdineBoxTest() throws Exception {
		log.debug("listOrdineBoxTest");
		mockMvc.perform(get("/rest/api/ordine-box/list")
				).andExpect(status().isOk());
	}
}
