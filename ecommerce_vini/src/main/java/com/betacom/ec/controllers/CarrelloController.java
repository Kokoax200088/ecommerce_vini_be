package com.betacom.ec.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.CarrelloReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.ICarrelloService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/cart")
public class CarrelloController {
	
	final ICarrelloService cartS;
	
	@GetMapping("/list")
	public ResponseEntity<Object> list() throws Exception {
		return ResponseEntity.ok(cartS.list());
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@RequestParam (required = true) Integer id) throws Exception {
		return ResponseEntity.ok(cartS.getById(id));
	}

}
