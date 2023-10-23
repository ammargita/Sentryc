package com.example.seller_infos.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerPageableResponse {

	private List<SellerPageableResponseObject> data;
}
