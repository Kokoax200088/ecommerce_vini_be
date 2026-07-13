package com.betacom.ec.dto.input;

import com.betacom.ec.models.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RatingCantinaReq {
private Integer id;

@NotNull(groups=ValidationGroups.Create.class, message="cantina_notFound")
	private Integer id_cantina;

@NotNull(groups=ValidationGroups.Create.class, message="cliente_notFound")
	private Integer id_cliente;

@NotNull(groups=ValidationGroups.Create.class, message="validazione_missing")
	private Double valutazione;
	private String commento;
}
