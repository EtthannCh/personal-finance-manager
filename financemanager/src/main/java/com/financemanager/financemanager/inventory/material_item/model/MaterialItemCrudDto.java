package com.financemanager.financemanager.inventory.material_item.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financemanager.financemanager.header.HeaderCollections;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialItemCrudDto {
    private String name;
    private String description;
    private Long uomId;
    private String uomName;

    public MaterialItem toRecord(HeaderCollections header) {
        return new MaterialItem(null, name, description, uomId, uomName,
                header.getUserId(), header.getUserName(), null,
                header.getUserId(), header.getUserName(), null,
                header.getUserId(), header.getUserName(), null);
    }
}
