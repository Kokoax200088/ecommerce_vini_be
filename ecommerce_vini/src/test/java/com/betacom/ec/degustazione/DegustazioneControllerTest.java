package com.betacom.ec.degustazione;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

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
import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional 
public class DegustazioneControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per Degustazione (Controller)");
        
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
        vendReq.setEmail("venditore.degustazione@controller.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.11);
        posReq.setLongitudine(22.22);
        posReq.setDescrizione("Posizione Degustazione Controller");
        posizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
    }

    @Test
    public void createDegustazione() throws Exception {
        log.debug("Test: createDegustazione (Controller)");
        
        DegustazioneReq req = new DegustazioneReq();
        req.setId(1);
        req.setDescrizione("Degustazione Controller Base");
        req.setCantinaId(1);
        req.setDataInizio(LocalDateTime.of(2026, 7, 17, 18, 0, 0));
        req.setDataFine(LocalDateTime.of(2026, 7, 17, 20, 0, 0));
        req.setPrezzo(50.0);

        mockMvc.perform(post("/rest/api/degustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateDegustazione() throws Exception {
        log.debug("Test: updateDegustazione (Controller)");
        
        DegustazioneReq createReq = new DegustazioneReq();
        createReq.setDescrizione("Degustazione per Update");
        createReq.setCantinaId(1);
        createReq.setDataInizio(LocalDateTime.of(2026, 7, 17, 18, 0, 0));
        createReq.setDataFine(LocalDateTime.of(2026, 7, 17, 20, 0, 0));
        createReq.setPrezzo(50.0);
        
        mockMvc.perform(post("/rest/api/degustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)));

        DegustazioneReq req = new DegustazioneReq();
        req.setId(1); 
        req.setDescrizione("Degustazione Aggiornata");
        req.setPrezzo(55.0);

        mockMvc.perform(patch("/rest/api/degustazione/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void getDegustazioneById() throws Exception {
        log.debug("Test: getDegustazioneById (Controller)");
        
        DegustazioneReq createReq = new DegustazioneReq();
        createReq.setDescrizione("Degustazione per Get");
        createReq.setCantinaId(1);
        createReq.setDataInizio(LocalDateTime.of(2026, 7, 17, 18, 0, 0));
        createReq.setDataFine(LocalDateTime.of(2026, 7, 17, 20, 0, 0));
        createReq.setPrezzo(50.0);
        
        mockMvc.perform(post("/rest/api/degustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)));

        mockMvc.perform(get("/rest/api/degustazione/getDegustazioneById")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listDegustazione() throws Exception {
        log.debug("Test: listDegustazione (Controller)");
        
        mockMvc.perform(get("/rest/api/degustazione/list")
                .param("descrizione", "Degustazione"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteDegustazione() throws Exception {
        log.debug("Test: deleteDegustazione (Controller)");
        
        DegustazioneReq createReq = new DegustazioneReq();
        createReq.setDescrizione("Degustazione per Delete");
        createReq.setCantinaId(1);
        createReq.setDataInizio(LocalDateTime.of(2026, 7, 17, 18, 0, 0));
        createReq.setDataFine(LocalDateTime.of(2026, 7, 17, 20, 0, 0));
        createReq.setPrezzo(50.0);
        
        mockMvc.perform(post("/rest/api/degustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)));

        mockMvc.perform(delete("/rest/api/degustazione/delete")
                .param("id", "1"))
                .andExpect(status().isOk());
    }
}