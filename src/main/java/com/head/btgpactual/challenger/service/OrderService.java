package com.head.btgpactual.challenger.service;

import com.head.btgpactual.challenger.controller.OrderResponse;
import com.head.btgpactual.challenger.dto.OrderCreatedEvent;
import com.head.btgpactual.challenger.entity.OrderEntity;
import com.head.btgpactual.challenger.entity.OrderItem;
import com.head.btgpactual.challenger.repository.OrderRepositoy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepositoy orderRepositoy;

    public OrderService(OrderRepositoy orderRepositoy) {
        this.orderRepositoy = orderRepositoy;
    }

    public void save(OrderCreatedEvent event) {
        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setTotal(getTotal(event));
        entity.setItens(getOrderItems(event));
        orderRepositoy.save(entity);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(i -> new OrderItem(i.produto(), i.preco(), i.quantidade())).
                toList();
    }

    private static BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepositoy.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponse::fromEntity);
    }
}
