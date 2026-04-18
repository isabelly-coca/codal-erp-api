package com.codal.erp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VendaResponseDTO {

    private Long id;
    private LocalDateTime dataVenda;
    private BigDecimal valorTotal;
    private String formaPagamento;
}