package com.betacom.ec.degustazione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import com.betacom.ec.dto.output.DegustazioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IDegustazioneService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class DegustazioneServiceTest {

    @Autowired private IDegustazioneService dS;

    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per Degustazione (Service)");
        
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
        vendReq.setEmail("venditore.degustazione@service.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.11);
        posReq.setLongitudine(22.22);
        posReq.setDescrizione("Posizione Degustazione Service");
        posizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
    }

    @Test
    public void createDegustazioneTest() {
        log.debug("Test: createDegustazione (Service)");
        try {
            DegustazioneReq req = new DegustazioneReq();
            req.setDescrizione("Degustazione Vini Rossi");
            req.setCantinaId(1);
            req.setDataInizio(LocalDateTime.of(2026, 7, 17, 18, 0, 0));
            req.setDataFine(LocalDateTime.of(2026, 7, 17, 20, 0, 0));
            req.setPrezzo(45.0);
            
            dS.create(req);
            
            List<DegustazioneDTO> list = dS.listWithParameters(null, "Degustazione Vini Rossi", null, null, null, null, null);
            assertNotNull(list);
            assertEquals(1, list.size());
            assertEquals(Double.valueOf(45.0), list.get(0).getPrezzo());
        } catch (Exception e) {
            throw new AssertionError("Errore in createDegustazioneTest: " + e.getMessage());
        }
    }

    @Test
    public void updateDegustazioneTest() {
        log.debug("Test: updateDegustazione (Service)");
        try {
            DegustazioneReq createReq = new DegustazioneReq();
            createReq.setDescrizione("Degustazione Vini Rossi");
            createReq.setCantinaId(1);
            createReq.setDataInizio(LocalDateTime.of(2026, 7, 17, 18, 0, 0));
            createReq.setDataFine(LocalDateTime.of(2026, 7, 17, 20, 0, 0));
            createReq.setPrezzo(45.0);
            dS.create(createReq);

            List<DegustazioneDTO> list = dS.listWithParameters(null, "Degustazione Vini Rossi", null, null, null, null, null);
            Integer generatedId = list.get(0).getId();

            DegustazioneReq updateReq = new DegustazioneReq();
            updateReq.setId(generatedId);
            updateReq.setDescrizione("Degustazione Vini Bianchi VIP");
            updateReq.setPrezzo(60.0);
            
            dS.update(updateReq);
            
            DegustazioneDTO dto = dS.getById(generatedId);
            assertEquals("Degustazione Vini Bianchi VIP", dto.getDescrizione());
            assertEquals(Double.valueOf(60.0), dto.getPrezzo());
        } catch (Exception e) {
            throw new AssertionError("Errore in updateDegustazioneTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteDegustazioneTest() {
        log.debug("Test: deleteDegustazione (Service)");
        try {
            DegustazioneReq createReq = new DegustazioneReq();
            createReq.setDescrizione("Degustazione Temporanea");
            createReq.setCantinaId(1);
            createReq.setDataInizio(LocalDateTime.of(2026, 7, 17, 18, 0, 0));
            createReq.setDataFine(LocalDateTime.of(2026, 7, 17, 20, 0, 0));
            createReq.setPrezzo(45.0);
            dS.create(createReq);
            
            List<DegustazioneDTO> list = dS.listWithParameters(null, "Degustazione Temporanea", null, null, null, null, null);
            Integer generatedId = list.get(0).getId();

            dS.delete(generatedId);
            assertThrows(EcommerceVinoException.class, () -> dS.getById(generatedId));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteDegustazioneTest: " + e.getMessage());
        }
    }
}