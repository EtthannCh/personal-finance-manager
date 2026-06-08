package com.financemanager.financemanager.inventory.inventory_item;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItem;

@Repository
public class InventoryItemRepository {

    private final JdbcClient jdbcClient;

    public InventoryItemRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Long create(InventoryItem ii) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
                insert into inventory_item
                (
                    material_item_id, material_item_name, qty,
                    created_by_id, created_by, created_at,
                    last_updated_by_id, last_updated_by, last_updated_at,
                    prev_updated_by_id, prev_updated_by, prev_updated_at
                )
                values
                (
                    :materialItemId, :materialItemName, :qty,
                    :createdById, :createdBy, :createdAt,
                    :lastUpdatedById, :lastUpdatedBy, :lastUpdatedAt,
                    :prevUpdatedById, :prevUpdatedBy, :prevUpdatedAt
                )
                """)
                .param("materialItemId", ii.material_item_id())
                .param("materialItemName", ii.material_item_name())
                .param("qty", ii.qty())
                .param("createdById", ii.created_by_id())
                .param("createdBy", ii.created_by())
                .param("createdAt", ii.created_at())
                .param("lastUpdatedById", ii.last_updated_by_id())
                .param("lastUpdatedBy", ii.last_updated_by())
                .param("lastUpdatedAt", ii.last_updated_at())
                .update(keyHolder, "id");

        return keyHolder.getKey().longValue();
    }
}
