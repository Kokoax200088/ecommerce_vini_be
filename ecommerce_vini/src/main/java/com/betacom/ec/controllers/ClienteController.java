package com.betacom.ec.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IClienteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/cliente")
public class ClienteController {
	private final IClienteService clienteService;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) ClienteRequest req) throws Exception{
		clienteService.create(req);
		return ResponseEntity.ok(ResponseDTO.builder()
				.msg("created cliente...")
				.build());
	
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) ClienteRequest req) throws Exception {
			clienteService.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated cliente...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
			clienteService.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted cliente...")
					.build());
	}
	
	@GetMapping("getClienteById")
	public ResponseEntity<Object> getById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(clienteService.getById(id)) ;
	}
	
	@GetMapping("listWithParameters")
	public ResponseEntity<Object> list(
			@RequestParam(required = false) String indirizzo
			) throws Exception {
//	è possibile sia inutile perchè dovrebbe dare solo la ref a idUtente e non info complete, in ogni caso lo lascio per future implementazioni
		return ResponseEntity.ok(clienteService.listBySearchString(indirizzo));
	}
}
