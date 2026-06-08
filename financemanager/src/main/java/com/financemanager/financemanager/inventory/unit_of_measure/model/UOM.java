package com.financemanager.financemanager.inventory.unit_of_measure.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Id;

public record UOM(
        @Id Long id,
        String name,
        String code,
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
