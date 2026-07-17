package com.betacom.ec.prodottodegustazione;

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

import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.DegustazioneReq; 
import com.betacom.ec.dto.input.ProdottoDegustazioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdottoDegustazioneTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void createCliente() throws Exception {
        log.debug("createCliente Test");
        
        ClienteRequest req = new ClienteRequest();
        req.setId(1);
        req.setNome("Mario");
        req.setCognome("Rossi");
        req.setEmail("mario.rossi@gmail.com");
        
        mockMvc.perform(post("/rest/api/cliente/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }
    
    @Test
    @Order(2)
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
    @Order(3)
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
    @Order(4)
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
    @Order(5)
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
    @Order(6)
    public void createDegustazione() throws Exception {
        log.debug("createDegustazione Test");
        
        DegustazioneReq req = new DegustazioneReq();
        req.setId(1);
        req.setDescrizione("Degustazione Vini Rossi");
        req.setCantinaId(1);
        java.time.LocalDateTime inizio = java.time.LocalDateTime.of(2026, 7, 17, 18, 0, 0);
        java.time.LocalDateTime fine = java.time.LocalDateTime.of(2026, 7, 17, 20, 0, 0);
        
        req.setDataInizio(inizio);
        req.setDataFine(fine);
        req.setPrezzo(45.0);
        
        mockMvc.perform(post("/rest/api/degustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void createProdottoDegustazione() throws Exception {
        log.debug("createProdottoDegustazione Test");
        
        ProdottoDegustazioneReq req = new ProdottoDegustazioneReq();
        req.setId(1);
        req.setId_carrello(1);       
        req.setId_degustazione(1);   
        req.setId_cantina(1);        
        req.setQuantità(2);
        
        mockMvc.perform(post("/rest/api/prodotto-degustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void updateProdottoDegustazione() throws Exception {
        log.debug("updateProdottoDegustazione Test");
        
        ProdottoDegustazioneReq req = new ProdottoDegustazioneReq();
        req.setId(1);
        req.setId_carrello(1);
        req.setId_degustazione(1);   
        req.setId_cantina(1);        
        req.setQuantità(4);        
        
        mockMvc.perform(post("/rest/api/prodotto-degustazione/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(9)
    public void deleteProdottoDegustazione() throws Exception {
        log.debug("deleteProdottoDegustazione Test");
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/api/prodotto-degustazione/delete/" + "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").exists());
    }
}