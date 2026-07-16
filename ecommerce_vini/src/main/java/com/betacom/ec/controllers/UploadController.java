package com.betacom.ec.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IMessaggioService;
import com.betacom.ec.services.interfaces.IUploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/api/upload")
public class UploadController {
	
	private final IUploadService uplS;
	private final IMessaggioService msgS;
	
	@PostMapping(value = "/admin/image", consumes = "multipart/form-data")
	public ResponseEntity<ResponseDTO> uploadImage(
			@RequestParam MultipartFile file,
			@RequestParam Integer id) throws Exception {
		
		ResponseDTO r = new ResponseDTO();	 
		//  Test del content type: PNG, JPG GIF, ...
		if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
			throw new EcommerceVinoException("upload_invalid");
		}	 
		
		r.setMsg(uplS.saveImage(file, id));
		return ResponseEntity.ok(r);
			 
	 }

	@GetMapping("/admin/getUrl")
	public ResponseEntity<ResponseDTO> getUrl(@RequestParam (required = true) String filename) 
			throws  Exception{
		ResponseDTO r = new ResponseDTO();
		r.setMsg(uplS.buildUrl(filename));
		return ResponseEntity.ok(r);
	}

}
