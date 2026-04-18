package com.codal.erp.dto;

import lombok.Data;

@Data
public class FornecedorResponseDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;
    private Boolean status;


}

