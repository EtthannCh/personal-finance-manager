package com.financemanager.financemanager.inventory.material_item.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public record MaterialItem(
        @Id Long id,
        String name,
        String description,
        String uom_id,
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
