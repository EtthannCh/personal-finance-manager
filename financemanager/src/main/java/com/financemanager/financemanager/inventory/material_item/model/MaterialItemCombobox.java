package com.financemanager.financemanager.inventory.material_item.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financemanager.financemanager.inventory.unit_of_measure.model.UOMCombobox;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialItemCombobox {
    private Long materialItemId;
    private String materialItemName;
    private List<UOMCombobox> uomCombobox;
}
