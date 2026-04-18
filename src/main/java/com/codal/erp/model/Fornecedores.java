package com.codal.erp.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Fornecedores extends Pessoa {
    @NotBlank
    private String cnpj;

    private String nomeEmpresa;
}