package com.head.btgpactual.challenger.controller;

import com.head.btgpactual.challenger.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(Long orderId, Long custormerId, BigDecimal total) {
    public static OrderResponse fromEntity(OrderEntity orderEntity) {
        return new OrderResponse(orderEntity.getOrderId(), orderEntity.getCustomerId(), orderEntity.getTotal());
    }
}
