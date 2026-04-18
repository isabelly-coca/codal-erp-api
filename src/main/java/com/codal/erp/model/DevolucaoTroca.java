package com.codal.erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class DevolucaoTroca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    private LocalDateTime data;

    private String motivo;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;
}
