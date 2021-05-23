package com.tomtom.ecommerce.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ErrorPayload extends ResponsePayload {
	private String cause;
	public ErrorPayload(String message) {
		super(message);
	}
}
