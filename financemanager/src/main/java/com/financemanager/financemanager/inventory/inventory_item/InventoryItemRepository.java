package com.financemanager.financemanager.inventory.inventory_item;

import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.financemanager.financemanager.exception.BusinessException;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItem;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDto;

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

    public void update(Long id, InventoryItem ii) {
        int update = jdbcClient.sql("""
                update inventory_item
                set
                    material_item_id = :materialItemId,
                    material_item_name = :materialItemName,
                    last_updated_by_id = :lastUpdatedById,
                    last_updated_by = :lastUpdatedBy,
                    last_updated_at = :lastUpdatedAt,
                    prev_updated_by_id = :prevUpdatedById,
                    prev_updated_by = :prevUpdatedBy,
                    prev_updated_at = :prevUpdatedAt
                where id = :id
                """)
                .param("id", id)
                .param("materialItemId", ii.material_item_id())
                .param("materialItemName", ii.material_item_name())
                .param("lastUpdatedById", ii.last_updated_by_id())
                .param("lastUpdatedBy", ii.last_updated_by())
                .param("lastUpdatedAt", ii.last_updated_at())
                .param("prevUpdatedById", ii.prev_updated_by_id())
                .param("prevUpdatedBy", ii.prev_updated_by())
                .param("prevUpdatedAt", ii.prev_updated_at())
                .param("id", ii.id())
                .update();

        if (update == 0)
            throw new BusinessException("INVENTORYITEMDETAIL_IDNOTFOUND", "460", HttpStatus.NOT_FOUND);
    }

    public Optional<InventoryItemDto> findById(Long id) {
        return jdbcClient.sql("""
                select *
                from inventory_item
                where id = :id
                """)
                .param("id")
                .query(InventoryItemDto.class)
                .optional();
    }

    public Page<InventoryItemDto> findPaginated(Pageable pageable, LocalDate startDate, LocalDate endDate,
            Set<Long> materialItemId) {
        String paginationString = "limit " + pageable.getPageSize() + " offset "
                + pageable.getPageNumber() * pageable.getPageSize();

        List<Long> materialItemIds = materialItemId.isEmpty() ? new ArrayList<>()
                : materialItemId.stream().toList();

        String sql = """
                select *
                from inventory_item
                where 1=1
                """ +
                (startDate != null ? " and created_at > to_date(:startDate)" : "") +
                (endDate != null ? " and created_at < to_date(:endDate)" : "") +
                (!materialItemId.isEmpty() ? " and material_item_id in (:materialItemIdList)" : "") +
                paginationString;
            
        List<InventoryItem> iiList = jdbcClient.sql(sql)
                .param("startDate", startDate)
                .param("endDate", endDate)
                .param("materialItemIdList", materialItemIds)
                .query(InventoryItem.class)
                .list();

        return new PageImpl<>(InventoryItemDto.fromRecordList(iiList), pageable, iiList.size());
    }
}
