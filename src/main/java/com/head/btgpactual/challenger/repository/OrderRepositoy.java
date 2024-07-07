package com.head.btgpactual.challenger.repository;

import com.head.btgpactual.challenger.controller.OrderResponse;
import com.head.btgpactual.challenger.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepositoy extends MongoRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
