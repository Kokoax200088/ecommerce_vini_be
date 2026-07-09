package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "alcolico")
public class Alcolico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id_alcolico;

	@ManyToOne
	@JoinColumn(name = "id_venditore", referencedColumnName = "id")
	private Venditore id_venditore;

	private String nome;

	private Integer annata;

	@ManyToOne
	@JoinColumn(name = "tipologia_alcolico", referencedColumnName = "id")
	private TipologiaAlcolico tipologia_alcolico;

	@ManyToOne
	@JoinColumn(name = "colore", referencedColumnName = "id")
	private Colore colore;

	private Integer gradazione;

	private String descrizione;

	private String provenienza;

	private String immagine;

	private Double prezzo;
}
