package com.financemanager.financemanager.inventory.inventory_item;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.financemanager.financemanager.exception.BusinessException;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDetail;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDetailDto;

@Repository
public class InventoryItemDetailRepository {

    private final JdbcClient jdbcClient;

    public InventoryItemDetailRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Long create(InventoryItemDetail iid) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
                insert into inventory_item_detail
                (
                    inventory_item_id, supplier_id, supplied_date, balance_qty, reserve_qty, price,
                    created_by_id, created_by, created_at,
                    last_updated_by_id, last_updated_by, last_updated_at
                )
                values
                (
                    :inventoryItemId, :supplierId, :suppliedDate, :balanceQty, :reserveQty, :price,
                    :createdById, :createdBy, :createdAt,
                    :lastUpdatedById, :lastUpdatedBy, :lastUpdatedAt
                )
                """)
                .param("inventoryItemId", iid.inventory_item_id())
                .param("supplierId", iid.supplier_id())
                .param("suppliedDate", iid.supplied_date())
                .param("balanceQty", iid.balance_qty())
                .param("reserveQty", Double.valueOf("0"))
                .param("price", iid.price())
                .param("createdById", iid.created_by_id())
                .param("createdBy", iid.created_by())
                .param("createdAt", iid.created_at())
                .param("lastUpdatedById", iid.last_updated_by_id())
                .param("lastUpdatedBy", iid.last_updated_by())
                .param("lastUpdatedAt", iid.last_updated_at())
                .update(keyHolder, "id");

        return keyHolder.getKey().longValue();
    }

    public void update(Long id, InventoryItemDetail iid) throws Exception {
        int update = jdbcClient.sql("""
                update inventory_item_detail
                set
                    supplier_id = :supplierId,
                    supplied_date = :suppliedDate,
                    balance_qty = :balanceQty,
                    price = :price,
                    last_updated_by_id = :lastUpdatedById,
                    last_updated_by = :lastUpdatedBy,
                    last_updated_at = :lastUpdatedAt,
                    prev_updated_by_id = :prevUpdatedById,
                    prev_updated_by = :prevUpdatedBy,
                    prev_updated_at = :prevUpdatedAt
                where id = :iidId
                    """)
                .param("supplierId", iid.supplier_id())
                .param("suppliedDate", iid.supplied_date())
                .param("balanceQty", iid.balance_qty())
                .param("price", iid.price())
                .param("lastUpdatedById", iid.last_updated_by_id())
                .param("lastUpdatedBy", iid.last_updated_by())
                .param("lastUpdatedAt", iid.last_updated_at())
                .param("prevUpdatedById", iid.prev_updated_by_id())
                .param("prevUpdatedBy", iid.prev_updated_by())
                .param("prevUpdatedAt", iid.prev_updated_at())
                .param("iidId", id)
                .update();

        if (update == 0)
            throw new BusinessException("INVENTORYITEMDETAIL_IDNOTFOUND", "460", HttpStatus.NOT_FOUND);
    }

    public Set<InventoryItemDetailDto> findListByInventoryItemId(Long inventoryItemId) {
        return jdbcClient.sql("""
                select *
                from inventory_item_detail
                where inventory_item_id = :id
                """)
                .param("id", inventoryItemId)
                .query(InventoryItemDetailDto.class)
                .set();
    }
}
