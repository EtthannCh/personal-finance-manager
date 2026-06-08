package com.financemanager.financemanager.inventory.inventory_item.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public record InventoryItem(
                @Id Long id,
                Long material_item_id,
                String material_item_name,
                Double qty,
                UUID created_by_id,
                String created_by,
                LocalDateTime created_at,
                UUID last_updated_by_id,
                String last_updated_by,
                LocalDateTime last_updated_at,
                UUID prev_updated_by_id,
                String prev_updated_by,
                LocalDateTime prev_updated_at) {

}
