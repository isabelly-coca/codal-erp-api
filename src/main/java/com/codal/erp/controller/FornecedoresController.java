package com.codal.erp.controller;


import com.codal.erp.dto.FornecedorRequestDTO;
import com.codal.erp.dto.FornecedorResponseDTO;
import com.codal.erp.model.Fornecedores;
import com.codal.erp.service.FornecedoresService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedoresController {

    @Autowired
    private FornecedoresService service;

    @PostMapping
    public FornecedorResponseDTO salvar(@RequestBody @Valid FornecedorRequestDTO dto) {
        return service.salvar(dto);
    }


    @GetMapping
    public List<Fornecedores> listar() {
        return service.listar();
    }
}
