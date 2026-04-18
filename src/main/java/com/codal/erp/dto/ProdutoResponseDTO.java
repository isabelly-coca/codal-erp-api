package com.codal.erp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProdutoResponseDTO {

    private Long id;

    private String nome;

    private String ean;

    private String ncm;

    private String foto;


    private Integer quantidade;

    private BigDecimal valorCusto;

    private BigDecimal valorVenda;

    private Boolean status;
}
