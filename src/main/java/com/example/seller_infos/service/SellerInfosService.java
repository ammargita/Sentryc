package com.example.seller_infos.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.seller_infos.model.SellerInfos;
import com.example.seller_infos.request.PageInput;
import com.example.seller_infos.request.SellerFilter;
import com.example.seller_infos.request.SellerSortBy;

@Component
public interface SellerInfosService {

	//Initialize the method
	List<SellerInfos> getSellerInfos(SellerFilter sellerFilter, PageInput pageInput, SellerSortBy sellerSortBy);

}
