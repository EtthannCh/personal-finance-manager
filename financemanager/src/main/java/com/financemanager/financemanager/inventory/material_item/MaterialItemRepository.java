package com.financemanager.financemanager.inventory.material_item;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.financemanager.financemanager.inventory.material_item.model.MaterialItem;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemCombobox;

@Repository
public class MaterialItemRepository {
    private final JdbcClient jdbcClient;

    public MaterialItemRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<MaterialItem> findById(Long miId) {
        return jdbcClient.sql("""
                select
                    id,name,description,
                    array_to_string(uom, ',') uom_id,
                    created_by,created_by_id, created_at,
                    last_updated_by, last_updated_by_id, last_updated_at,
                    prev_updated_by, prev_updated_by_id, prev_updated_at
                from material_item
                where id = :miId
                """)
                .param("miId", miId)
                .query(MaterialItem.class)
                .optional();
    }

    public List<MaterialItemCombobox> materialItemCombobox(String name) {
        return jdbcClient.sql("""
                select id materialItemId, name materialItemName
                from material_item
                """
                + (name != null ? " where upper(name) LIKE '%' || upper(:name) || '%' " : ""))
                .param("name", name)
                .query(MaterialItemCombobox.class)
                .list();
    }

    public Long create(MaterialItem mi) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
            insert into material_item
            (
                name, description, uom,
                created_by, created_by_id, created_at,
                last_updated_by, last_updated_by_id, last_updated_at
            )
            values
            (
                :name, :description, string_to_array(:uom, ',')::int4[],
                :createdBy, :createdById, now(),
                :lastUpdatedBy, :lastUpdatedById, now()
            )
            """)
            .param("name", mi.name())
            .param("description", mi.description())
            .param("uom", mi.uom_id())
            .param("createdBy", mi.created_by())
            .param("createdById", mi.created_by_id())
            .param("lastUpdatedBy", mi.last_updated_by())
            .param("lastUpdatedById", mi.last_updated_by_id())
            .update(keyHolder, "id");
        
        return keyHolder.getKey().longValue();
    }
}
