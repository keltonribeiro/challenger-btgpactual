package com.head.btgpactual.challenger.controller;

import com.head.btgpactual.challenger.dto.ApiResponse;
import com.head.btgpactual.challenger.dto.PaginationResponse;
import com.head.btgpactual.challenger.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/customers/{customerId/orders}")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10")  Integer pageSize) {
        var body = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new ApiResponse<>(
                body.getContent(), PaginationResponse.fromPage(body)
        ));
    }

}
