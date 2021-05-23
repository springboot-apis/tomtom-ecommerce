package com.tomtom.ecommerce.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Seller implements Cloneable {
	private String SellerId;
	private String SellerName;
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
