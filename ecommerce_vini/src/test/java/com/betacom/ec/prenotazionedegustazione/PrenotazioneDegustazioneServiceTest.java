package com.betacom.ec.prenotazionedegustazione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import com.betacom.ec.dto.output.PrenotazioneDegustazioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IPrenotazioneDegustazioneService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class PrenotazioneDegustazioneServiceTest {

    @Autowired private IPrenotazioneDegustazioneService pdS;

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
        log.debug("Setup DB isolato per PrenotazioneDegustazioneService");

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
        utenteReq.setEmail("mario.rossi@service.com");
        utenteReq.setIdRuolo(1);
        utenteReq.setPassword("password123");
        utenteReq.setDataNascita("01/01/2000");
        utenteC.create(utenteReq);

        VenditoreRequest vendReq = new VenditoreRequest();
        vendReq.setId(1);
        vendReq.setNome("Caio");
        vendReq.setCognome("Ilario");
        vendReq.setEmail("c.maio@service.com");
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
    public void createPrenotazioneDegustazioneTest() {
        log.debug("Create PrenotazioneDegustazione (Service)");
        try {
            PrenotazioneDegustazioneReq req = new PrenotazioneDegustazioneReq();
            req.setId_cantina(1);
            req.setId_degustazione(1);
            req.setId_ordine(1);
            req.setId_status(1);

            pdS.create(req);
            
            List<PrenotazioneDegustazioneDTO> list = pdS.listWithParameters(null, 1, 1, 1);
            assertNotNull(list);
            assertEquals(1, list.size());
        } catch (Exception e) {
            throw new AssertionError("Errore in createPrenotazioneDegustazioneTest: " + e.getMessage());
        }
    }

    @Test
    public void updatePrenotazioneDegustazioneTest() {
        log.debug("Update PrenotazioneDegustazione (Service)");
        try {
            PrenotazioneDegustazioneReq createReq = new PrenotazioneDegustazioneReq();
            createReq.setId_cantina(1);
            createReq.setId_degustazione(1);
            createReq.setId_ordine(1);
            createReq.setId_status(1);
            pdS.create(createReq);

            List<PrenotazioneDegustazioneDTO> list = pdS.listWithParameters(null, 1, 1, 1);
            Integer generatedId = list.get(0).getId();

            PrenotazioneDegustazioneReq updateReq = new PrenotazioneDegustazioneReq();
            updateReq.setId(generatedId);
            updateReq.setId_cantina(1);
            updateReq.setId_degustazione(1);
            updateReq.setId_ordine(1);
            updateReq.setId_status(1);

            pdS.update(updateReq);
            
            PrenotazioneDegustazioneDTO dto = pdS.getById(generatedId);
            assertNotNull(dto);
            assertEquals(generatedId, dto.getId());
        } catch (Exception e) {
            throw new AssertionError("Errore in updatePrenotazioneDegustazioneTest: " + e.getMessage());
        }
    }

    @Test
    public void deletePrenotazioneDegustazioneTest() {
        log.debug("Delete PrenotazioneDegustazione (Service)");
        try {
            PrenotazioneDegustazioneReq createReq = new PrenotazioneDegustazioneReq();
            createReq.setId_cantina(1);
            createReq.setId_degustazione(1);
            createReq.setId_ordine(1);
            createReq.setId_status(1);
            pdS.create(createReq);
            
            List<PrenotazioneDegustazioneDTO> list = pdS.listWithParameters(null, 1, 1, 1);
            Integer generatedId = list.get(0).getId();

            pdS.delete(generatedId);
            assertThrows(EcommerceVinoException.class, () -> pdS.getById(generatedId));
        } catch (Exception e) {
            throw new AssertionError("Errore in deletePrenotazioneDegustazioneTest: " + e.getMessage());
        }
    }
}