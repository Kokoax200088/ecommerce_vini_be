package com.betacom.ec.prenotazionedegustazione;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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
import com.betacom.ec.controllers.OrdineController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.StatusController;
import com.betacom.ec.controllers.UtenteController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.input.PosizioneReq;
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
public class PrenotazioneDegustazioneControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private UtenteController utenteC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;
    @Autowired private DegustazioneController degustazioneC;
    @Autowired private StatusController statusC;
    @Autowired private OrdineController ordineC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per PrenotazioneDegustazione (Controller)");

        RuoloRequest rUser = new RuoloRequest();
        rUser.setId(1);
        rUser.setNome("user");
        rUser.setCanBuy(true);
        rUser.setCanManage(false);
        rUser.setCanSell(false);
        ruoloC.create(rUser);

        RuoloRequest rSeller = new RuoloRequest();
        rSeller.setId(2);
        rSeller.setNome("seller");
        rSeller.setCanManage(false);
        rSeller.setCanBuy(false);
        rSeller.setCanSell(true);
        ruoloC.create(rSeller);

        UtenteRequest utenteReq = new UtenteRequest();
        utenteReq.setId(1);
        utenteReq.setNome("Mario");
        utenteReq.setCognome("Rossi");
        utenteReq.setEmail("mario.rossi@controller.com");
        utenteReq.setIdRuolo(1);
        utenteReq.setPassword("password123");
        utenteReq.setDataNascita("01/01/2000");
        utenteC.create(utenteReq);

        VenditoreRequest vendReq = new VenditoreRequest();
        vendReq.setId(1);
        vendReq.setNome("Caio");
        vendReq.setCognome("Ilario");
        vendReq.setEmail("c.maio@controller.com");
        vendReq.setIdRuolo(2);
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.11);
        posReq.setLongitudine(22.22);
        posReq.setDescrizione("Torino");
        posizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Test");
        cantinaReq.setVenditoreId(1);
        cantinaReq.setPosizioneId(1);
        cantinaC.create(cantinaReq);

        DegustazioneReq degReq = new DegustazioneReq();
        degReq.setId(1);
        degReq.setDescrizione("Degustazione Vini");
        degReq.setCantinaId(1);
        degReq.setDataInizio(java.time.LocalDateTime.now().plusDays(1));
        degReq.setDataFine(java.time.LocalDateTime.now().plusDays(1).plusHours(2));
        degReq.setPrezzo(25.0);
        degustazioneC.create(degReq);

        StatusReq statusReq = new StatusReq();
        statusReq.setId(1);
        statusReq.setNome("CONFERMATO");
        statusReq.setDescrizione("Ordine confermato");
        statusC.create(statusReq);

        OrdineReq ordineReq = new OrdineReq();
        ordineReq.setId(1);
        ordineReq.setData_ordine(LocalDate.now());
        ordineReq.setTotale(50.0);
        ordineReq.setId_utente(1);
        ordineReq.setId_status(1);
        ordineReq.setIndirizzoDestinazione("Via Roma 1");
        ordineC.create(ordineReq);
    }

    @Test
    public void createPrenotazioneDegustazione() throws Exception {
        log.debug("Test: createPrenotazioneDegustazione (Controller)");
        
        PrenotazioneDegustazioneReq req = new PrenotazioneDegustazioneReq();
        req.setId_cantina(1);
        req.setId_degustazione(1);
        req.setId_ordine(1);
        req.setId_status(1);

        mockMvc.perform(post("/rest/api/prenotazionedegustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePrenotazioneDegustazione() throws Exception {
        log.debug("Test: updatePrenotazioneDegustazione (Controller)");
        
        PrenotazioneDegustazioneReq createReq = new PrenotazioneDegustazioneReq();
        createReq.setId_cantina(1);
        createReq.setId_degustazione(1);
        createReq.setId_ordine(1);
        createReq.setId_status(1);
        mockMvc.perform(post("/rest/api/prenotazionedegustazione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)));

        PrenotazioneDegustazioneReq updateReq = new PrenotazioneDegustazioneReq();
        updateReq.setId(1);
        updateReq.setId_cantina(1);
        updateReq.setId_degustazione(1);
        updateReq.setId_ordine(1);
        updateReq.setId_status(1);

        mockMvc.perform(patch("/rest/api/prenotazionedegustazione/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateReq)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPrenotazioneDegustazioneById() throws Exception {
        log.debug("Test: getPrenotazioneDegustazioneById (Controller)");
        
        mockMvc.perform(get("/rest/api/prenotazionedegustazione/getPrenotazioneDegustazioneById")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listPrenotazioneDegustazione() throws Exception {
        log.debug("Test: listPrenotazioneDegustazione (Controller)");
        
        mockMvc.perform(get("/rest/api/prenotazionedegustazione/list")
                .param("id_cantina", "1")
                .param("id_degustazione", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePrenotazioneDegustazione() throws Exception {
        log.debug("Test: deletePrenotazioneDegustazione (Controller)");
        
        mockMvc.perform(delete("/rest/api/prenotazionedegustazione/delete/1"))
                .andExpect(status().isOk());
    }
}