package com.betacom.ec.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "colore")
public class Colore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_colore;

	@Column(
			name = "nome",
			nullable = true
			)
	private String nome;

	@Column(
			name = "descrizione",
			nullable = true
			)
	private String descrizione;
	
	@OneToMany(mappedBy = "alcolico", fetch = FetchType.LAZY)
	private List<Alcolico> listAlcolico;
}
