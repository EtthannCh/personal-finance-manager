package com.financemanager.financemanager.inventory.inventory_item;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financemanager.financemanager.header.HeaderCollections;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemCrudDto;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/inventory-item")
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    public InventoryItemController(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created Inventory Item")
    })
    @Operation(summary = "Create Inventory Item", description = "Create Inventory Item.")
    public Long createInventoryItem(
            @RequestHeader("user-id") UUID userId,
            @RequestHeader("user-name") String userName,
            @RequestBody InventoryItemCrudDto iiCrud) throws Exception {
        HeaderCollections header = new HeaderCollections().setUserId(userId).setUserName(userName);
        return inventoryItemService.createInventoryItem(iiCrud, header);
    }

    @PostMapping("find-paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find Paginated Inventory Item")
    })
    @Operation(summary = "Find Paginated Inventory Item", description = "Find Paginated Inventory Item")
    public PagedModel<InventoryItemDto> findPaginated(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam Optional<String> sortBy,
            @RequestParam Direction sortDirection,
            @RequestParam Optional<LocalDate> startDate,
            @RequestParam Optional<LocalDate> endDate,
            @RequestBody Optional<Set<Long>> materialItemIdSet) {
        Sort sort = Sort.by(sortDirection, sortBy == null || sortBy.isEmpty() ? "id" : sortBy.get());
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return new PagedModel<>(
                inventoryItemService.findPaginated(pageable, startDate.orElse(null), endDate.orElse(null),
                        materialItemIdSet.orElse(new HashSet<>())));
    }

}
