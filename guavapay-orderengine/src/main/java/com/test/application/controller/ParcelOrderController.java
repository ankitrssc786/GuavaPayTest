package com.test.application.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.application.domain.Order;
import com.test.application.model.AssignCourierRequest;
import com.test.application.model.ChangeOrderDestinationRequest;
import com.test.application.model.ChangeOrderStatusRequest;
import com.test.application.model.CreateOrderRequest;
import com.test.application.service.OrderService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Validated
public class ParcelOrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<Order> getAllOrders() {

		String username = "Ankit";
		String userType = "User";
		return orderService.findAll(username, userType);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@NotNull @PathVariable Long id) {
		String username = "Ankit";
		String userType = "User";
		Optional<Order> order;
		order = orderService.findOne(id, username);
		if (order == null) {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		} else {
			return ResponseEntity.ok(order);
		}
	}

	@PostMapping
	public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
		String username = "Ankit";
		String userType = "User";
		if (userType.equalsIgnoreCase("User")) {
			Order result = orderService.createOrder(createOrderRequest, username);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		}

	}

	@ApiOperation(value = "User can change order destination before accepted", response = Order.class)
	@PutMapping("/change-destination")
	public ResponseEntity<Order> changeDestination(@Valid @RequestBody ChangeOrderDestinationRequest request) {
		String username = "Ankit";
		String userType = "User";
		if (userType.equalsIgnoreCase("User")) {
			Order result = orderService.changeDestination(request, username);
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		}
	}

	@ApiOperation(value = "User can cancel order before accepted")
	@PutMapping("/cancel/{id}")
	public ResponseEntity<Void> cancelOrder(@NotNull @PathVariable Long id) {
		String username = "Ankit";
		String userType = "User";
		if (userType.equalsIgnoreCase("User")) {
			orderService.cancelOrder(id, username);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		}
	}

	@ApiOperation(value = "Admin can change order status", response = Order.class)
	@PutMapping("/change-status")
	public ResponseEntity<Order> changeStatus(@Valid @RequestBody ChangeOrderStatusRequest request) {
		String username = "Ankit";
		String userType = "Courier";
		if (userType.equalsIgnoreCase("Admin") || userType.equalsIgnoreCase("Courier")) {
			Order result = orderService.changeStatus(request, username);
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		}
	}

	@ApiOperation(value = "Admin assign courier for each order", response = Order.class)
	@PutMapping("/assign-courier")
	public ResponseEntity<Order> assignCourier(@Valid @RequestBody AssignCourierRequest request) {
		String username = "Ankit";
		String userType = "Admin";
		if (userType.equalsIgnoreCase("Admin")) {
			Order result = orderService.assignCourier(request, username);
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		}
	}

}
