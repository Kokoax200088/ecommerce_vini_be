package com.betacom.ec.dto.input;


import java.util.List;

import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.OrdineAlcolico;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatusReq {
	
	private Integer id;
	@NotNull(groups=ValidationGroups.Create.class, message="nome_missing")
	private String nome;
	@NotNull(groups=ValidationGroups.Create.class, message="descrizione_status_missing")
	private String descrizione;
	@NotNull(groups=ValidationGroups.Create.class, message="ordine_alcolico_missing")
	private List<OrdineAlcolico> listOrdineAlcolico;
	@NotNull(groups=ValidationGroups.Create.class, message="ordine_missing")
	private List<Ordine> listOrdine;
	
}
