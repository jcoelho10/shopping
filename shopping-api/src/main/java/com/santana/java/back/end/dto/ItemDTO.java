package com.santana.java.back.end.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.santana.java.back.end.model.Item;

public class ItemDTO {

	@NotBlank
	private String productIdentifier;

	@NotNull
	private Float price;


	public static ItemDTO convert(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductIdentifier(item.getProductIdentifier());
		itemDTO.setPrice(item.getPrice());

		return

		itemDTO;
	}


	public String getProductIdentifier() {
		return productIdentifier;
	}


	public void setProductIdentifier(String productIdentifier) {
		this.productIdentifier = productIdentifier;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}
	
	

}