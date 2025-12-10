package com.korber.order.controller;

import com.korber.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private OrderService service;

    @PostMapping
    public String placeOrder(@RequestBody Map<String, Object> req) {
        Long productId = Long.valueOf(req.get("productId").toString());
        int quantity = Integer.parseInt(req.get("quantity").toString());

        return service.placeOrder(productId, quantity);
    }
}

