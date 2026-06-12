package com.financemanager.financemanager.inventory.material_item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financemanager.financemanager.header.HeaderCollections;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemCombobox;
import com.financemanager.financemanager.inventory.material_item.model.MaterialItemCrudDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/material-item")
public class MaterialItemController {
    private final MaterialItemService materialItemService;

    public MaterialItemController(MaterialItemService materialItemService) {
        this.materialItemService = materialItemService;
    }

    @GetMapping("/combobox")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material Item Combobox")
    })
    @Operation(summary = "Material Item Combobox", description = "Material Item Combobox.")
    public List<MaterialItemCombobox> materialItemCombobox(@RequestParam Optional<String> name) {
        return materialItemService.materialItemCombobox(name.orElse(null));
    }

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material Item Combobox")
    })
    @Operation(summary = "Material Item Combobox", description = "Material Item Combobox.")
    public Long createMaterialItem(
            @RequestHeader("user-id") UUID userId,
            @RequestHeader("user-name") String userName,
            @RequestBody MaterialItemCrudDto miCrud) throws Exception {
        HeaderCollections header = new HeaderCollections().setUserId(userId).setUserName(userName);
        return materialItemService.createMaterialItem(miCrud, header);
    }
}
