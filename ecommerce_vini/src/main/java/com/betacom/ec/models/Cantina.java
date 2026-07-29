package com.betacom.ec.models;

import java.util.ArrayList;
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
	
	// posizione tipo stringa non opzionale
		@Column(
				name = "posizione",
				nullable = false
				)
		private String posizione;
		
		// descrizione tipo stringa opzionale
			@Column(
				name = "descrizione",
				nullable = true
			)
			private String descrizione = null;

	// collegamento al venditore
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "id_venditore", foreignKey = @ForeignKey(name = "fk_cantina_venditore"))
	private Venditore venditore;

	// collegeamneto al rating Cantina
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<RatingCantina> listRatingCantina = new ArrayList <RatingCantina> ();

	// collegamento con Box
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<Box> listBox = new ArrayList <Box> ();

	// collegamento alla degustazione
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<Degustazione> listDegustazione = new ArrayList <Degustazione> ();

	// collegamento con OrdineAlcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<OrdineAlcolico> listOrdineAlcolico = new ArrayList <OrdineAlcolico> ();
	
	// collegamento
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<OrdineBox> listOrdineBox = new ArrayList <OrdineBox> ();

	// collegamento al rating alcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<RatingAlcolico> listRatingAlcolico = new ArrayList <RatingAlcolico> ();

	// collegamento ad immagine

	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<ImmagineCantina> listImmagine = new ArrayList <ImmagineCantina> ();

	// collegamento con ProdottoAlcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<ProdottoAlcolico> listProdottoAlcolico = new ArrayList <ProdottoAlcolico> ();

	// collegamento con ProdottoBox
		@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
		private List<ProdottoBox> listProdottoBox = new ArrayList <ProdottoBox> ();
	
	// collegamento a spedizione
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<SpedizioneAlcolico> listSpedizione = new ArrayList <SpedizioneAlcolico> ();

	
	// collegamento cantina alcolico
	@OneToMany(mappedBy = "cantina", fetch = FetchType.LAZY)
	private List<CantinaAlcolico> listCantinaAlcolico = new ArrayList <CantinaAlcolico> ();

}
