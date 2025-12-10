package com.korber.order.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryBatch {
    private Long id;
    private Long productId;
    private int quantity;
}
