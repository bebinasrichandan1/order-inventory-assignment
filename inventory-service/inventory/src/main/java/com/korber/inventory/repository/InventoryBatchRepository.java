package com.korber.inventory.repository;

import com.korber.inventory.model.InventoryBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryBatchRepository extends JpaRepository<InventoryBatch, Long> {

    List<InventoryBatch> findByProductIdOrderByExpiryDateAsc(Long productId);

}
