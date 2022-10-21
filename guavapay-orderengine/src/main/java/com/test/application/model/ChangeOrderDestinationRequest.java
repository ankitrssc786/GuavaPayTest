package com.test.application.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangeOrderDestinationRequest {

	@NotNull
	private Long orderId;

	@NotBlank
	private String newDestination;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getNewDestination() {
		return newDestination;
	}

	public void setNewDestination(String newDestination) {
		this.newDestination = newDestination;
	}

}