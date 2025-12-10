package com.korber.inventory.service.handler;

import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.repository.InventoryBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultInventoryHandler implements InventoryHandler  {

    private InventoryBatchRepository repository;

    @Override
    public void updateInventory(Long productId, int quantity) {
        List<InventoryBatch> batches =
                repository.findByProductIdOrderByExpiryDateAsc(productId);

        int remaining = quantity;

        for (InventoryBatch batch : batches) {
            if (remaining <= 0) break;

            int deduct = Math.min(batch.getQuantity(), remaining);
            batch.setQuantity(batch.getQuantity() - deduct);
            remaining -= deduct;

            repository.save(batch);
        }
    }

}
