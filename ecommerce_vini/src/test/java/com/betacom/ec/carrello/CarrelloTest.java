package com.betacom.ec.carrello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail; // Importato per gestire i fallimenti nel catch

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.betacom.ec.controllers.CarrelloController;
import com.betacom.ec.controllers.ProdottoBoxController;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.ProdottoBoxReq;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.ProdottoBox;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelloTest {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private CarrelloController carrelloController;
	
    @Autowired
    private ProdottoBoxController prodBoxController;
	
    @Autowired
    private MockMvc mockMvc;
	
    @Test
    @Order(1)
    public void createCarrello() {
        log.debug("createCarrello Test");
		
        CarrelloReq req = new CarrelloReq();

        Cliente cliente = new Cliente();
        cliente.setId(1); 
        req.setId_cliente(1);
        req.setQuantità(0);
        req.setTotale(0.0); 		
        try {
            ResponseEntity<ResponseDTO> response = carrelloController.create(req);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        } catch (Exception e) {
            fail("Errore in createCarrello: " + e.getMessage());
        }
    }
	
    @Test
    @Order(2)
    public void createProdBox() {
        log.debug("createProdBox Test");
		
        ProdottoBoxReq req = new ProdottoBoxReq();
        req.setId_box(1);
        req.setId_cantina(1);
        req.setId_carrello(1);
        req.setQuantità(3);		
        try {
            ResponseEntity<ResponseDTO> response = prodBoxController.create(req);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        } catch (Exception e) {
            fail("Errore in createProdBox: " + e.getMessage());
        }
    }
	
    @Test
    @Order(3)
    public void updateCarrello() {
        log.debug("updateCarrello Test");
        Box box = new Box();
        box.setId(1); 
        
      
        Cantina cantina = new Cantina();
        cantina.setId(1); 
        
        Carrello carrello = new Carrello();
        carrello.setId(1); 
        
        ProdottoBox pb = new ProdottoBox();
        pb.setBox(box);        
        pb.setCantina(cantina);
        pb.setCarrello(carrello); 
        pb.setQuantità(3);
        
        List<ProdottoBox> listaBox = new ArrayList<>();
        listaBox.add(pb);

        CarrelloReq req = new CarrelloReq();
        req.setId_cliente(1);
        req.setListaBox(listaBox);
        req.setQuantità(3);        
        req.setTotale(45.50);		
        
        try {
            ResponseEntity<ResponseDTO> response = carrelloController.update(req);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        } catch (Exception e) {
            fail("Errore in updateCarrello: " + e.getMessage());
        }
    }
}