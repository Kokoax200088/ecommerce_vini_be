package com.betacom.ec.cantinaalcolico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.AlcolicoController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.CantinaAlcolicoReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.CantinaAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.ICantinaAlcolicoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class CantinaAlcolicoServiceTest {

    @Autowired private ICantinaAlcolicoService cantinaAlcolicoS;

    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;
    @Autowired private AlcolicoController alcolicoC;
    @Autowired private TipologiaAlcolicoController tipologiaC;
    @Autowired private ColoreController coloreC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per CantinaAlcolico (Service)");
        
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
        vendReq.setEmail("venditore.cantinaalcolico@service.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.11);
        posReq.setLongitudine(22.22);
        posReq.setDescrizione("Posizione CantinaAlcolico Base");
        posizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
        
        TipologiaAlcolicoReq tReq = new TipologiaAlcolicoReq();
        tReq.setId(1);
        tReq.setNome("Vino");
        tReq.setDescrizione("Vino Test");
        tipologiaC.create(tReq);
        
        ColoreReq coReq = new ColoreReq();
        coReq.setId(1);
        coReq.setNome("Rosso");
        coReq.setDescrizione("Vino Rosso");
        coloreC.create(coReq);
        
        AlcolicoReq aReq = new AlcolicoReq();
        aReq.setId_alcolico(1);
        aReq.setAnnata(2022);
        aReq.setDescrizione("Vino Test Service");
        aReq.setId_venditore(vendReq.getId());
        aReq.setGradazione(12);
        aReq.setNome("Vino Base");
        aReq.setPrezzo(20.0);
        aReq.setId_tipologia_alcolico(1);
        aReq.setId_colore(1);
        alcolicoC.create(aReq);
    }
    
    @Test
    public void createCantinaAlcolicoTest() {
        log.debug("Test: createCantinaAlcolico (Service)");
        try {
            CantinaAlcolicoReq req = new CantinaAlcolicoReq();
            req.setCantinaId(1);
            req.setAlcolicoId(1);
            req.setQuantita(50);
            
            cantinaAlcolicoS.create(req);
            
            List<CantinaAlcolicoDTO> list = cantinaAlcolicoS.listBySearchString(1, 1);
            assertNotNull(list);
            assertEquals(1, list.size());
            assertEquals(Integer.valueOf(50), list.get(0).getQuantita());
        } catch (Exception e) {
            throw new AssertionError("Errore in createCantinaAlcolicoTest: " + e.getMessage());
        }
    }

    @Test
    public void updateCantinaAlcolicoTest() {
        log.debug("Test: updateCantinaAlcolico (Service)");
        try {
            CantinaAlcolicoReq createReq = new CantinaAlcolicoReq();
            createReq.setCantinaId(1);
            createReq.setAlcolicoId(1);
            createReq.setQuantita(50);
            cantinaAlcolicoS.create(createReq);

            List<CantinaAlcolicoDTO> list = cantinaAlcolicoS.listBySearchString(1, 1);
            Integer generatedId = list.get(0).getId();

            CantinaAlcolicoReq updateReq = new CantinaAlcolicoReq();
            updateReq.setId(generatedId);
            updateReq.setCantinaId(1);
            updateReq.setAlcolicoId(1);
            updateReq.setQuantita(120);
            
            cantinaAlcolicoS.update(updateReq);
            
            CantinaAlcolicoDTO dto = cantinaAlcolicoS.getById(generatedId);
            assertEquals(Integer.valueOf(120), dto.getQuantita());
        } catch (Exception e) {
            throw new AssertionError("Errore in updateCantinaAlcolicoTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteCantinaAlcolicoTest() {
        log.debug("Test: deleteCantinaAlcolico (Service)");
        try {
            CantinaAlcolicoReq createReq = new CantinaAlcolicoReq();
            createReq.setCantinaId(1);
            createReq.setAlcolicoId(1);
            createReq.setQuantita(50);
            cantinaAlcolicoS.create(createReq);
            
            List<CantinaAlcolicoDTO> list = cantinaAlcolicoS.listBySearchString(1, 1);
            Integer generatedId = list.get(0).getId();

            cantinaAlcolicoS.delete(generatedId);
            assertThrows(EcommerceVinoException.class, () -> cantinaAlcolicoS.getById(generatedId));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteCantinaAlcolicoTest: " + e.getMessage());
        }
    }
}