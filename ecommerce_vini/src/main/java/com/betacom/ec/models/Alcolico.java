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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	private Integer id;

	@ManyToOne
	@JoinColumn (
			name="id_venditore",
			foreignKey = @ForeignKey(name ="fk_alcolico_venditore" )	
			)
	private Venditore venditore;

	@Column(
			name = "nome",
			nullable = true
			)
	private String nome;

	@Column(
			name = "annata",
			nullable = true
			)
	private Integer annata;

	@ManyToOne
	@JoinColumn (
			name="id_tipologia_alcolico",
			foreignKey = @ForeignKey(name ="fk_alcolico_tipologia" )	
			)
	private TipologiaAlcolico tipologia_alcolico;

	@ManyToOne
	@JoinColumn (
			name="id_colore",
			foreignKey = @ForeignKey(name ="fk_alcolico_colore" )	
			)
	private Colore colore;

	@Column(
			name = "gradazione",
			nullable = true
			)
	private Integer gradazione;

	@Column(
			name = "descrizione",
			nullable = true
			)
	private String descrizione;

	@Column(
			name = "provenienza",
			nullable = true
			)
	private String provenienza;

	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "id_immagine", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_immagine_alcolico"))
	private Immagine immagine;

	@Column(
			name = "sconto",
			nullable = true
			)
	private Double prezzo;
	
	@OneToMany(
			mappedBy = "box_alcolico",
			fetch = FetchType.LAZY)
	private List <BoxAlcolico> listBoxAlcolico;	
	
	@ManyToMany (
            mappedBy = "cantina",
            fetch = FetchType.LAZY
            )
	private List<Cantina> listCantina;
	
	@ManyToOne
	@JoinColumn (
			name="id_cantina_alcolico",
			foreignKey = @ForeignKey(name ="fk_alcolico_cantina_alcolico" )	
			)
	private CantinaAlcolico cantinaAlcolico;
	
	@ManyToMany (
            mappedBy = "degustazione",
            fetch = FetchType.LAZY
            )
	private List<Degustazione> listDegustazione;
	
	@ManyToMany (
            mappedBy = "ordine_alcolico",
            fetch = FetchType.LAZY
            )
	private List<OrdineAlcolico> listOrdineAlcolico;
	
	@OneToMany(
			mappedBy = "prodotto",
			fetch = FetchType.LAZY)
	private List <Prodotto> listProdotto;	
	
	@OneToMany(
			mappedBy = "rating_alcolico",
			fetch = FetchType.LAZY)
	private List <RatingAlcolico> listRatingAlcolico;
}
