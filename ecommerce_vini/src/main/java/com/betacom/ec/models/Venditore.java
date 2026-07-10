package com.betacom.ec.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table (name = "venditore")
public class Venditore {
	@Id
	@GeneratedValue	(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(
			name = "id_utente",
			referencedColumnName = "id",
			foreignKey = @ForeignKey (name = "fk_utente_venditore")
			)
	private Utente utente;
	
	@Column(name="partita_iva",
			nullable=false)
	private String partitaIva;
	
//	@OneToMany (
//				mappedBy = "venditore",
//				fetch = FetchType.LAZY
//			)
//	private List<Cantina> listCantina;
}
