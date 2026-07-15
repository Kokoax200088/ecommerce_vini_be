package com.betacom.ec.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.output.ColoreDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IColoreService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/colore")
@RequiredArgsConstructor
public class ColoreController extends ExceptionManager {

	private final IColoreService coloreS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody ColoreReq req) throws Exception {
		coloreS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("colore_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDTO> remove(@PathVariable("id") Integer id) throws Exception {
		coloreS.remove(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("colore_remove_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<ColoreDTO>> list() {
		return ResponseEntity.ok(coloreS.listAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ColoreDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(coloreS.getById(id));
	}
}
