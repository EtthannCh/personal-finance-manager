package com.financemanager.financemanager.inventory.unit_of_measure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UOMCombobox {
    private Long uomId;
    private String uomName;
    private String uomCode;
}
