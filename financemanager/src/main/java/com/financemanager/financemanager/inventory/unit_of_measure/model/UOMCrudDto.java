package com.financemanager.financemanager.inventory.unit_of_measure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financemanager.financemanager.header.HeaderCollections;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UOMCrudDto {
    private String name;
    private String code;

    public UOM torecord(HeaderCollections header) {
        return new UOM(null, name, code,
                header.getUserId(), header.getUserName(), null,
                header.getUserId(), header.getUserName(), null,
                header.getUserId(), header.getUserName(), null);
    }
}
