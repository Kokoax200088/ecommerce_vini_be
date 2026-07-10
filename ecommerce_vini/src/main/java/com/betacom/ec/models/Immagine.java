package com.betacom.ec.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

public class Immagine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tipo", nullable = false)
	private String tipo;

	@Column(name = "url", nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "id_degustazione", foreignKey = @ForeignKey(name = "fk_immagine_degustazione"))
	private Degustazione degustazione;

	@ManyToOne
	@JoinColumn(name = "id_box", foreignKey = @ForeignKey(name = "fk_immagine_box"))
	private Box box;

	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "id_alcolico", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_immagine_alcolico"))
	private Alcolico alcolico;

	@ManyToOne
	@JoinColumn(name = "id_cantina", foreignKey = @ForeignKey(name = "fk_immagine_cantina"))
	private Cantina cantina;
}
