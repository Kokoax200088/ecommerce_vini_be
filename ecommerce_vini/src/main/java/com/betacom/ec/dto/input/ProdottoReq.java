package com.betacom.ec.dto.input;

import com.betacom.ec.models.Carrello;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProdottoReq {
	private Integer id;
	@NotNull(groups=ValidationGroups.Create.class, message="carrello_notFound")
	private Integer id_carrello;
	private Integer id_alcolico;
	private Integer id_cantina;
	private Integer quantità;
}
