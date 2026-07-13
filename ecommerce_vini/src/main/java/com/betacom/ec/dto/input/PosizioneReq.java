package com.betacom.ec.dto.input;

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
public class PosizioneReq {

	@NotNull(groups = {ValidationGroups.Update.class}, message ="posizione_update_id_missing")
	private Integer id;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="posizione_latitudine_missing")
	private Double latitudine;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="posizione_longitudine_missing")
	private Double longitudine;
	
	@NotNull(groups = {ValidationGroups.Update.class}, message ="posizione_id_cantina_missing")
	private Integer posizioneId;
	
	private String descrizione;
}