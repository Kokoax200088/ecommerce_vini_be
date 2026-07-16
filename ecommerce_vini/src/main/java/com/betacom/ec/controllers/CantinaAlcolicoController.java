package com.betacom.ec.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.CantinaAlcolicoReq;
import com.betacom.ec.dto.output.CantinaAlcolicoDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.ICantinaAlcolicoService;
import com.betacom.ec.services.interfaces.IMessaggioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/api/cantina-alcolico")
@RequiredArgsConstructor
public class CantinaAlcolicoController extends ExceptionManager {

	private final ICantinaAlcolicoService cantinaAlcolicoS;
	private final IMessaggioService msgS;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody CantinaAlcolicoReq req) throws Exception {
		cantinaAlcolicoS.create(req);
		return new ResponseEntity<>(
				ResponseDTO.builder().msg(msgS.get("cantinaAlcolico_create_ok")).build(),
				HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody CantinaAlcolicoReq req) throws Exception {
		cantinaAlcolicoS.update(req);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("cantinaAlcolico_update_ok")).build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) throws Exception {
		cantinaAlcolicoS.delete(id);
		return ResponseEntity.ok(
				ResponseDTO.builder().msg(msgS.get("cantinaAlcolico_delete_ok")).build());
	}

	@GetMapping("/list")
	public ResponseEntity<List<CantinaAlcolicoDTO>> list(
			@RequestParam(required = false) Integer idCantina,
			@RequestParam(required = false) Integer idAlcolico) throws Exception {
		return ResponseEntity.ok(cantinaAlcolicoS.listBySearchString(idCantina, idAlcolico));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<CantinaAlcolicoDTO> get(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(cantinaAlcolicoS.getById(id));
	}
}