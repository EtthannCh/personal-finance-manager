package com.financemanager.financemanager.inventory.material_item.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialItemDto {
    private Long id;
    private String name;
    private String description;
    private List<Long> uomIds;
    private UUID createdById;
    private String createdBy;
    private LocalDateTime createdAt;
    private UUID lastUpdatedById;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;
    private UUID prevUpdatedById;
    private String prevUpdatedBy;
    private LocalDateTime prevUpdatedAt;

    public static MaterialItemDto fromRecord(MaterialItem mi) {
        if (mi == null)
            return null;

        return new MaterialItemDto().setId(mi.id())
                .setName(mi.name())
                .setDescription(mi.description())
                .setUomIds(
                        Arrays.asList(mi.uom_id().split(",")).stream().map(Long::valueOf).collect(Collectors.toList()))
                .setCreatedById(mi.created_by_id())
                .setCreatedBy(mi.created_by())
                .setCreatedAt(mi.created_at())
                .setLastUpdatedById(mi.last_updated_by_id())
                .setLastUpdatedBy(mi.last_updated_by())
                .setLastUpdatedAt(mi.last_updated_at())
                .setPrevUpdatedById(mi.prev_updated_by_id())
                .setPrevUpdatedBy(mi.prev_updated_by())
                .setPrevUpdatedAt(mi.prev_updated_at());
    }
}
