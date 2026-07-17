package com.betacom.ec.carrello;

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

import com.betacom.ec.controllers.RatingAlcolicoController;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.RatingAlcolicoReq;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RatingAlcolicoTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private RatingAlcolicoController rAcontroller;
	

    @Autowired
    private MockMvc mockMvc;
	
	
	 @Test
	    @Order(1)
	    public void createRatingAlcolico() throws Exception {
	        log.debug("createRatingAlcolico Test");
			
	        RatingAlcolicoReq req = new RatingAlcolicoReq();
	        req.setId_cliente(1);
	        req.setId_cantina(null); 
	        req.setId_alcolico(null);
	        req.setValutazione(2.0);
	        req.setCommento("Vino rosso di ottima annata, peccato per i test e per il nostr DB lungo");
	        mockMvc.perform(post("/rest/api/rating-alcolico/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(req))
					).andExpect(status().isOk());
	    }

}
