package com.codal.erp.controller;

import com.codal.erp.dto.VendaRequestDTO;
import com.codal.erp.dto.VendaResponseDTO;
import com.codal.erp.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService service;

    @PostMapping
    public VendaResponseDTO realizarVenda(@RequestBody @Valid VendaRequestDTO dto) {
        return service.realizarVenda(dto);
    }
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        service.cancelarVenda(id);
        return ResponseEntity.ok("Venda cancelada com sucesso");
    }
}