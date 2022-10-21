package com.test.application.event;

import com.test.application.model.OrderStatus;


public class OrderStatusChangeEvent {

	private String orderNumber;

	private OrderStatus orderStatus;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
