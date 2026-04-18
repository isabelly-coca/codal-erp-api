package com.codal.erp.controller;

import com.codal.erp.dto.DevolucaoTrocaRequestDTO;
import com.codal.erp.service.DevolucaoTrocaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimentacoes")
public class DevolucaoTrocaController {

    @Autowired
    private DevolucaoTrocaService service;

    @PostMapping
    public void processar(@RequestBody DevolucaoTrocaRequestDTO dto) {
        service.processar(dto);
    }
}