package com.betacom.ec.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cantina")
public class Cantina {

	// id cantina generato automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// nome della cantina non opzionale
	@Column(name = "nome", nullable = false)
	private String nome;

	// collegamento al venditore
	@ManyToOne
	@JoinColumn(name = "id_venditore", foreignKey = @ForeignKey(name = "fk_cantina_venditore"))
	private Venditore venditore;

	// collegeamneto al rating Cantina
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<RatingCantina> listRatingCantina;

	// collegeamneto al box
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<Box> listBox;

	// collegamento alla degustazione
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<Degustazione> listDegustazione;

	// collegamento all'ordine alcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<OrdineAlcolico> listOrdineAlcolico;

	// collegamento al rating alcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<RatingAlcolico> listRatingAlcolico;

	// collegamento ad immagine

	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<ImmagineCantina> listImmagine;

	// collegamento al prodotto
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<Prodotto> listProdotto;

	// collegamento a spedizione
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<Spedizione> listSpedizione;

	// collegamento cantina alcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<CantinaAlcolico> listCantinaAlcolico;

	// collegamento alla posizione
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "id_posizione", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cantina_posizione"))
	private Posizione posizione;
}
