package com.example.seller_infos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.GraphQlTester.EntityList;

import com.example.seller_infos.response.SellerPageableResponseObject;

@SpringBootTest
@AutoConfigureGraphQlTester
class GraphQLSellerInfosControllerTest {

	@Autowired
	GraphQlTester graphQlTester;
	
	@SuppressWarnings("unused")
	@Test
	void sellerInformationTest() {
	
		String document = """
			query sellerInfo {
				 sellers(filter:{
				     producerIds:["1","2"]
				     marketplaceIds:["1","2"]
				 },page:{
					page:1
					size:2
				 },sortBy:NAME_DESC){
				    data{
				        sellerName
				        externalId
				        marketplaceId
				        producerSellerStates{
				            producerId
				            producerName
				            sellerState
				            sellerId
				        }
				    }
				 }
				}		
				""";
	      
		EntityList<SellerPageableResponseObject> response = graphQlTester.document(document)
		.execute()
		.errors()
		.verify()
		.path("sellers.data[*]")
		.entityList(SellerPageableResponseObject.class);
		
		Assertions.assertTrue(response.get().size()>0);
	    Assertions.assertNotNull(response.get().get(0).getMarketplaceId());
	    Assertions.assertNotNull(response.get().get(0).getSellerName());
	    
	    for(int i=0;i<response.get().size();i++) {
	    	assertEquals(response.get().get(i).getSellerName(), "Graph1");
	    	break;
	    }	    
	}
}
