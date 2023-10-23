package com.example.seller_infos.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerPageableResponseObject {

	private String sellerName;
	
	private String externalId;
	
	private String marketplaceId;
	
	private List<ProducerSellerStates> producerSellerStates;
}
