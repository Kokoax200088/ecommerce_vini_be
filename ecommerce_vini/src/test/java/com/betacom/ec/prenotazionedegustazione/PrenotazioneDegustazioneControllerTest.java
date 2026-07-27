package com.betacom.ec.prenotazionedegustazione;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
import com.betacom.ec.controllers.OrdineController;
import com.betacom.ec.controllers.PrenotazioneDegustazioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.StatusController;
import com.betacom.ec.controllers.UtenteController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.input.PrenotazioneDegustazioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrenotazioneDegustazioneControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private UtenteController utenteC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private DegustazioneController degustazioneC;
    @Autowired private OrdineController ordineC;
    @Autowired private PrenotazioneDegustazioneController prenotazioneC;
    @Autowired private StatusController statusC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB inizio per PrenotazioneDegustazioneControllerTest", this.getClass().getName());

        RuoloRequest rUser = new RuoloRequest();
        rUser.setNome("user");
        rUser.setCanBuy(true);
        rUser.setCanManage(false);
        rUser.setCanSell(false);
        ruoloC.create(rUser);

        RuoloRequest rSeller = new RuoloRequest();
        rSeller.setNome("seller");
        rSeller.setCanManage(false);
        rSeller.setCanBuy(false);
        rSeller.setCanSell(true);
        ruoloC.create(rSeller);

        UtenteRequest utenteReq = new UtenteRequest();
        utenteReq.setNome("Mario");
        utenteReq.setCognome("Rossi");
        utenteReq.setEmail("mario.rossi@controller.com");
        utenteReq.setIdRuolo(1);
        utenteReq.setPassword("password123");
        utenteReq.setDataNascita("01/01/2000");
        utenteC.create(utenteReq);

        VenditoreRequest vendReq = new VenditoreRequest();
        vendReq.setNome("Caio");
        vendReq.setCognome("Ilario");
        vendReq.setEmail("c.maio@controller.com");
        vendReq.setIdRuolo(2);
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setNome("Cantina Test");
        cantinaReq.setVenditoreId(1);
        cantinaReq.setPosizione("Torino");
        cantinaC.create(cantinaReq);

        DegustazioneReq degReq = new DegustazioneReq();
        degReq.setNome("Degustazione Vini");
        degReq.setDescrizione("Degustazione Vini");
        degReq.setCantinaId(1);
        degReq.setDataInizio("20/08/2026 18:00:00"); 
        degReq.setDataFine("20/08/2026 20:00:00");
        degReq.setPrezzo(25.0);
        degustazioneC.create(degReq);

        StatusReq statusReq = new StatusReq();
        statusReq.setNome("CONFERMATO");
        statusReq.setDescrizione("Ordine confermato");
        statusC.create(statusReq);

        OrdineReq ordineReq = new OrdineReq();
        ordineReq.setData_ordine(LocalDate.now());
        ordineReq.setTotale(50.0);
        ordineReq.setId_utente(1);
        ordineReq.setId_status(1); 
        ordineReq.setIndirizzoDestinazione("Via Roma 1");
        ordineC.create(ordineReq);
        
        PrenotazioneDegustazioneReq prenotationReq = new PrenotazioneDegustazioneReq();
        prenotationReq.setId_cantina(1);
        prenotationReq.setId_degustazione(1);
        prenotationReq.setId_ordine(1);
        prenotationReq.setId_status(1); 
        prenotazioneC.create(prenotationReq);

        log.debug("Setup DB completo per PrenotazioneDegustazioneControllerTest");
    }

    @Test
    public void createPrenotazioneDegustazione() throws Exception {
        log.debug("Test: createPrenotazioneDegustazione (Controller)");
        
        PrenotazioneDegustazioneReq req = new PrenotazioneDegustazioneReq();
        req.setId_cantina(1);
        req.setId_degustazione(1);
        req.setId_ordine(1);
        req.setId_status(1);

        MvcResult result = mockMvc.perform(post("/rest/api/prenotazionedegustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void updatePrenotazioneDegustazione() throws Exception {
        log.debug("Test: updatePrenotazioneDegustazione (Controller)");

        PrenotazioneDegustazioneReq updateReq = new PrenotazioneDegustazioneReq();
        updateReq.setId(1);
        updateReq.setId_cantina(1);
        updateReq.setId_degustazione(1);
        updateReq.setId_ordine(1);
        updateReq.setId_status(1);

        MvcResult result = mockMvc.perform(patch("/rest/api/prenotazionedegustazione/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateReq)))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void getPrenotazioneDegustazioneById() throws Exception {
        log.debug("Test: getPrenotazioneDegustazioneById (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/prenotazionedegustazione/getPrenotazioneDegustazioneById")
                .param("id", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void listPrenotazioneDegustazione() throws Exception {
        log.debug("Test: listPrenotazioneDegustazione (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/prenotazionedegustazione/list")
                .param("id_cantina", "1")
                .param("id_degustazione", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void deletePrenotazioneDegustazione() throws Exception {
        log.debug("Test: deletePrenotazioneDegustazione (Controller)");
        
        MvcResult result = mockMvc.perform(delete("/rest/api/prenotazionedegustazione/delete/1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }
}