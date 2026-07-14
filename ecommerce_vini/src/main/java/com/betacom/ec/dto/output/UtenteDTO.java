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
public class UtenteDTO {
	private Integer id;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String ruolo;
	
	//attributi opzionali (si può mettere come generic Object)
	private ClienteDTO clienteDTO;
	private VenditoreDTO venditoreDTO;
}
