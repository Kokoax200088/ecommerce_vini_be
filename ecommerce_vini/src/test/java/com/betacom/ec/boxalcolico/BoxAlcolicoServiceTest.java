package com.betacom.ec.boxalcolico;

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
import com.betacom.ec.controllers.BoxController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.BoxAlcolicoReq;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.BoxAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IBoxAlcolicoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class BoxAlcolicoServiceTest {

    @Autowired private IBoxAlcolicoService baS;

    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private BoxController boxC;
    @Autowired private AlcolicoController alcolicoC;
    @Autowired private PosizioneController PosizioneC;
    @Autowired private ColoreController coloreC;
    @Autowired private TipologiaAlcolicoController tipologiaC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per BoxAlcolicoService");

        RuoloRequest rSeller = new RuoloRequest();
        rSeller.setId(1);
        rSeller.setNome("seller");
        rSeller.setCanSell(true);
        ruoloC.create(rSeller);

        VenditoreRequest vReq = new VenditoreRequest();
        vReq.setId(1);
        vReq.setNome("Caio");
        vReq.setCognome("Ilario");
        vReq.setEmail("c.maio@gmail.com");
        vReq.setIdRuolo(rSeller.getId());
        vReq.setPartitaIva("A99");
        vReq.setPassword("password123");
        vReq.setDataNascita("08/08/1996");
        venditoreC.create(vReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.1111);
        posReq.setLongitudine(22.2222);
        posReq.setDescrizione("Torino Service");
        PosizioneC.create(posReq);

        
        CantinaReq cReq = new CantinaReq();
        cReq.setId(1);
        cReq.setNome("Cantina Test");
        cReq.setVenditoreId(vReq.getId());
        cReq.setPosizioneId(posReq.getId());
        cantinaC.create(cReq);

        BoxReq bReq = new BoxReq();
        bReq.setId(1);
        bReq.setNome("Box Test");
        bReq.setSconto(10.0);
        bReq.setCantinaId(cReq.getId());
        boxC.create(bReq);
        
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
        aReq.setAnnata(2020);
        aReq.setDescrizione("Vino Rosso");
        aReq.setId_venditore(vReq.getId());
        aReq.setGradazione(13);
        aReq.setNome("Merlot");
        aReq.setPrezzo(15.0);
        aReq.setId_tipologia_alcolico(1);
        aReq.setId_colore(1);
        alcolicoC.create(aReq);
    }

    @Test
    public void createBoxAlcolicoTest() {
        log.debug("Create BoxAlcolico (Service)");
        try {
            BoxAlcolicoReq req = new BoxAlcolicoReq();
            req.setId(1);
            req.setBoxId(1);
            req.setAlcolicoId(1);
            req.setQuantita(12);

            baS.create(req);
            
            List<BoxAlcolicoDTO> list = baS.listWithParameters(12, null, null, null, null);
            assertNotNull(list);
            assertEquals(1, list.size());
            assertEquals(Integer.valueOf(12), list.get(0).getQuantita());
        } catch (Exception e) {
            throw new AssertionError("Errore in createBoxAlcolicoTest: " + e.getMessage());
        }
    }

    @Test
    public void updateBoxAlcolicoTest() {
        log.debug("Update BoxAlcolico (Service)");
        try {
            BoxAlcolicoReq createReq = new BoxAlcolicoReq();
            createReq.setId(1);
            createReq.setBoxId(1);
            createReq.setAlcolicoId(1);
            createReq.setQuantita(12);
            baS.create(createReq);

            BoxAlcolicoReq req = new BoxAlcolicoReq();
            req.setId(1);
            req.setBoxId(1);
            req.setAlcolicoId(1);
            req.setQuantita(25);
            baS.update(req);
            
            BoxAlcolicoDTO dto = baS.getById(1);
            assertEquals(Integer.valueOf(25), dto.getQuantita());
            
        } catch (Exception e) {
            throw new AssertionError("Errore in updateBoxAlcolicoTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteBoxAlcolicoTest() {
        log.debug("Delete BoxAlcolico (Service)");
        try {
            BoxAlcolicoReq createReq = new BoxAlcolicoReq();
            createReq.setId(1);
            createReq.setBoxId(1);
            createReq.setAlcolicoId(1);
            createReq.setQuantita(12);
            baS.create(createReq);

            baS.delete(1);
            assertThrows(EcommerceVinoException.class, () -> baS.getById(1));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteBoxAlcolicoTest: " + e.getMessage());
        }
    }
}