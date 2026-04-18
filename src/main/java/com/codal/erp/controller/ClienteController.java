package com.codal.erp.controller;

import com.codal.erp.dto.ClienteRequestDTO;
import com.codal.erp.dto.ClienteResponseDTO;
import com.codal.erp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ClienteResponseDTO salvar(@RequestBody @Valid ClienteRequestDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public List<ClienteResponseDTO> buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable Long id,
                                        @RequestBody @Valid ClienteRequestDTO dto) {
        return service.atualizar(id, dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable Long id) {
        service.reativar(id);
        return ResponseEntity.noContent().build();
    }
}
