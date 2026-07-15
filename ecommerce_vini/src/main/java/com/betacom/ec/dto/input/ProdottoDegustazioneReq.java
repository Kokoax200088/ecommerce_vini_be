package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProdottoDegustazioneReq {
	@NotNull(groups = {ValidationGroups.Update.class}, message ="prod_deg-id_missing")
	private Integer id;
	@NotNull(groups = {ValidationGroups.Update.class}, message ="prod_deg-id_cart_missing")
	private Integer id_carrello;
	@NotNull(groups = {ValidationGroups.Update.class}, message ="prod_deg-id_deg__missing")
	private Integer id_degustazione;
	@NotNull(groups = {ValidationGroups.Update.class}, message ="prod_deg-id_cant_missing")
	private Integer id_cantina;
	private Integer quantità;
}
