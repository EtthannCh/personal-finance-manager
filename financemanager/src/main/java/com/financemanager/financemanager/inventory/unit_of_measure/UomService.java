package com.financemanager.financemanager.inventory.unit_of_measure;

import java.util.List;

import com.financemanager.financemanager.inventory.unit_of_measure.model.UOMCombobox;

public interface UomService {
    public List<UOMCombobox> uomComboboxByListId(List<Long> ids);

    public List<Long> findUOMIdList();
}
