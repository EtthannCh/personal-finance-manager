package com.financemanager.financemanager.inventory.inventory_item.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryItemDto {
    private Long id;
    private Long materialItemId;
    private String materialItemName;
    private Double qty;
    private UUID createdById;
    private String createdBy;
    private LocalDateTime createdAt;
    private UUID lastUpdatedById;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;
    private UUID prevUpdatedById;
    private String prevUpdatedBy;
    private LocalDateTime prevUpdatedAt;

    public InventoryItemDto fromRecord(InventoryItem ii) {
        if (ii == null)
            return null;

        return new InventoryItemDto()
                .setId(ii.id())
                .setMaterialItemId(ii.material_item_id())
                .setMaterialItemName(ii.material_item_name())
                .setQty(ii.qty())
                .setCreatedById(ii.created_by_id())
                .setCreatedBy(ii.created_by())
                .setCreatedAt(ii.created_at())
                .setLastUpdatedById(ii.last_updated_by_id())
                .setLastUpdatedBy(ii.last_updated_by())
                .setLastUpdatedAt(ii.last_updated_at())
                .setPrevUpdatedById(ii.prev_updated_by_id())
                .setPrevUpdatedBy(ii.prev_updated_by())
                .setPrevUpdatedAt(ii.prev_updated_at());
    }

    public List<InventoryItemDto> fromRecordList(List<InventoryItem> listIi) {
        if (listIi.isEmpty())
            return Collections.emptyList();

        List<InventoryItemDto> listIiDto = new ArrayList<>();
        for (InventoryItem ii : listIi) {
            listIiDto.add(fromRecord(ii));
        }

        return listIiDto;
    }
}
