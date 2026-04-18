package com.codal.erp.service;

import com.codal.erp.dto.FornecedorRequestDTO;
import com.codal.erp.dto.FornecedorResponseDTO;
import com.codal.erp.model.Fornecedores;
import com.codal.erp.repository.FornecedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedoresService {

    @Autowired
    private FornecedoresRepository repository;

public FornecedorResponseDTO salvar(FornecedorRequestDTO dto) {

        Fornecedores fornecedor = new Fornecedores();

        fornecedor.setNome(dto.getNome());
        fornecedor.setCnpj(limparCnpj(dto.getCnpj()));
        fornecedor.setTelefone(dto.getTelefone());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setEndereco(dto.getEndereco());

        if (fornecedor.getStatus() == null) {
            fornecedor.setStatus(true);
        }

        Fornecedores salvo = repository.save(fornecedor);

        return toResponseDTO(salvo);
    }

    public List<Fornecedores> listar() {
        return repository.findAll();
    }

    public Fornecedores buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    private FornecedorResponseDTO toResponseDTO(Fornecedores fornecedor) {
        FornecedorResponseDTO dto = new FornecedorResponseDTO();

        dto.setId(fornecedor.getId());
        dto.setNome(fornecedor.getNome());
        dto.setCnpj(fornecedor.getCnpj());
        dto.setTelefone(fornecedor.getTelefone());
        dto.setEmail(fornecedor.getEmail());
        dto.setEndereco(fornecedor.getEndereco());
        dto.setStatus(fornecedor.getStatus());

        return dto;
    }

    public Fornecedores atualizar(Long id, Fornecedores fornecedorAtualizado) {

        Fornecedores fornecedor = buscarPorId(id);

        fornecedor.setNome(fornecedorAtualizado.getNome());
        fornecedor.setCnpj(limparCnpj(fornecedorAtualizado.getCnpj()));
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedor.setEmail(fornecedorAtualizado.getEmail());
        fornecedor.setEndereco(fornecedorAtualizado.getEndereco());
        fornecedor.setStatus(fornecedorAtualizado.getStatus());

        return repository.save(fornecedor);
    }

    public void inativar(Long id) {
        Fornecedores fornecedor = buscarPorId(id);
        fornecedor.setStatus(false);
        repository.save(fornecedor);
    }

    public void reativar(Long id) {
        Fornecedores fornecedor = buscarPorId(id);
        fornecedor.setStatus(true);
        repository.save(fornecedor);
    }

    private String limparCnpj(String cnpj) {
        return cnpj.replaceAll("\\D", "");
    }
}