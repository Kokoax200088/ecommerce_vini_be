package com.betacom.ec.dto.output;

import java.time.LocalDate;
import java.util.List;

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
public class OrdineDTO {
	private Integer id;
	private LocalDate data_ordine;
	private Double totale;
	private StatusDTO status;
	private List<OrdineAlcolicoDTO> ordineAlcolico;
	private String indirizzoDestinazione;
}
