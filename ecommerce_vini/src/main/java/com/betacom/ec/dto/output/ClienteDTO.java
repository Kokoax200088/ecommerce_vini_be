package com.betacom.ec.dto.output;

import java.util.List;

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
public class ClienteDTO {
	private Integer id;
	private String indirizzo;
    private UtenteDTO utente;
	//attributi in join
	private CarrelloDTO carrello;
	private List<RatingAlcolicoDTO> listRatingAlcolico;
	private List<RatingCantinaDTO> listRatingCantina;
}
