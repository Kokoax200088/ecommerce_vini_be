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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
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
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "id_venditore", foreignKey = @ForeignKey(name = "fk_cantina_venditore"))
	private Venditore venditore;

	// collegeamneto al rating Cantina
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<RatingCantina> listRatingCantina;

	// collegamento con Box
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<Box> listBox;

	// collegamento alla degustazione
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<Degustazione> listDegustazione;

	// collegamento con OrdineAlcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<OrdineAlcolico> listOrdineAlcolico;
	
	// collegamento
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<OrdineBox> listOrdineBox;

	// collegamento al rating alcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<RatingAlcolico> listRatingAlcolico;

	// collegamento ad immagine

	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<ImmagineCantina> listImmagine;

	// collegamento con ProdottoAlcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<ProdottoAlcolico> listProdottoAlcolico;

	// collegamento con ProdottoBox
		@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
		private List<ProdottoBox> listProdottoBox;
	
	// collegamento a spedizione
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<SpedizioneAlcolico> listSpedizione;

	
	// collegamento cantina alcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<CantinaAlcolico> listCantinaAlcolico;

	// collegamento con Posizione
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "id_posizione", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cantina_posizione"))
	private Posizione posizione;
}
