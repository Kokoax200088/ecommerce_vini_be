package com.betacom.ec.cantina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.CantinaDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.ICantinaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class CantinaServiceTest {

    @Autowired private ICantinaService cantinaS;

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
    public void createCantinaTest() {
        log.debug("Test: createCantina (Service)");
        try {
            PosizioneReq posReq2 = new PosizioneReq();
            posReq2.setId(2);
            posReq2.setLatitudine(45.0);
            posReq2.setLongitudine(9.0);
            posReq2.setDescrizione("Posizione Nuova Cantina");
            posizioneC.create(posReq2);

            CantinaReq req = new CantinaReq();
            req.setNome("Cantina Nuova");
            req.setVenditoreId(1);
            req.setPosizioneId(2);
            
            cantinaS.create(req);
            
            List<CantinaDTO> list = cantinaS.listBySearchString("Nuova", null);
            assertNotNull(list);
            assertEquals(1, list.size());
            assertEquals("Cantina Nuova", list.get(0).getNome());
        } catch (Exception e) {
            throw new AssertionError("Errore in createCantinaTest: " + e.getMessage());
        }
    }

    @Test
    public void updateCantinaTest() {
        log.debug("Test: updateCantina (Service)");
        try {
            CantinaReq req = new CantinaReq();
            req.setId(1);
            req.setNome("Cantina Modificata");
            req.setVenditoreId(1);
            req.setPosizioneId(1);
            
            cantinaS.update(req);
            
            CantinaDTO dto = cantinaS.getById(1);
            assertEquals("Cantina Modificata", dto.getNome());
        } catch (Exception e) {
            throw new AssertionError("Errore in updateCantinaTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteCantinaTest() {
        log.debug("Test: deleteCantina (Service)");
        try {
            cantinaS.delete(1);
            assertThrows(EcommerceVinoException.class, () -> cantinaS.getById(1));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteCantinaTest: " + e.getMessage());
        }
    }
}