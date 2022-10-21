package com.test.application.model;

import javax.validation.constraints.NotNull;

public class ChangeOrderStatusRequest {

	@NotNull
	private Long orderId;

	@NotNull
	private OrderStatus newOrderStatus;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getNewOrderStatus() {
		return newOrderStatus;
	}

	public void setNewOrderStatus(OrderStatus newOrderStatus) {
		this.newOrderStatus = newOrderStatus;
	}

}
