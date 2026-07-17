package com.betacom.ec.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.CaratteristicaReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.CaratteristicaDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.ICaratteristicaService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/caratteristica")
@RequiredArgsConstructor
public class CaratteristicaController extends ExceptionManager {

	private final ICaratteristicaService caratteristicaS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody (required = true) @Validated(ValidationGroups.Create.class) CaratteristicaReq req) throws Exception {
		caratteristicaS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("caratteristica_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ResponseDTO> remove(@PathVariable("id") Integer id) throws Exception {
		caratteristicaS.remove(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("caratteristica_remove_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<CaratteristicaDTO>> list() {
		return ResponseEntity.ok(caratteristicaS.listAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<CaratteristicaDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(caratteristicaS.getById(id));
	}
}
