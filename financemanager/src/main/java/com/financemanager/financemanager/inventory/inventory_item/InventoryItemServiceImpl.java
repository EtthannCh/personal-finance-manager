package com.financemanager.financemanager.inventory.inventory_item;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.financemanager.financemanager.exception.BusinessException;
import com.financemanager.financemanager.header.HeaderCollections;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemCrudDto;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDetailDto;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDto;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemDetailRepository inventoryItemDetailRepository;

    public InventoryItemServiceImpl(
            InventoryItemRepository inventoryItemRepository,
            InventoryItemDetailRepository inventoryItemDetailRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.inventoryItemDetailRepository = inventoryItemDetailRepository;
    }

    @Override
    public Long createInventoryItem(InventoryItemCrudDto iiCrudDto, HeaderCollections header) throws Exception {
        try {
            return inventoryItemRepository.create(iiCrudDto.toRecord(header));
        } catch (DuplicateKeyException e) {
            throw new BusinessException(e.getMessage(), "460", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void updateInventoryItem(Long id, InventoryItemCrudDto iiCrudDto, HeaderCollections header)
            throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInventoryItem'");
    }

    @Override
    public Optional<InventoryItemDto> findById(Long id) {
        return inventoryItemRepository.findById(id);
    }

    @Override
    public Page<InventoryItemDto> findPaginated(Pageable pageable, LocalDate startDate, LocalDate endDate,
            Set<Long> materialItemId) {
        List<InventoryItemDto> iiList = inventoryItemRepository
                .findPaginated(pageable, startDate, endDate, materialItemId).getContent();
        for (InventoryItemDto inventoryItemDto : iiList) {
            Set<InventoryItemDetailDto> iidSet = inventoryItemDetailRepository
                    .findListByInventoryItemId(inventoryItemDto.getId());
            inventoryItemDto.setIidSet(iidSet);
        }
        return new PageImpl<>(iiList);
    }

}
