package com.codal.erp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @NotBlank
    private String nome;
    @NotBlank
    private String ean;
    @NotBlank
    private String ncm;
    private String foto;

    @NotNull
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    private Integer quantidade;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser maior que zero")
    private BigDecimal valorCusto;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor de venda deve ser maior que zero")
    private BigDecimal valorVenda;
    private boolean status = true ;


}
