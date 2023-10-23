package com.example.seller_infos.model;

import com.example.seller_infos.response.SellerState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sellers")
@Getter
@Setter
public class Sellers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private SellerState state;
	
	@OneToOne
	private Producers producers;
	
	@OneToOne
	private SellerInfos sellerInfos;
}
