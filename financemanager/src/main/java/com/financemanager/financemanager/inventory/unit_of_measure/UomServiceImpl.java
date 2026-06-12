package com.financemanager.financemanager.inventory.unit_of_measure;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financemanager.financemanager.inventory.unit_of_measure.model.UOMCombobox;

@Service
public class UomServiceImpl implements UomService {

    private final UomRepository uomRepository;

    public UomServiceImpl(UomRepository uomRepository){
        this.uomRepository = uomRepository;
    }

    @Override
    public List<UOMCombobox> uomComboboxByListId(List<Long> ids) {
        return uomRepository.uomComboboxByListId(ids);
    }

    @Override
    public List<Long> findUOMIdList() {
       return uomRepository.findUOMIdList();
    }
}
