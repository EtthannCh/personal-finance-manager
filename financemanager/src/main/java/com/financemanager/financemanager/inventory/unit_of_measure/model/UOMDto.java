package com.financemanager.financemanager.inventory.unit_of_measure.model;

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
public class UOMDto {
    private Long id;
    private String name;
    private String code;
    private UUID createdById;
    private String createdBy;
    private LocalDateTime createdAt;
    private UUID lastUpdatedById;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;
    private UUID prevUpdatedById;
    private String prevUpdatedBy;
    private LocalDateTime prevUpdatedAt;

    public UOMDto fromRecord(UOM uom) {
        if (uom == null)
            return null;

        return new UOMDto()
                .setId(uom.id())
                .setName(uom.name())
                .setCode(uom.code())
                .setCreatedById(uom.created_by_id())
                .setCreatedBy(uom.created_by())
                .setCreatedAt(uom.created_at())
                .setLastUpdatedById(uom.last_updated_by_id())
                .setLastUpdatedBy(uom.last_updated_by())
                .setLastUpdatedAt(uom.last_updated_at())
                .setPrevUpdatedById(uom.prev_updated_by_id())
                .setPrevUpdatedBy(uom.prev_updated_by())
                .setPrevUpdatedAt(uom.prev_updated_at());
    }

    public List<UOMDto> fromRecordList(List<UOM> uomList) {
        if (uomList.isEmpty())
            return Collections.emptyList();

        List<UOMDto> uomDtoList = new ArrayList<>();
        for (UOM uom : uomList) {
            uomDtoList.add(fromRecord(uom));
        }

        return uomDtoList;
    }
}
