package com.betacom.ec.dto.output;

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
public class PrenotazioneDegustazioneDTO {
	
	Integer id;
	OrdineDTO ordine;
	DegustazioneDTO degustazione;
	StatusDTO status;
	CantinaDTO cantina;
}
