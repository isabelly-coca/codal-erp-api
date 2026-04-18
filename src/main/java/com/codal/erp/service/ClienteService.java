package com.codal.erp.service;

import com.codal.erp.dto.ClienteRequestDTO;
import com.codal.erp.dto.ClienteResponseDTO;
import com.codal.erp.model.Cliente;
import com.codal.erp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codal.erp.exception.RegraNegocioException;


import java.util.List;

@Service

public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {

        Cliente cliente = new Cliente();

        String cpfLimpo = limparCpf(dto.getCpf());

        if (repository.existsByCpf(cpfLimpo)) {
            throw new RegraNegocioException("CPF já cadastrado!");
        }

        cliente.setNome(dto.getNome());
        cliente.setCpf(cpfLimpo);
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setEndereco(dto.getEndereco());
        cliente.setDataNascimento(dto.getDataNascimento());

        if (cliente.getStatus() == null) {
            cliente.setStatus(true);
        }

        Cliente salvo = repository.save(cliente);

        return toResponseDTO(salvo);
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {

        ClienteResponseDTO dto = new ClienteResponseDTO();

        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        dto.setTelefone(cliente.getTelefone());
        dto.setEmail(cliente.getEmail());
        dto.setEndereco(cliente.getEndereco());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setStatus(cliente.getStatus());

        return dto;
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findByStatusTrue()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }




    private String limparCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        return toResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContaining(nome)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        String cpfLimpo = limparCpf(dto.getCpf());

        if (!cliente.getCpf().equals(cpfLimpo) &&
                repository.existsByCpf(cpfLimpo)) {
            throw new RegraNegocioException("CPF já cadastrado!");
        }

        cliente.setNome(dto.getNome());
        cliente.setCpf(cpfLimpo);
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setEndereco(dto.getEndereco());
        cliente.setDataNascimento(dto.getDataNascimento());

        Cliente atualizado = repository.save(cliente);

        return toResponseDTO(atualizado);
    }

    public void inativar(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        cliente.setStatus(false);
        repository.save(cliente);
    }

    public void reativar(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        cliente.setStatus(true);
        repository.save(cliente);
    }
}

