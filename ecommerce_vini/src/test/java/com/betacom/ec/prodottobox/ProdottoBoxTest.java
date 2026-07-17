package com.betacom.ec.prodottobox;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.ProdottoBoxReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottoBoxTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
	@Order(1)
	public void createRuoloUser() throws Exception{ 
		log.debug("createRuolo User");
		
		RuoloRequest req = new RuoloRequest();
		req.setId(1);
		req.setNome("user");
		req.setCanManage(false);
		req.setCanBuy(true);
		req.setCanSell(false);
		 mockMvc.perform(post("/rest/api/ruolo/create")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(req))
	                ).andExpect(status().isOk());
	}
    
    @Test
    @Order(2)
    public void createCliente() throws Exception {
        log.debug("createCliente Test");
        
        ClienteRequest req = new ClienteRequest();
        req.setNome("Mario");
		req.setCognome("Rossi");
		req.setDataNascita("21/11/2005");
		req.setEmail("mario.rossi@tiscali.net");
		req.setIdRuolo(1); 
		req.setPassword("abete1");
		
		req.setIndirizzo("via Roma, 1 Torino TO");
        
        mockMvc.perform(post("/rest/api/cliente/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }
    
    @Test
    @Order(3)
    public void createCarrello() throws Exception {
        log.debug("createCarrello Test");
		
        CarrelloReq req = new CarrelloReq();
        req.setId_cliente(1);
        req.setQuantità(0);
        req.setTotale(0.0); 		
        mockMvc.perform(post("/rest/api/cart/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void createRuoloSeller() throws Exception {
        log.debug("createRuolo Seller");
        
        RuoloRequest req = new RuoloRequest();
        req.setId(2);
        req.setNome("seller");
        req.setCanManage(false);
        req.setCanBuy(false);
        req.setCanSell(true);
        
        mockMvc.perform(post("/rest/api/ruolo/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void createVenditore() throws Exception {
        log.debug("createVenditore Test");
        
        VenditoreRequest req = new VenditoreRequest();
        req.setId(1);
        req.setNome("Caio Maio");
        req.setDataNascita("08/08/1996");
        req.setCognome("Ilario");
        req.setEmail("c.maio@gmail.com");
        req.setIdRuolo(2);
        req.setPartitaIva("A99");
        
        mockMvc.perform(post("/rest/api/venditore/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(6)
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
    @Order(7)
    public void createBox() throws Exception {
        log.debug("createBox Test");
        
        BoxReq req = new BoxReq();
        req.setId(1);
        req.setNome("Boxlandia");
        
        mockMvc.perform(post("/rest/api/box/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void createProdottoBox() throws Exception {
        log.debug("createProdottoBox Test");
        
        ProdottoBoxReq req = new ProdottoBoxReq();
        req.setId(1);
        req.setId_box(1);        
        req.setId_cantina(1);    
        req.setId_carrello(1);   
        req.setQuantità(3);
        
        mockMvc.perform(post("/rest/api/prodotto-box/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(9)
    public void updateProdottoBox() throws Exception {
        log.debug("updateProdottoBox Test");
        
        ProdottoBoxReq req = new ProdottoBoxReq();
        req.setId(1);
        req.setId_box(1);
        req.setId_cantina(1);   
        req.setId_carrello(1);   
        req.setQuantità(5);       
        
        mockMvc.perform(post("/rest/api/prodotto-box/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(10)
    public void deleteProdottoBox() throws Exception {
        log.debug("deleteProdottoBox Test");
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/api/prodotto-box/delete/" + "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").exists());
    }
}