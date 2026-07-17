package com.betacom.ec.carrello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail; // Importato per gestire i fallimenti nel catch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.CarrelloController;
import com.betacom.ec.controllers.ProdottoBoxController;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.ProdottoBoxReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Carrello;
import com.betacom.ec.models.Cliente;
import com.betacom.ec.models.ProdottoBox;
import com.betacom.ec.repository.ICarrelloRepository;
import com.betacom.ec.repository.IProdottoBoxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelloTest {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private CarrelloController carrelloController;
	
    @Autowired
    private ProdottoBoxController prodBoxController;
    
    @Autowired
    private IProdottoBoxRepository repP;
    
    @Autowired
    private ICarrelloRepository carR;
	
    @Autowired
    private MockMvc mockMvc;
    

	
    @Test
    @Order(1)
    public void createCarrello() throws Exception {
        log.debug("createCarrello Test");
		
        CarrelloReq req = new CarrelloReq();
        req.setId_cliente(1);
        req.setQuantità(0);
        req.setTotale(0.0); 		
        mockMvc.perform(post("/rest/api/cart/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }
    
    @Test
	@Order(2)
	public void createRuoloSeller() throws Exception{
		log.debug("createRuolo Seller");
		
		RuoloRequest req = new RuoloRequest();
		req.setId(2);
		req.setNome("seller");
		req.setCanManage(false);
		req.setCanBuy(false);
		req.setCanSell(true);
		mockMvc.perform(post("/rest/api/ruolo/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
	}
	
    
    @Test
    @Order(3)
    public void createVenditore() throws Exception {
        log.debug("createVenditore Test");
		
        VenditoreRequest req = new VenditoreRequest();
        req.setId(1);
        req.setNome("Caio Maio");
        req.setDataNascita("08/08/1996");
        req.setCognome("Ilario");
        req.setEmail("c.maio@gmail.com");
        req.setIdRuolo(2);
        req.setPartitaIva("A99");
        mockMvc.perform(post("/rest/api/venditore/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }
    
    @Test
    @Order(4)
    public void createCantina() throws Exception {
        log.debug("createCantina Test");
		
        CantinaReq req = new CantinaReq();
        req.setId(1);
        req.setNome("Villa Turistica");
        req.setVenditoreId(1);
        mockMvc.perform(post("/rest/api/cantina/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }
    
    @Test
    @Order(5)
    public void createBox() throws Exception {
        log.debug("createBox Test");
		
        BoxReq req = new BoxReq();
        req.setId(1);
        req.setNome("Villa Turistica");
        req.setNome("Boxlandia");
        mockMvc.perform(post("/rest/api/box/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }
	
    @Test
    @Order(6)
    public void createProdBox() throws Exception{
        log.debug("createProdBox Test");
		
        ProdottoBoxReq req = new ProdottoBoxReq();
        req.setId_box(1);
        req.setId_cantina(1);
        req.setId_carrello(1);
        req.setQuantità(3);		
        
        mockMvc.perform(post("/rest/api/prodotto-box/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }
	
    @Test
    @Order(7)
    public void updateCarrello() throws Exception{
        log.debug("updateCarrello Test");
        
        Carrello carrello = new Carrello();
        carrello.setId(1); 

        List<ProdottoBox> listaBox = new ArrayList<>();
        
        ProdottoBox pb = repP.findById(1).orElseThrow();
        
        listaBox.add(pb);
        
        Carrello req = carR.findById(1).orElseThrow();
        req.setListaProdottoBox(listaBox);
        req.setQuantità(3);        
        req.setTotale(45.50);		
        
        mockMvc.perform(post("/rest/api/cart/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk());
    }
    
    @Test
	@Order(8)
	public void delete() throws Exception{
		log.debug("delete");
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/rest/api/cart/delete/" +  "1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.msg").exists());
		  
	}
}