package com.korber.inventory.service;

import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.repository.InventoryBatchRepository;
import com.korber.inventory.service.handler.InventoryHandler;
import com.korber.inventory.service.handler.InventoryFactoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private InventoryBatchRepository repository;
    private InventoryFactoryHandler handlerFactory;

    public List<InventoryBatch> getInventoryByProduct(Long productId) {
        return repository.findByProductIdOrderByExpiryDateAsc(productId);
    }

    public void updateInventory(Long productId, int quantity) {
        InventoryHandler handler = handlerFactory.getHandler("default");
        handler.updateInventory(productId, quantity);
    }
}
