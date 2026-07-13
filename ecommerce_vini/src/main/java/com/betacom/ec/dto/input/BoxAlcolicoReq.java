package com.betacom.ec.dto.input;

import jakarta.validation.constraints.Min;
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
public class BoxAlcolicoReq {

	@NotNull(groups = {ValidationGroups.Update.class}, message ="boxAlcolico_update_id_missing")
	private Integer id;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="boxAlcolico_id_box_missing")
	private Integer boxId;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="boxAlcolico_id_alcolico_missing")
	private Integer alcolicoId;
	
	@NotNull(groups= {ValidationGroups.Create.class,ValidationGroups.Update.class}, message="boxAlcolico_quantita_missing")
	@Min(value = 0, groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "boxAlcolico_quantita_negative")
	private Integer quantita;
}