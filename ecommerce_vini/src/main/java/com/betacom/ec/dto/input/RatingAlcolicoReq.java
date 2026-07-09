package com.betacom.ec.dto.input;

import com.betacom.ec.models.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RatingAlcolicoReq {
	private Integer id;
	private Integer id_alcolico;
	private Integer id_cantina;
	private Integer id_cliente;
	private Integer valutazione;
	private String commento;
}
