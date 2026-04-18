package com.codal.erp.controller;

import com.codal.erp.dto.ClienteRequestDTO;
import com.codal.erp.dto.ProdutoRequestDTO;
import com.codal.erp.dto.ProdutoResponseDTO;
import com.codal.erp.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ProdutoResponseDTO salvar(@RequestBody @Valid ProdutoRequestDTO dto) {
        return service.salvar(dto);
    }
    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    @GetMapping("/buscar")
    public List<ProdutoResponseDTO> buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id,
                                        @RequestBody @Valid ProdutoRequestDTO dto) {
        return service.atualizar(id, dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/reativar/{id}")
    public void reativar(@PathVariable Long id) {
        service.reativar(id);
    }
}
