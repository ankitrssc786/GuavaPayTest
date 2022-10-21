package com.test.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.application.domain.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByIdAndCreatedBy(Long id, String createdBy);

    List<Order> findAllByCreatedBy(String createdBy);

    Optional<Order> findByOrderNumber(Long id);

}
