package com.example.seller_infos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.seller_infos.model.SellerInfos;
import com.example.seller_infos.model.Sellers;
import com.example.seller_infos.repository.SellersRepository;
import com.example.seller_infos.request.PageInput;
import com.example.seller_infos.request.SellerFilter;
import com.example.seller_infos.request.SellerSortBy;
import com.example.seller_infos.response.ProducerSellerStates;
import com.example.seller_infos.response.SellerPageableResponse;
import com.example.seller_infos.response.SellerPageableResponseObject;
import com.example.seller_infos.service.SellerInfosService;

@Controller
public class GraphQLSellerInfosController {

	@Autowired
	SellerInfosService sellerInfosService;
	
	@Autowired
	SellersRepository sellersRepository;
			
	//In this api using graphql schema and parsing parameter to filter a seller information. 
	@QueryMapping
	public SellerPageableResponse sellers(@Argument SellerFilter filter,@Argument PageInput page,@Argument SellerSortBy sortBy) {
		
		//fetch seller information using JPQL through this method.
		List<SellerInfos> sellerInfos = sellerInfosService.getSellerInfos(filter,page,sortBy);
		
		//When Successfully fetching a response then we are set the information according to the client requirement.
		SellerPageableResponse response = new SellerPageableResponse();		
		List<SellerPageableResponseObject> responseObjects = new ArrayList<>();
		for(SellerInfos infos:sellerInfos) {
			
			SellerPageableResponseObject responseObject = new SellerPageableResponseObject();
			responseObject.setExternalId(infos.getExternal_id());
			
			if(infos.getMarketPlaces()!=null) {
				responseObject.setMarketplaceId(infos.getMarketPlaces().getId().toString());
			}
					
			responseObject.setSellerName(infos.getName());
			
			List<ProducerSellerStates> sellerStates = new ArrayList<>();
			List<Sellers> sellers = sellersRepository.findBySellerInfos(infos);
			for (Sellers seller : sellers) {
				
				ProducerSellerStates producerSellerStates = new ProducerSellerStates();
				producerSellerStates.setProducerId(seller.getProducers().getId());
				producerSellerStates.setProducerName(seller.getProducers().getName());
				producerSellerStates.setSellerId(seller.getSellerInfos().getId());
				producerSellerStates.setSellerState(seller.getState().name());
				sellerStates.add(producerSellerStates);
			}
			responseObject.setProducerSellerStates(sellerStates);
			
			responseObjects.add(responseObject);
		}
			
		response.setData(responseObjects);
		return response;
	}
}
