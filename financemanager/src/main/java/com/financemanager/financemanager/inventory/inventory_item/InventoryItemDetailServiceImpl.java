package com.financemanager.financemanager.inventory.inventory_item;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.financemanager.financemanager.exception.BusinessException;
import com.financemanager.financemanager.header.HeaderCollections;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDetailCrudDto;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDetailDto;

@Service
public class InventoryItemDetailServiceImpl implements InventoryItemDetailService {

    private final InventoryItemDetailRepository inventoryItemDetailRepository;
    private final InventoryItemService inventoryItemService;

    public InventoryItemDetailServiceImpl(
            InventoryItemDetailRepository inventoryItemDetailRepository,
            InventoryItemService inventoryItemService) {
        this.inventoryItemDetailRepository = inventoryItemDetailRepository;
        this.inventoryItemService = inventoryItemService;
    }

    @Override
    public Long createInventoryItemDetail(InventoryItemDetailCrudDto iidCrud, HeaderCollections header)
            throws Exception {
        try {
            inventoryItemService.findById(iidCrud.getInventoryItemId())
                    .orElseThrow(() -> new BusinessException("INVENTORYITEMDETAIL_INVENTORYITEM_IDNOTFOUND", "460",
                            HttpStatus.NOT_FOUND));

            if (iidCrud.getBalanceQty() < 0)
                throw new BusinessException("INVENTORYITEMDETAIL_BALANCEQTYBELOWZERO", "460", HttpStatus.BAD_REQUEST);

            if (iidCrud.getPrice().compareTo(BigDecimal.valueOf(0)) < 0) {
                throw new BusinessException("INVENTORYITEMDETAIL_PRICEBELOWZERO", "460", HttpStatus.BAD_REQUEST);
            }

            return inventoryItemDetailRepository.create(iidCrud.toRecord(header));
        } catch (DuplicateKeyException e) {
            throw e;
        }
    }

    @Override
    public void updateInventoryItemDetail(Long id, InventoryItemDetailCrudDto iidCrud, HeaderCollections header)
            throws Exception {
        try {
            inventoryItemDetailRepository.update(id, iidCrud.toRecord(header));
        } catch (DuplicateKeyException e) {
            throw e;
        }
    }

    @Override
    public Set<InventoryItemDetailDto> fInventoryItemDetailByInventoryItemId(Long inventoryItemId) {
        return inventoryItemDetailRepository.findListByInventoryItemId(inventoryItemId);
    }

}
