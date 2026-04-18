package com.codal.erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataVenda;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private BigDecimal valorFinal;

    private String formaPagamento;

    private Boolean status = true;

    private String vendedor;

    private String observacao;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens;
}