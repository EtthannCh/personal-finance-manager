package com.financemanager.financemanager.inventory.material_item;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financemanager.financemanager.exception.BusinessException;
import com.financemanager.financemanager.header.HeaderCollections;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItem;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemCombobox;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemCrudDto;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemDto;
import com.financemanager.financemanager.inventory.unit_of_measure.UomService;
import com.financemanager.financemanager.inventory.unit_of_measure.model.UOMCombobox;

@Service
public class MaterialItemServiceImpl implements MaterialItemService {

    private final MaterialItemRepository materialItemRepository;
    private final UomService uomService;

    public MaterialItemServiceImpl(
            MaterialItemRepository materialItemRepository,
            UomService uomService) {
        this.materialItemRepository = materialItemRepository;
        this.uomService = uomService;
    }

    @Override
    public Optional<MaterialItemDto> findById(Long miId) {
        Optional<MaterialItem> mi = materialItemRepository.findById(miId);
        if (mi.isPresent()) {
            return Optional.of(MaterialItemDto.fromRecord(mi.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<MaterialItemCombobox> materialItemCombobox(String name) {
        List<MaterialItemCombobox> miComboboxes = materialItemRepository.materialItemCombobox(name);
        for (MaterialItemCombobox miCbx : miComboboxes) {
            Optional<MaterialItemDto> mi = this.findById(miCbx.getMaterialItemId());
            if (mi.isPresent()) {
                List<Long> uomIds = mi.get().getUomIds();
                List<UOMCombobox> uomList = uomService.uomComboboxByListId(uomIds);
                miCbx.setUomCombobox(uomList);
            }
        }
        return miComboboxes;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createMaterialItem(MaterialItemCrudDto miCrudDto, HeaderCollections header) {
        try {
            List<Long> uomList = uomService.findUOMIdList();
            for (Long uomCrud : miCrudDto.getUomId()) {
                if(!uomList.contains(uomCrud)){
                    throw new BusinessException("MATERIALITEM_UOMNOTFOUND", "460", HttpStatus.BAD_REQUEST);
                }
            }

            return materialItemRepository.create(miCrudDto.toRecord(header));
        } catch (DuplicateKeyException e) {
            throw new BusinessException(e.getMessage(), "460", HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
