package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "immagine_degustazione")
public class ImmagineDegustazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "url", nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "id_degustazione", foreignKey = @ForeignKey(name = "fk_immagine_degustazione"))
	private Degustazione degustazione;
}
