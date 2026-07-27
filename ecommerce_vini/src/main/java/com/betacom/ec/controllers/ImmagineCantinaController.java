package com.betacom.ec.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.betacom.ec.dto.input.ImmagineBoxReq;
import com.betacom.ec.dto.input.ImmagineCantinaReq;
import com.betacom.ec.dto.input.ValidationGroups;
import com.betacom.ec.dto.output.ImmagineCantinaDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IImmagineCantinaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/immagine-cantina")
public class ImmagineCantinaController {

	private final IImmagineCantinaService immS;

	@PostMapping(value = "create", consumes = "multipart/form-data")
	public ResponseEntity<ResponseDTO> create(
			@RequestParam MultipartFile file,
			@RequestParam Integer id_cantina) throws Exception{
		ResponseDTO r = new ResponseDTO();	 
		if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
			throw new EcommerceVinoException("upload_invalid");
		}	 
		immS.create(file, id_cantina);
		return ResponseEntity.ok(ResponseDTO.builder()
				.msg("created...")
				.build());
	}

	@PatchMapping(value = "update", consumes = "multipart/form-data")
	public ResponseEntity<ResponseDTO> update(
	        @ModelAttribute @Validated(ValidationGroups.Update.class) ImmagineCantinaReq req) throws Exception {
	    
	    if (req.getFile() == null || req.getFile().getContentType() == null || !req.getFile().getContentType().startsWith("image/")) {
	        throw new EcommerceVinoException("upload_invalid");
	    }
	    
	    immS.update(req);
	    
	    return ResponseEntity.ok(ResponseDTO.builder()
	            .msg("updated...")
	            .build());
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(required = true) Integer id) throws Exception {
		immS.delete(id);
		return ResponseEntity.ok(ResponseDTO.builder().msg("deleted...").build());
	}

	@GetMapping("list")
	public ResponseEntity<List<ImmagineCantinaDTO>> list(@RequestParam(required = true) Integer idCantina) throws Exception {
		return ResponseEntity.ok(immS.listBySearchString(idCantina));
	}

	@GetMapping("getById")
	public ResponseEntity<Object> getImmagineDegustazioneById(@RequestParam(required = true) Integer id)
			throws Exception {
		return ResponseEntity.ok(immS.getById(id));
	}

}
