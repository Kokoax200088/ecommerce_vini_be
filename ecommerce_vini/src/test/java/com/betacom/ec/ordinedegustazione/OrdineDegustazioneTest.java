package com.betacom.ec.ordinedegustazione;

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

import com.betacom.ec.dto.input.OrdineDegustazioneRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineDegustazioneTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	public void createOrdineDegustazioneTest() throws Exception {
		log.debug("createOrdineDegustazioneTest");
		OrdineDegustazioneRequest req = new OrdineDegustazioneRequest();
		req.setOrdineId(1);
		req.setDegustazioneId(1);
		req.setStatusId(1);
		req.setCantinaId(1);
		req.setQuantita(1);
		mockMvc.perform(post("/rest/api/ordine-degustazione/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void listOrdineDegustazioneTest() throws Exception {
		log.debug("listOrdineDegustazioneTest");
		mockMvc.perform(get("/rest/api/ordine-degustazione/list")
				).andExpect(status().isOk());
	}
}
