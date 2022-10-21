package com.test.application.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.application.domain.Order;
import com.test.application.event.UserType;
import com.test.application.exception.CourierMustNotBeAssignedException;
import com.test.application.exception.InvalidInputException;
import com.test.application.exception.OrderCanNotCancelException;
import com.test.application.model.AssignCourierRequest;
import com.test.application.model.ChangeOrderDestinationRequest;
import com.test.application.model.ChangeOrderStatusRequest;
import com.test.application.model.CreateOrderRequest;
import com.test.application.model.OrderStatus;
import com.test.application.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;


	@Transactional(readOnly = true)
	public List<Order> findAll(String username, String userType) {
		List<Order> orderList = isAdmin(userType) ? orderRepository.findAll()
				: orderRepository.findAllByCreatedBy(username);
		return orderList;
	}

	@Transactional(readOnly = true)
	public Optional<Order> findOne(Long id, String username) {
		Optional<Order> order = java.util.Optional.empty();
		try {
			if (username != null) {
				order = orderRepository.findByIdAndCreatedBy(id, username);
			} else {
				order = orderRepository.findById(id);
			}
			return order;
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		return order;
	}

	public Order createOrder(CreateOrderRequest req, String username) {
		Order order = new Order();
		order.setName(req.getName());
		order.setDestination(req.getDestination());
		order.setNote(req.getNote());
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setStatus(OrderStatus.INITIAL);
		order.setCreatedBy(username);
		order.setWeight(12.00);
		order.setAmount(BigDecimal.valueOf(req.getAmount()));
		order.setCourier(username);

		return orderRepository.save(order);
	}

	public Order changeDestination(ChangeOrderDestinationRequest request, String username) {
		Optional<Order> order = findOne(request.getOrderId(), username);
		Order orderVal = new Order();
		if (order.isPresent()) {
			orderVal = order.get();
			orderVal.setDestination(request.getNewDestination());
		}
		return orderRepository.save(orderVal);
	}

	public void cancelOrder(Long id, String username) {
		Optional<Order> order = findOne(id, username);
		Order orderVal = new Order();
		if (order.isPresent()) {
			orderVal = order.get();
			if (orderVal.getStatus().ordinal() > OrderStatus.PENDING.ordinal()) {
				throw new OrderCanNotCancelException();
			}
		}
		orderVal.setStatus(OrderStatus.CANCEL);
		orderRepository.save(orderVal);
	}

	public Order changeStatus(ChangeOrderStatusRequest event, String username) {
		Order orderVal = new Order();
		Optional<Order> order;
		try {
			if (username != null) {
				order = orderRepository.findByIdAndCreatedBy(event.getOrderId(), username);
			} else {
				order = orderRepository.findById(event.getOrderId());
			}
			if (order.isPresent()) {
				orderVal = order.get();
				orderVal.setStatus(event.getNewOrderStatus());
			}
			return orderRepository.save(orderVal);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		return orderVal;

	}

	public Order assignCourier(AssignCourierRequest request, String username) {
		
		Order orderVal = new Order();
		Optional<Order> order;
		try {
			if (username != null) {
				order = orderRepository.findByIdAndCreatedBy(request.getOrderId(), username);
			} else {
				order = orderRepository.findById(request.getOrderId());
			}
			
			if (order.isPresent()) {
				orderVal = order.get();
				orderVal.setCourier(request.getCourierUsername());
				orderVal.setStatus(OrderStatus.PENDING);
			}
			return orderRepository.save(orderVal);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}		
		return orderVal;
	}

	private boolean isAdmin(String userType) {
		if (UserType.ADMIN.equals(userType)) {
			return true;
		}
		return false;
	}
}
