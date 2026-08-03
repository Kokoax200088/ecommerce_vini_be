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
	private StatusDTO status;
	private UtenteDTO utente;
	private List<OrdineAlcolicoDTO> ordineAlcolico;
	private List<OrdineBoxDTO> ordineBox;
	private List<OrdineDegustazioneDTO> ordineDeg;
	private String indirizzo_destinazione;
}
