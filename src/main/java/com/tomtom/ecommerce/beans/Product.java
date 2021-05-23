package com.tomtom.ecommerce.beans;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product implements Cloneable {
	private String productId;
	private String productName;
	private BigDecimal productMrp;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
