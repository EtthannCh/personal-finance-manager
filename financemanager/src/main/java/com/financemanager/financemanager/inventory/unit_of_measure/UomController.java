package com.financemanager.financemanager.inventory.unit_of_measure;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financemanager.financemanager.inventory.unit_of_measure.model.UOMCombobox;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/unit-of-measure")
public class UomController {

    private final UomService uomService;

    public UomController(UomService uomService){
        this.uomService = uomService;
    }
    
    @PostMapping("/uom-combobox")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find Unit of Measure Combobox")
    })
    @Operation(summary = "Find Unit of Measure Combobox", description = "Find Unit of Measure Combobox")
    public List<UOMCombobox> uomComboboxByListId(
            @RequestBody List<Long> ids) {
        return uomService.uomComboboxByListId(ids);
    }
}
