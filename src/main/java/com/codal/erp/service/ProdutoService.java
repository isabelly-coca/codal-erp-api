package com.codal.erp.service;

import com.codal.erp.dto.ProdutoRequestDTO;
import com.codal.erp.dto.ProdutoResponseDTO;
import com.codal.erp.model.Categoria;
import com.codal.erp.model.Produto;
import com.codal.erp.repository.CategoriaRepository;
import com.codal.erp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {

        if (repository.existsByEan(dto.getEan())) {
            throw new RuntimeException("Já existe um produto com esse EAN");
        }

        if (dto.getValorVenda().compareTo(dto.getValorCusto()) < 0) {
            throw new RuntimeException("Valor de venda não pode ser menor que o custo");
        }

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setEan(dto.getEan());
        produto.setNcm(dto.getNcm());
        produto.setFoto(dto.getFoto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setValorCusto(dto.getValorCusto());
        produto.setValorVenda(dto.getValorVenda());
        produto.setStatus(true);

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setCategoria(categoria);

        Produto salvo = repository.save(produto);



        return toDTO(salvo);
    }


    public List<ProdutoResponseDTO> listar() {
        return repository.findByStatusTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return toDTO(produto);
    }
    public List<ProdutoResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toDTO)
                .toList();
    }


    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (dto.getValorVenda().compareTo(dto.getValorCusto()) < 0) {
            throw new RuntimeException("Valor de venda não pode ser menor que o custo");
        }

        produto.setNome(dto.getNome());
        produto.setEan(dto.getEan());
        produto.setNcm(dto.getNcm());
        produto.setFoto(dto.getFoto());
        produto.setQuantidade(dto.getQuantidade());
        produto.setValorCusto(dto.getValorCusto());
        produto.setValorVenda(dto.getValorVenda());

        Produto atualizado = repository.save(produto);

        return toDTO(atualizado);
    }

    public void inativar(Long id) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setStatus(false);

        repository.save(produto);
    }
    public void reativar(Long id) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setStatus(true);

        repository.save(produto);
    }


    private ProdutoResponseDTO toDTO(Produto produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();

        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setEan(produto.getEan());
        dto.setNcm(produto.getNcm());
        dto.setFoto(produto.getFoto());
        dto.setQuantidade(produto.getQuantidade());
        dto.setValorCusto(produto.getValorCusto());
        dto.setValorVenda(produto.getValorVenda());
        dto.setStatus(produto.isStatus());

        return dto;
    }
}
