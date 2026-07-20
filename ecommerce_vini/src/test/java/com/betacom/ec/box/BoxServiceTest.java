package com.betacom.ec.box;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.betacom.ec.controllers.CarrelloController;
import com.betacom.ec.controllers.ClienteController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.BoxDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.models.Posizione;
import com.betacom.ec.repository.IPosizioneRepository;
import com.betacom.ec.services.interfaces.IBoxService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class BoxServiceTest {

    @Autowired private IBoxService boxS;

    @Autowired private RuoloController ruoloC;
    @Autowired private ClienteController clienteC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private PosizioneController PosizioneC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB inizio per BoxService");

        RuoloRequest rUser = new RuoloRequest();
        rUser.setId(1);
        rUser.setNome("user");
        rUser.setCanBuy(true);
        rUser.setCanManage(false);
        rUser.setCanSell(false);
        ruoloC.create(rUser);

        ClienteRequest clienteReq = new ClienteRequest();
        clienteReq.setId(1);
        clienteReq.setNome("Mario");
        clienteReq.setCognome("Rossi");
        clienteReq.setEmail("mario.rossi.service@tiscali.net");
        clienteReq.setIdRuolo(rUser.getId());
        clienteReq.setPassword("abete1");
        clienteReq.setIndirizzo("via Roma, 1 Torino TO");
        clienteReq.setDataNascita("21/11/2005");
        clienteC.create(clienteReq);

		RuoloRequest rSeller = new RuoloRequest();
		rSeller.setId(2);
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
        posReq.setDescrizione("Torino Service");
        PosizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina di Prova Service");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
    }

    @Test
    public void createBoxTest() {
        log.debug("Create Box (Service)");
        try {
            BoxReq req = new BoxReq();
            req.setNome("Box Bianco");
            req.setSconto(10.0);
            req.setCantinaId(1);

            boxS.create(req);
            List<BoxDTO> list = boxS.list("Box Bianco", 1);
            assertEquals(1, list.size());
            assertEquals("Box Bianco", list.get(0).getNome());
        } catch (Exception e) {
            throw new AssertionError("Errore in createBoxTest: " + e.getMessage());
        }
    }

    @Test
    public void updateBoxTest() {
        log.debug("Update Box (Service)");
        try {
            BoxReq req = new BoxReq();
            req.setId(1);
            req.setNome("Box Modificato");
            req.setSconto(20.0);
            req.setCantinaId(1);
            
            boxS.update(req);
            BoxDTO dto = boxS.getById(1);
            
            
            assertEquals("Box Modificato", dto.getNome());
            assertEquals(Double.valueOf(20.0), dto.getSconto());
            assertEquals(Integer.valueOf(1), dto.getId_cantina()); 
            
        } catch (Exception e) {
            throw new AssertionError("Errore in updateBoxTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteBoxTest() {
        log.debug("Delete Box (Service)");
        try {
            boxS.delete(1);
            assertThrows(EcommerceVinoException.class, () -> boxS.getById(1));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteBoxTest: " + e.getMessage());
        }
    }
}