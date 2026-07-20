package com.betacom.ec.immaginedegustazione;

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
import com.betacom.ec.controllers.DegustazioneController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.input.ImmagineDegustazioneReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ImmagineDegustazioneControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;
    @Autowired private DegustazioneController degustazioneC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per ImmagineDegustazione (Controller)");
        
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
        vendReq.setEmail("venditore.immaginedegustazione@controller.com");
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

        DegustazioneReq degustazioneReq = new DegustazioneReq();
        degustazioneReq.setId(1);
        degustazioneReq.setDescrizione("Degustazione Base Controller");
        degustazioneReq.setCantinaId(cantinaReq.getId());
        degustazioneReq.setDataInizio(LocalDateTime.of(2026, 7, 20, 10, 0));
        degustazioneReq.setDataFine(LocalDateTime.of(2026, 7, 20, 12, 0));
        degustazioneReq.setPrezzo(30.0);
        degustazioneC.create(degustazioneReq);
    }

    @Test
    public void createImmagineDegustazione() throws Exception {
        log.debug("Test: createImmagineDegustazione (Controller)");
        
        ImmagineDegustazioneReq req = new ImmagineDegustazioneReq();
        req.setId_degustazione(1);

        mockMvc.perform(post("/rest/api/immagine-degustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateImmagineDegustazione() throws Exception {
        log.debug("Test: updateImmagineDegustazione (Controller)");
        
        ImmagineDegustazioneReq req = new ImmagineDegustazioneReq();
        req.setId(1);
        req.setId_degustazione(1);

        mockMvc.perform(patch("/rest/api/immagine-degustazione/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void getImmagineDegustazioneById() throws Exception {
        log.debug("Test: getImmagineDegustazioneById (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-degustazione/getImmagineDegustazioneById")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listImmagineDegustazione() throws Exception {
        log.debug("Test: listImmagineDegustazione (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-degustazione/list/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteImmagineDegustazione() throws Exception {
        log.debug("Test: deleteImmagineDegustazione (Controller)");
        
        mockMvc.perform(delete("/rest/api/immagine-degustazione/delete/1"))
                .andExpect(status().isOk());
    }
}