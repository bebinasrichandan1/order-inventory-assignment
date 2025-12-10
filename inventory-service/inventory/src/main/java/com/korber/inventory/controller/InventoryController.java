package com.korber.inventory.controller;

import com.korber.inventory.model.InventoryBatch;
import com.korber.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public List<InventoryBatch> getInventory(@PathVariable Long productId) {
        return inventoryService.getInventoryByProduct(productId);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateInventory(@RequestBody Map<String, Object> req) {
        Long productId = Long.valueOf(req.get("productId").toString());
        int quantity = Integer.parseInt(req.get("quantity").toString());

        inventoryService.updateInventory(productId, quantity);
        return ResponseEntity.ok("Updated inventory");
    }

}
