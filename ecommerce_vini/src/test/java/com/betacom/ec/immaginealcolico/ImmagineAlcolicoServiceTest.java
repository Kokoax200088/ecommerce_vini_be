package com.betacom.ec.immaginealcolico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.AlcolicoController;
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.ImmagineAlcolicoReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ImmagineAlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IImmagineAlcolicoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class ImmagineAlcolicoServiceTest {

    @Autowired private IImmagineAlcolicoService immS;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private AlcolicoController alcolicoC;
    @Autowired private TipologiaAlcolicoController tipologiaC;
    @Autowired private ColoreController coloreC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per ImmagineAlcolico (Service)");
        
        RuoloRequest rSeller = new RuoloRequest();
        rSeller.setId(1);
        rSeller.setNome("seller");
        rSeller.setCanManage(false);
        rSeller.setCanBuy(false);
        rSeller.setCanSell(true);
        ruoloC.create(rSeller);

        VenditoreRequest vReq = new VenditoreRequest();
        vReq.setId(1);
        vReq.setNome("Caio");
        vReq.setCognome("Ilario");
        vReq.setEmail("venditore.immagine@service.com");
        vReq.setIdRuolo(rSeller.getId());
        vReq.setPartitaIva("A99");
        vReq.setPassword("password123");
        vReq.setDataNascita("08/08/1996");
        venditoreC.create(vReq);
        
        TipologiaAlcolicoReq tReq = new TipologiaAlcolicoReq();
        tReq.setId(1);
        tReq.setNome("Vino");
        tReq.setDescrizione("Vino Test");
        tipologiaC.create(tReq);
        
        ColoreReq cReq = new ColoreReq();
        cReq.setId(1);
        cReq.setNome("Rosso");
        cReq.setDescrizione("Vino Rosso");
        coloreC.create(cReq);

        AlcolicoReq aReq = new AlcolicoReq();
        aReq.setId_alcolico(1);
        aReq.setImmagine("immagine_vino_controller.jpg");
        aReq.setId_caratteristiche(null);
        aReq.setProvenienza("Italia");
        aReq.setAnnata(2023);
        aReq.setDescrizione("Vino Test Immagine Controller");
        aReq.setId_venditore(vReq.getId());
        aReq.setGradazione(13);
        aReq.setNome("Vino Controller");
        aReq.setPrezzo(20.0);
        aReq.setId_tipologia_alcolico(1);
        aReq.setId_colore(1);
        alcolicoC.create(aReq);
    }
    
    @Test
    public void createImmagineAlcolicoTest() {
        log.debug("Test: createImmagineAlcolico (Service)");
        try {
            ImmagineAlcolicoReq req = new ImmagineAlcolicoReq();
            req.setId_alcolico(1);
            
            // Simula un file 
            MockMultipartFile mockFile = new MockMultipartFile(
                    "file", 
                    "bottiglia.jpg", 
                    "image/jpeg", 
                    "contenuto fittizio".getBytes());
            req.setFile(mockFile);
            
            immS.create(req);
            
            List<ImmagineAlcolicoDTO> list = immS.listBySearch(1);
            assertNotNull(list);
            assertEquals(1, list.size());
        } catch (Exception e) {
            throw new AssertionError("Errore in createImmagineAlcolicoTest: " + e.getMessage());
        }
    }

    @Test
    public void updateImmagineAlcolicoTest() {
        log.debug("Test: updateImmagineAlcolico (Service)");
        try {
            ImmagineAlcolicoReq createReq = new ImmagineAlcolicoReq();
            createReq.setId_alcolico(1);
            MockMultipartFile mockFile1 = new MockMultipartFile("file", "bottiglia.jpg", "image/jpeg", "contenuto".getBytes());
            createReq.setFile(mockFile1);
            immS.create(createReq);

            List<ImmagineAlcolicoDTO> list = immS.listBySearch(1);
            Integer generatedId = list.get(0).getId();

            ImmagineAlcolicoReq updateReq = new ImmagineAlcolicoReq();
            updateReq.setId(generatedId);
            updateReq.setId_alcolico(1);
            MockMultipartFile mockFile2 = new MockMultipartFile("file", "bottiglia_aggiornata.jpg", "image/jpeg", "nuovo contenuto".getBytes());
            updateReq.setFile(mockFile2);
            
            immS.update(updateReq);
            
            ImmagineAlcolicoDTO dto = immS.getById(generatedId);
            assertNotNull(dto.getUrl());
        } catch (Exception e) {
            throw new AssertionError("Errore in updateImmagineAlcolicoTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteImmagineAlcolicoTest() {
        log.debug("Test: deleteImmagineAlcolico (Service)");
        try {
            ImmagineAlcolicoReq createReq = new ImmagineAlcolicoReq();
            createReq.setId_alcolico(1);
            MockMultipartFile mockFile = new MockMultipartFile("file", "bottiglia.jpg", "image/jpeg", "contenuto".getBytes());
            createReq.setFile(mockFile);
            immS.create(createReq);
            
            List<ImmagineAlcolicoDTO> list = immS.listBySearch(1);
            Integer generatedId = list.get(0).getId();

            immS.delete(generatedId);
            assertThrows(EcommerceVinoException.class, () -> immS.getById(generatedId));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteImmagineAlcolicoTest: " + e.getMessage());
        }
    }
}