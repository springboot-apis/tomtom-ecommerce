package com.tomtom.ecommerce.beans;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class Cart {
private List<Product> productInCart = new LinkedList<>();
private BigDecimal totalBilling;
	
}
