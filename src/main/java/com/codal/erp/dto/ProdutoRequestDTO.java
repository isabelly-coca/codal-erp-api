package com.codal.erp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoRequestDTO {

    @NotBlank
    private String nome;

    private Long categoriaId;

    @NotBlank
    private String ean;

    @NotBlank
    private String ncm;

    private String foto;

    @NotNull
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    private Integer quantidade;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor de custo deve ser maior que zero")
    private BigDecimal valorCusto;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor de venda deve ser maior que zero")
    private BigDecimal valorVenda;

    private Boolean status = true;
}
