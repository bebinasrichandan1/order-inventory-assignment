package com.korber.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
public class InventoryBatch {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long productId;

    private int quantity;

    private LocalDate expiryDate;

}
