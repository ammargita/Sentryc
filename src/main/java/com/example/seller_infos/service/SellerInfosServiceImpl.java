package com.example.seller_infos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.seller_infos.model.SellerInfos;
import com.example.seller_infos.request.PageInput;
import com.example.seller_infos.request.SellerFilter;
import com.example.seller_infos.request.SellerSortBy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service("SellerInfosService")
public class SellerInfosServiceImpl implements SellerInfosService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//Implementation of service class method.
	public List<SellerInfos> getSellerInfos(SellerFilter sellerFilter, PageInput pageInput, SellerSortBy sellerSortBy) {
		
		//Making a string for fetching seller information and passes in JPQL query. 
		String queryString = "Select si From SellerInfos si Left Join Sellers s ON s.sellerInfos.id = si.id where";
		
		//Check condition which is not required to pass in parameter.
		String whereString = "";
		if(sellerFilter.getSearchByName()!=null) {
			whereString += " AND si.name =: name";
		}
		if(sellerFilter.getProducerIds()!=null && !sellerFilter.getProducerIds().isEmpty()) {
			whereString += " AND s.producers.id IN (:producerIds)";
		}
		if(sellerFilter.getMarketplaceIds()!=null && !sellerFilter.getMarketplaceIds().isEmpty()) {
			whereString += " AND si.marketPlaces.id IN (:marketPlaceIds)";
		}
		
		//For replace any first condition.
		String processed = whereString.replaceFirst("AND", "");
		
		//It is check of sorting through column in ascending or descending.
		String sortBy = "";
		if(SellerSortBy.MARKETPLACE_ID_ASC.name().equalsIgnoreCase(sellerSortBy.name())) {
			sortBy = "si.marketPlaces.id ASC";
		}else if(SellerSortBy.MARKETPLACE_ID_DESC.name().equalsIgnoreCase(sellerSortBy.name())) {
			sortBy = "si.marketPlaces.id DESC";
		}else if(SellerSortBy.NAME_ASC.name().equalsIgnoreCase(sellerSortBy.name())) {
			sortBy = "si.name ASC";
		}else if(SellerSortBy.NAME_DESC.name().equalsIgnoreCase(sellerSortBy.name())) {
			sortBy = "si.name DESC";
		}else if(SellerSortBy.SELLER_INFO_EXTERNAL_ID_ASC.name().equalsIgnoreCase(sellerSortBy.name())) {
			sortBy = "si.external_id ASC";
		}else if(SellerSortBy.SELLER_INFO_EXTERNAL_ID_ASC.name().equalsIgnoreCase(sellerSortBy.name())) {
			sortBy = "si.external_id DESC";
		}
		
		//we are define initial page and passes string in JPQL query.
		Integer initialPage = (pageInput.getPage() - 1) * pageInput.getSize();
		Query q = entityManager.createQuery(queryString + processed + " ORDER BY "+sortBy);
		q.setFirstResult(initialPage);
		q.setMaxResults(pageInput.getSize());
		
		//Set request parameter
		if(sellerFilter.getSearchByName()!=null) {
			q.setParameter("name", sellerFilter.getSearchByName());		
		}
		if(sellerFilter.getProducerIds()!=null && !sellerFilter.getProducerIds().isEmpty()) {
			q.setParameter("producerIds", sellerFilter.getProducerIds());
		}
		if(sellerFilter.getMarketplaceIds()!=null && !sellerFilter.getMarketplaceIds().isEmpty()) {
			q.setParameter("marketPlaceIds", sellerFilter.getMarketplaceIds());		
		}
		
		//get list of seller information.
		@SuppressWarnings("unchecked")
		List<SellerInfos> result = q.getResultList();
		return result;
	}
}
