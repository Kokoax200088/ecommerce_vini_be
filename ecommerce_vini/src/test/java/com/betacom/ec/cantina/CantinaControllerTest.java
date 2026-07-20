package com.betacom.ec.cantina;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CantinaControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per Cantina (Controller)");
        
        RuoloRequest rSeller = new RuoloRequest();
        rSeller.setId(1);
        rSeller.setNome("seller");
        rSeller.setCanManage(false);
        rSeller.setCanBuy(false);
        rSeller.setCanSell(true);
        ruoloC.create(rSeller);

        VenditoreRequest vendReq = new VenditoreRequest();
        vendReq.setId(1);
        vendReq.setNome("Caio");
        vendReq.setCognome("Ilario");
        vendReq.setEmail("venditore.cantina@controller.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.11);
        posReq.setLongitudine(22.22);
        posReq.setDescrizione("Posizione Base Controller");
        posizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
    }

    @Test
    public void createCantina() throws Exception {
        log.debug("Test: createCantina (Controller)");
        
        PosizioneReq posReq2 = new PosizioneReq();
        posReq2.setId(2);
        posReq2.setLatitudine(45.0);
        posReq2.setLongitudine(9.0);
        posReq2.setDescrizione("Posizione Nuova Controller");
        posizioneC.create(posReq2);

        CantinaReq req = new CantinaReq();
        req.setId(2);
        req.setNome("Cantina Controller Nuova");
        req.setVenditoreId(1);
        req.setPosizioneId(2);

        mockMvc.perform(post("/rest/api/cantina/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCantina() throws Exception {
        log.debug("Test: updateCantina (Controller)");
        
        CantinaReq req = new CantinaReq();
        req.setId(1);
        req.setNome("Cantina Controller Aggiornata");
        req.setVenditoreId(1);
        req.setPosizioneId(1);

        mockMvc.perform(put("/rest/api/cantina/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void getCantinaById() throws Exception {
        log.debug("Test: getCantinaById (Controller)");
        mockMvc.perform(get("/rest/api/cantina/get")
				.param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listCantina() throws Exception {
        log.debug("Test: listCantina (Controller)");
        mockMvc.perform(get("/rest/api/cantina/list")
                .param("nomeCantina", "Base"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCantina() throws Exception {
        log.debug("Test: deleteCantina (Controller)");
        mockMvc.perform(delete("/rest/api/cantina/remove")
        						.param("id", "1"))
                .andExpect(status().isOk());
    }
}