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

import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IVenditoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/venditore")
public class VenditoreController {
	private final IVenditoreService venditoreService;
	
	@PostMapping("create")
	public ResponseEntity<ResponseDTO> create(
			@RequestBody (required = true) @Validated(ValidationGroups.Create.class) VenditoreRequest req) throws Exception{
		venditoreService.create(req);
		return ResponseEntity.ok(ResponseDTO.builder()
				.msg("created venditore...")
				.build());
	
	}
	
	@PatchMapping("update")
	public ResponseEntity<ResponseDTO> update(
			@RequestBody (required = true) @Validated(ValidationGroups.Update.class) VenditoreRequest req) throws Exception {
			venditoreService.update(req);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("updated venditore...")
					.build());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(
			@PathVariable (required = true) Integer id
			) throws Exception{
			venditoreService.delete(id);
			return ResponseEntity.ok(ResponseDTO.builder()
					.msg("deleted venditore...")
					.build());
	}
	
	@GetMapping("getVenditoreById")
	public ResponseEntity<Object> getById(@RequestParam (required = true) Integer id) throws Exception{
		return ResponseEntity.ok(venditoreService.getById(id)) ;
	}
	
	@GetMapping("listWithParameters")
	public ResponseEntity<Object> list(
			@RequestParam(required = false) String partitaIva
			) throws Exception {
//	è possibile sia inutile perchè dovrebbe dare solo la ref a idUtente e non info complete, in ogni caso lo lascio per future implementazioni
		return ResponseEntity.ok(venditoreService.list());
	}
}
