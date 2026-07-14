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

public class BoxReq {

	@NotNull (groups = {ValidationGroups.Update.class } , message ="Box_update_id_missing")
	private Integer id;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class},message="box_nome_missing")
	@NotBlank(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class},message="box_nome_missing")
	private String nome;
	
	private Double sconto;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class},message="box_id_cantina_missing")
	private Integer cantinaId;
}
