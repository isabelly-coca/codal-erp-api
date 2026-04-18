package com.codal.erp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import com.codal.erp.dto.ItemVendaDTO;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VendaRequestDTO {

    @NotBlank
    private String formaPagamento;

    @NotEmpty
    private List<ItemVendaDTO> itens;

    private Long clienteId;

    private BigDecimal desconto;
}
