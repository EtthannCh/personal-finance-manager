package com.financemanager.financemanager.inventory.inventory_item.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financemanager.financemanager.header.HeaderCollections;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryItemDetailCrudDto {
    private Long inventoryItemId;
    private Long supplierId;
    private LocalDate suppliedDate;
    private Double balanceQty;
    private Double reserveQty;
    private BigDecimal price;

    public InventoryItemDetail toRecord(HeaderCollections header) {
        return new InventoryItemDetail(
                null, inventoryItemId, supplierId, suppliedDate, balanceQty, reserveQty, price,
                header.getUserId(), header.getUserName(), null,
                header.getUserId(), header.getUserName(), null,
                header.getUserId(), header.getUserName(), null);
    }
}
