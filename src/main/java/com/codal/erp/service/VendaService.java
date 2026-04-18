package com.codal.erp.service;

import com.codal.erp.dto.*;
import com.codal.erp.model.*;
import com.codal.erp.repository.ClienteRepository;
import com.codal.erp.repository.ProdutoRepository;
import com.codal.erp.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public VendaResponseDTO realizarVenda(VendaRequestDTO dto) {

        if (dto.getItens() == null || dto.getItens().isEmpty()) {
            throw new RuntimeException("A venda deve ter pelo menos um item");
        }

        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        venda.setFormaPagamento(dto.getFormaPagamento());

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        venda.setCliente(cliente);

        List<ItemVenda> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ItemVendaDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (!produto.isStatus()) {
                throw new RuntimeException("Produto inativo: " + produto.getNome());
            }

            if (produto.getQuantidade() < itemDTO.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para: " + produto.getNome());
            }

            produto.setQuantidade(produto.getQuantidade() - itemDTO.getQuantidade());

            BigDecimal preco = produto.getValorVenda();

            ItemVenda item = new ItemVenda();
            item.setVenda(venda);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(preco);

            BigDecimal subtotal = preco.multiply(BigDecimal.valueOf(itemDTO.getQuantidade()));
            item.setSubtotal(subtotal);

            total = total.add(subtotal);
            itens.add(item);
        }

        venda.setItens(itens);
        venda.setValorTotal(total);


        BigDecimal desconto = dto.getDesconto() != null
                ? dto.getDesconto()
                : BigDecimal.ZERO;

        venda.setDesconto(desconto);

        BigDecimal valorFinal = total.subtract(desconto);
        venda.setValorFinal(valorFinal);

        Venda salva = vendaRepository.save(venda);

        return toDTO(salva);
    }

    private VendaResponseDTO toDTO(Venda venda) {
        VendaResponseDTO dto = new VendaResponseDTO();

        dto.setId(venda.getId());
        dto.setDataVenda(venda.getDataVenda());
        dto.setValorTotal(venda.getValorTotal());
        dto.setFormaPagamento(venda.getFormaPagamento());

        return dto;
    }
    public void cancelarVenda(Long id) {

        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        if (Boolean.FALSE.equals(venda.getStatus())) {
            throw new RuntimeException("Venda já está cancelada");
        }

        for (ItemVenda item : venda.getItens()) {
            Produto produto = item.getProduto();
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
        }

        venda.setStatus(false);

        vendaRepository.save(venda);
    }
}