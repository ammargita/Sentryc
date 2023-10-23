package com.example.seller_infos.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerFilter {

	private String searchByName;
	
	private List<Long> producerIds;
	
	private List<Long> marketplaceIds;
}
