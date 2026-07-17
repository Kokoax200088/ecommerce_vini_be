package com.betacom.ec.carrello;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.betacom.ec.controllers.RatingCantinaController;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RatingCantinaTest {
	
private final ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private RatingCantinaController rCcontroller;
    

    @Autowired
    private MockMvc mockMvc;

}
