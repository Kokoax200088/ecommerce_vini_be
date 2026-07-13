package com.betacom.ec.dto.output;

import com.betacom.ec.models.OrdineAlcolico;
import com.betacom.ec.models.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SpedizioneDTO {
	private Integer id;
	private String corriere;
	private String codice_tracciamento;
	//private CantinaDTO cantina;
	//private ClienteDTO cliente;
	private StatusDTO status;
	private OrdineAlcolicoDTO ordine_alcolico;
}
