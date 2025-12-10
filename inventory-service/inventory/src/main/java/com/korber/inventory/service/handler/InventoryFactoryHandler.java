package com.korber.inventory.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryFactoryHandler {
    private DefaultInventoryHandler defaultHandler;
    public InventoryHandler getHandler(String type) {
        return defaultHandler;
    }
}