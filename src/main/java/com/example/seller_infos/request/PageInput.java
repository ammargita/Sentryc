package com.example.seller_infos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageInput {

	private int page;
	
	private int size;
}
