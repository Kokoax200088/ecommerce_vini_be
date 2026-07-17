package com.betacom.ec.ratingalcolico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.AlcolicoController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.RatingAlcolicoController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.RatingAlcolicoReq;
import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.ICantinaRepository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RatingAlcolicoTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private RatingAlcolicoController rAcontroller;
	

    @Autowired
    private MockMvc mockMvc;
	
    @Test
    @Order(1)
    public void createAlcolico() throws Exception {
        log.debug("createAlcolico Test");
		
        AlcolicoReq req = new AlcolicoReq();
        req.setId_alcolico(1);
        req.setAnnata(1998);
        req.setDescrizione("vino buono"); 
        req.setId_venditore(1);
        req.setGradazione(17);
        req.setNome("Barolo");
        req.setPrezzo(25.0);
        req.setId_tipologia_alcolico(1);
        req.setId_colore(1);
        mockMvc.perform(post("/rest/api/alcolico/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }
    
    @Test
    @Order(2)
    public void createCantina() throws Exception {
        log.debug("createCantina Test");
		
        CantinaReq req = new CantinaReq();
        req.setId(1);
        req.setNome("Villa Turistica");
        req.setVenditoreId(1);
        mockMvc.perform(post("/rest/api/cantina/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }
	
	 @Test
	    @Order(3)
	    public void createRatingAlcolico() throws Exception {
	        log.debug("createRatingAlcolico Test");
			
	        RatingAlcolicoReq req = new RatingAlcolicoReq();
	        req.setId(1);
	        req.setId_cliente(1);
	        req.setId_cantina(1); 
	        req.setId_alcolico(1);
	        req.setValutazione(2.0);
	        req.setCommento("Vino rosso di ottima annata, peccato per i test e per il nostr DB lungo");
	        mockMvc.perform(post("/rest/api/rating-alcolico/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(req))
					).andExpect(status().isOk());
	    }
	 

	 @Test
	    @Order(4)
	    public void getRating() throws Exception {
	        log.debug("createRatingCantina Test");
	        
	        ResponseEntity<?> resp = rAcontroller.getById(1);
			assertEquals(HttpStatus.OK, resp.getStatusCode());
			RatingAlcolicoDTO rC = (RatingAlcolicoDTO)resp.getBody();
	    }
	 
	 @Test
		@Order(5)
		public void listAllController() throws Exception {
			log.debug("listAll");

			MvcResult result = mockMvc.perform(get("/rest/api//rating-alcolico/list"))
		            .andExpect(status().isOk())
		            .andReturn();
			  
			String json = result.getResponse().getContentAsString();
			
			List<RatingAlcolicoDTO> lS = objectMapper.readValue(
		            json,
		            new TypeReference<List<RatingAlcolicoDTO>>() {}
		    );
			
			assertFalse(lS.isEmpty());
			
			lS.forEach(s -> log.debug(s.toString()));
		}
	 
	 @Test
		@Order(6)
		public void delete() throws Exception{
			log.debug("delete");
			
			mockMvc.perform(MockMvcRequestBuilders.delete("/rest/api/rating-alcolico/delete/" +  "1"))
		            .andExpect(status().isOk())
		            .andExpect(jsonPath("$.msg").exists());
			  
		}

}
