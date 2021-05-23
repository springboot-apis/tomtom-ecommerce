package com.tomtom.ecommerce.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Customer implements Cloneable {
	private String customerId;
	private String customerName;
	private Cart cart;
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
