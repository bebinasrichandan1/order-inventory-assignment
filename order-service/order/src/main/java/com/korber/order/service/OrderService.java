package com.korber.order.service;

import com.korber.order.model.InventoryBatch;
import com.korber.order.model.Order;
import com.korber.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private RestTemplate restTemplate;
    private OrderRepository repository;

    public String placeOrder(Long productId, int quantity) {

        // Check inventory
        ResponseEntity<InventoryBatch[]> inventoryResponse =
                restTemplate.getForEntity("http://localhost:8081/inventory/" + productId,
                        InventoryBatch[].class);
        InventoryBatch[] batches = inventoryResponse.getBody();
        int available = Arrays.stream(batches)
                .mapToInt(InventoryBatch::getQuantity)
                .sum();

        if (available < quantity) {
            return "Not enough stock";
        }
        // Update inventory
        Map<String, Object> body = Map.of(
                "productId", productId,
                "quantity", quantity
        );
        restTemplate.postForEntity("http://localhost:8081/inventory/update", body, String.class);
        // Save order
        Order order = new Order(null, productId, quantity, LocalDate.now());
        repository.save(order);
        return "Order placed";
    }
}

