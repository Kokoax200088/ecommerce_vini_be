package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipologiaAlcolicoReq {

	private Integer id;

	@NotBlank(groups= {ValidationGroups.Create.class}, message="tipologiaAlcolico_nome_missing")
	private String nome;

	private String descrizione;
}
