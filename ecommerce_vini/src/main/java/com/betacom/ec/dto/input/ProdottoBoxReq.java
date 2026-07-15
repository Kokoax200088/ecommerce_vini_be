package com.betacom.ec.dto.input;

import com.betacom.ec.models.Box;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Carrello;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProdottoBoxReq {
	@NotNull(groups = {ValidationGroups.Update.class}, message ="prod_box-id_missing")
	private Integer id;
	@NotNull(groups = {ValidationGroups.Update.class}, message ="prod_box-id_cart_missing")
	private Integer id_carrello;
	@NotNull(groups = {ValidationGroups.Update.class}, message ="prod_box-id_box_missing")
	private Integer id_box;
	@NotNull(groups = {ValidationGroups.Update.class}, message ="prod_box-id_cant_missing")
	private Integer id_cantina;
	private Integer quantità;
}
