package com.santana.java.back.end.model;

import javax.persistence.Embeddable;

import com.santana.java.back.end.dto.ItemDTO;

@Embeddable
public class Item {

	private String productIdentifier;

	private Float price;

	public static Item convert(ItemDTO itemDTO) {
		Item item = new Item();
		item.setProductIdentifier(itemDTO.getProductIdentifier());
		item.setPrice(itemDTO.getPrice());

		return item;
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