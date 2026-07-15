package com.betacom.ec.models;

import java.util.List;

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

	//collegato con Venditore
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

	//collegato con tipologiaAlcolico
	@ManyToOne
	@JoinColumn (
			name="id_tipologia_alcolico",
			foreignKey = @ForeignKey(name ="fk_alcolico_tipologia" )	
			)
	private TipologiaAlcolico tipologia_alcolico;

	//collegamento con Colore
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

	//collegato con immagineAlcolico
	@OneToMany(
			mappedBy = "alcolico",
			fetch = FetchType.LAZY)
	private List<ImmagineAlcolico> listImmagine;	

	@Column(
			name = "prezzo",
			nullable = true
			)
	private Double prezzo;
	
	//collegato con boxAlcolico
	@OneToMany(
			mappedBy = "alcolico",
			fetch = FetchType.LAZY)
	private List <BoxAlcolico> listBoxAlcolico;	
	
	//collegato con cantinaAlcolico
	@OneToMany(
			mappedBy = "alcolico",
			fetch = FetchType.LAZY)
	private List<CantinaAlcolico> listCantinaAlcolico;
	
	//collegamento con Degustazione 
	@ManyToMany (
            mappedBy = "listAlcolico",
            fetch = FetchType.LAZY
            )
	private List<Degustazione> listDegustazione;
	
	//collegamento con ordineAlcolico
	@OneToMany(
			mappedBy = "alcolico",
			fetch = FetchType.LAZY)
	private List<OrdineAlcolico> listOrdineAlcolico;
	
	//collega,ento con ProdottoAlcolico
	@OneToMany(
			mappedBy = "alcolico",
			fetch = FetchType.LAZY)
	private List <ProdottoAlcolico> listProdottoAlcolico;	
	
	//collegato ocn RatingAlcolico
	@OneToMany(
			mappedBy = "alcolico",
			fetch = FetchType.LAZY)
	private List <RatingAlcolico> listRatingAlcolico;
	
	//da collegare con Caratteristica
	/* @ManyToMany(
			mappedby = "listAlcolico",
			fetch = FetchType.LAZY
			)
	private List <Caratteristica> listCaratteristica;
	*/
}
