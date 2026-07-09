package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table (
		name = "role"
		)
public class Ruolo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(
			length = 100,
			nullable = false
			)
	private String nome;
	
	@Column(
			name = "can_manage",
			nullable = false
			)
	private Boolean canManage;
	
	@Column(
			name = "can_sell",
			nullable = false
			)
	private Boolean canSell;
	
	@Column(
			name = "can_buy",
			nullable = false
			)
	private Boolean canBuy;
}
