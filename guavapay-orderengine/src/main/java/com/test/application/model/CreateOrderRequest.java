package com.test.application.model;

import javax.validation.constraints.NotBlank;

import lombok.NonNull;

public class CreateOrderRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String destination;

	@NonNull
	private Double amount;

	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
