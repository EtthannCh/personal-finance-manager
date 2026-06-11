package com.financemanager.financemanager.inventory.inventory_item;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.financemanager.financemanager.header.HeaderCollections;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemCrudDto;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDto;

public interface InventoryItemService {
    public Long createInventoryItem(InventoryItemCrudDto iiCrudDto, HeaderCollections header) throws Exception;

    public void updateInventoryItem(Long id, InventoryItemCrudDto iiCrudDto, HeaderCollections header) throws Exception;

    public Optional<InventoryItemDto> findById(Long id);

    public Page<InventoryItemDto> findPaginated(Pageable pageable, LocalDate startDate, LocalDate endDate, Set<Long> materialItemId);
}
