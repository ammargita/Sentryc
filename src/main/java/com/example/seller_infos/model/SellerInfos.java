package com.example.seller_infos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seller_infos")
@Getter
@Setter
public class SellerInfos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String url;
	
	private String country;
	
	private String external_id;
	
	@ManyToOne
	private MarketPlaces marketPlaces;
}
