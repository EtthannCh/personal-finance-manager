package com.financemanager.financemanager.inventory.inventory_item.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financemanager.financemanager.header.HeaderCollections;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryItemCrudDto {
    private Long materialItemId;
    private String materialItemName;
    private Double qty;

    public InventoryItem toRecord(HeaderCollections header) {
        return new InventoryItem(
                null, materialItemId, materialItemName, qty,
                header.getUserId(), header.getUserName(), null,
                header.getUserId(), header.getUserName(), null,
                header.getUserId(), header.getUserName(), null);
    }

}
