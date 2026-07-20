package com.betacom.ec.posizione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.PosizioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IPosizioneService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PosizioneServiceTest {

    @Autowired private IPosizioneService posS;

    @Autowired RuoloController ruoloC;
    @Autowired VenditoreController venditoreC;
    @Autowired CantinaController cantinaC;
    @Autowired PosizioneController PosizioneC;
    
    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup: Ruolo, Venditore, Cantina");
        
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
        vendReq.setEmail("c.maio.service@gmail.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("abete1");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.1111);
        posReq.setLongitudine(22.2222);
        posReq.setDescrizione("Torino");
        PosizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina di Prova Service");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
    }
    
    @Test
    public void createPosizioneTest() {
        try {
            PosizioneReq req = new PosizioneReq();
            req.setId(2);
            req.setLatitudine(45.5);
            req.setLongitudine(9.5);
            req.setDescrizione("Location Test");
            
            posS.create(req);
            
            List<PosizioneDTO> list = posS.listBySearchString("Location");
            assertNotNull(list);
            assertEquals(1, list.size());
            assertEquals("Location Test", list.get(0).getDescrizione());
        } catch (Exception e) {
            throw new AssertionError("Errore in createPosizioneTest: " + e.getMessage());
        }
    }

    @Test
    public void updatePosizioneTest() {
        try {
            PosizioneReq req = new PosizioneReq();
            req.setId(1);
            req.setLatitudine(46.0);
            req.setLongitudine(10.0);
            req.setDescrizione("New Location");
            
            posS.update(req);
            
            PosizioneDTO dto = posS.getById(1);
            assertEquals("New Location", dto.getDescrizione());
            assertEquals(Double.valueOf(46.0), dto.getLatitudine());
        } catch (Exception e) {
            throw new AssertionError("Errore in updatePosizioneTest: " + e.getMessage());
        }
    }

    @Test
    public void deletePosizioneTest() {
        try {
            posS.delete(1);
            assertThrows(EcommerceVinoException.class, () -> posS.getById(1));
        } catch (Exception e) {
            throw new AssertionError("Errore in deletePosizioneTest: " + e.getMessage());
        }
    }
}