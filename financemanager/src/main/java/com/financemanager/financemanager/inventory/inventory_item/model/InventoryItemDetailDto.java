package com.financemanager.financemanager.inventory.inventory_item.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryItemDetailDto {
    private Long id;
    private Long inventoryItemId;
    private Long supplierId;
    private LocalDate suppliedDate;
    private Double balanceQty;
    private Double reserveQty;
    private BigDecimal price;
    private UUID createdById;
    private String createdBy;
    private LocalDateTime createdAt;
    private UUID lastUpdatedById;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;
    private UUID prevUpdatedById;
    private String prevUpdatedBy;
    private LocalDateTime prevUpdatedAt;

    private Set<InventoryItemDetailDto> inventoryItemDetail;

    public InventoryItemDetailDto fromRecord(InventoryItemDetail iid) {
        if (iid == null)
            return null;

        return new InventoryItemDetailDto()
                .setId(iid.id())
                .setInventoryItemId(iid.inventory_item_id())
                .setSupplierId(iid.supplier_id())
                .setSuppliedDate(iid.supplied_date())
                .setBalanceQty(iid.balance_qty())
                .setReserveQty(iid.reserve_qty())
                .setPrice(iid.price())
                .setCreatedById(iid.created_by_id())
                .setCreatedBy(iid.created_by())
                .setCreatedAt(iid.created_at())
                .setLastUpdatedById(iid.last_updated_by_id())
                .setLastUpdatedBy(iid.last_updated_by())
                .setLastUpdatedAt(iid.last_updated_at())
                .setPrevUpdatedById(iid.prev_updated_by_id())
                .setPrevUpdatedBy(iid.prev_updated_by())
                .setPrevUpdatedAt(iid.prev_updated_at());
    }

    public List<InventoryItemDetailDto> fromRecordList(List<InventoryItemDetail> listIid) {
        if (listIid.isEmpty())
            return Collections.emptyList();

        List<InventoryItemDetailDto> result = new ArrayList<>();
        for (InventoryItemDetail iid : listIid) {
            result.add(fromRecord(iid));
        }

        return result;
    }
}
