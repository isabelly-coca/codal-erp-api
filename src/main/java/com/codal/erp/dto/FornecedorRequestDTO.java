package com.codal.erp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FornecedorRequestDTO {


@NotBlank
private String nome;

@NotBlank
private String cnpj;

@NotBlank
private String telefone;

@Email
private String email;

private String endereco;


}