package com.financemanager.financemanager.inventory.inventory_item;

import java.util.Set;

import com.financemanager.financemanager.header.HeaderCollections;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDetailCrudDto;
import com.financemanager.financemanager.inventory.inventory_item.model.InventoryItemDetailDto;

public interface InventoryItemDetailService {
        public Long createInventoryItemDetail(InventoryItemDetailCrudDto iidCrud, HeaderCollections header)
                        throws Exception;

        public void updateInventoryItemDetail(Long id, InventoryItemDetailCrudDto iidCrud, HeaderCollections header)
                        throws Exception;

        public Set<InventoryItemDetailDto> fInventoryItemDetailByInventoryItemId(Long inventoryItemId);

}
