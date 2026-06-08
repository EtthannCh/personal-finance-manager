package com.financemanager.financemanager.inventory.inventory_item.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public record InventoryItemDetail(
        @Id Long id,
        Long inventory_item_id,
        Long supplier_id,
        LocalDate supplied_date,
        Double balance_qty,
        Double reserve_qty,
        BigDecimal price,
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
