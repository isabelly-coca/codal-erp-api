package com.codal.erp.dto;

import lombok.Data;
import com.codal.erp.model.TipoMovimentacao;


@Data
public class DevolucaoTrocaRequestDTO {

    private Long vendaId;
    private TipoMovimentacao tipo;
    private String motivo;


}