package com.betacom.ec.degustazione;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.DegustazioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class DegustazioneControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private DegustazioneController degustazioneC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB inizio per DegustazioneControllerTest", this.getClass().getName());
        
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

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizione("indirizzo");
        cantinaC.create(cantinaReq);
        
        DegustazioneReq degReq = new DegustazioneReq();
        degReq.setNome("Degustazione Iniziale"); 
        degReq.setDescrizione("Degustazione per Update");
        degReq.setCantinaId(1);
        degReq.setDataInizio("17/07/2026 18:00:00");
        degReq.setDataFine("17/07/2026 20:00:00");
        degReq.setPrezzo(50.0);
        degustazioneC.create(degReq);

        log.debug("Setup DB completo per DegustazioneControllerTest");
    }

    @Test
    public void createDegustazione() throws Exception {
        log.debug("Test: createDegustazione (Controller)");
        
        DegustazioneReq req = new DegustazioneReq();
        req.setNome("Degustazione Vini Rossi"); 
        req.setDescrizione("Degustazione Controller Base");
        req.setCantinaId(1);
        req.setDataInizio("26/07/2026 18:00:00");
        req.setDataFine("26/07/2026 20:00:00");
        req.setPrezzo(50.0);

        MvcResult result = mockMvc.perform(post("/rest/api/degustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void updateDegustazione() throws Exception {
        log.debug("Test: updateDegustazione (Controller)");
        
        DegustazioneReq updateReq = new DegustazioneReq();
        updateReq.setId(1); 
        updateReq.setNome("Degustazione Aggiornata");
        updateReq.setDescrizione("Nuova descrizione aggiornata");
        updateReq.setCantinaId(1); 
        updateReq.setDataInizio("18/07/2026 18:00:00");
        updateReq.setDataFine("18/07/2026 20:00:00");
        updateReq.setPrezzo(55.0);

        MvcResult result = mockMvc.perform(patch("/rest/api/degustazione/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateReq)))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void getDegustazioneById() throws Exception {
        log.debug("Test: getDegustazioneById (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/degustazione/getDegustazioneById")
                .param("id", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void listDegustazione() throws Exception {
        log.debug("Test: listDegustazione (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/degustazione/list")
                .param("descrizione", "Degustazione"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void deleteDegustazione() throws Exception {
        log.debug("Test: deleteDegustazione (Controller)");
        
        MvcResult result = mockMvc.perform(delete("/rest/api/degustazione/delete/1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }
}