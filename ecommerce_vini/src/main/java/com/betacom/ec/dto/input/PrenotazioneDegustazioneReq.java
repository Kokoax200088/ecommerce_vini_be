package com.betacom.ec.dto.input;


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
public class PrenotazioneDegustazioneReq {
	Integer id;
	Integer id_ordine;
	Integer id_degustazione;
	Integer id_status;
	Integer id_cantina;
}
