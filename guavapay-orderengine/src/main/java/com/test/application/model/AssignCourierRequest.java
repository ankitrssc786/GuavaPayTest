package com.test.application.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssignCourierRequest {

	@NotNull
	private Long orderId;

	@NotBlank
	private String courierUsername;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCourierUsername() {
		return courierUsername;
	}

	public void setCourierUsername(String courierUsername) {
		this.courierUsername = courierUsername;
	}

}