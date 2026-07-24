package com.betacom.ec.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "status")
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name="nome", nullable=false)
	private String nome;
	
	@NotBlank
	@Column(name="descrizione", nullable=false)
	private String descrizione;
	
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<Ordine> listOrdine = new ArrayList <Ordine> ();
	
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<OrdineAlcolico> listOrdineAlcolico = new ArrayList <OrdineAlcolico> ();
}
