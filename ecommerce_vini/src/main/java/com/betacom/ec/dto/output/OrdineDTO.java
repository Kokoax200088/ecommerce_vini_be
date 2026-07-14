package com.betacom.ec.dto.output;

import java.time.LocalDate;
import java.util.List;

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
	private Integer id_status;
	private Integer id_utente;
	private List<OrdineAlcolicoDTO> ordineAlcolico;
	private String indirizzoDestinazione;
}
