package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CantinaReq {

	@NotNull(groups = {ValidationGroups.Update.class}, message ="cantina_update_id_missing")
	private Integer id;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="cantina_nome_missing")
	@NotBlank(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="cantina_nome_empty")
	private String nome;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="cantina_id_venditore_missing")
	private Integer venditoreId;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="cantina_id_venditore_missing")
	private Integer posizioneId; 
}