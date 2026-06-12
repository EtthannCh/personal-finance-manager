package com.financemanager.financemanager.inventory.material_item;

import java.util.List;
import java.util.Optional;

import com.financemanager.financemanager.header.HeaderCollections;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemCombobox;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemCrudDto;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemDto;

public interface MaterialItemService {

    public Optional<MaterialItemDto> findById(Long miId);

    public List<MaterialItemCombobox> materialItemCombobox(String name);

    public Long createMaterialItem(MaterialItemCrudDto miCrudDto, HeaderCollections header) throws Exception;
}
