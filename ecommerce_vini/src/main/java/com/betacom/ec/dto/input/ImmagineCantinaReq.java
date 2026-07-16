package com.betacom.ec.dto.input;


import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class ImmagineCantinaReq {

	@NotNull(groups = {ValidationGroups.Update.class}, message ="immagine_cantina.id_missing")
	private Integer id;
	@NotNull(groups = {ValidationGroups.Update.class}, message ="immagine.url_missing")
	private MultipartFile file;
	@NotNull(groups = {ValidationGroups.Update.class}, message ="immagine_cantina.id_cant_missing")
	private Integer id_cantina;
}
