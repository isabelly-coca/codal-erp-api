package com.codal.erp.service;

import com.codal.erp.dto.DevolucaoTrocaRequestDTO;
import com.codal.erp.model.*;
import com.codal.erp.repository.DevolucaoTrocaRepository;
import com.codal.erp.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

@Service
public class DevolucaoTrocaService {

    @Autowired
    private DevolucaoTrocaRepository repository;

    @Autowired
    private VendaRepository vendaRepository;

    public void processar(DevolucaoTrocaRequestDTO dto) {

        Venda venda = vendaRepository.findById(dto.getVendaId())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        if (dto.getTipo().equals("DEVOLUCAO")) {

            for (ItemVenda item : venda.getItens()) {
                Produto produto = item.getProduto();
                produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            }

            venda.setStatus(false);
        }

        if (dto.getTipo().equals("TROCA")) {

            for (ItemVenda item : venda.getItens()) {
                Produto produto = item.getProduto();
                produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            }

            venda.setStatus(false);

            Venda novaVenda = new Venda();
            novaVenda.setDataVenda(LocalDateTime.now());
            novaVenda.setFormaPagamento(venda.getFormaPagamento());
            novaVenda.setCliente(venda.getCliente());

            List<ItemVenda> novosItens = new ArrayList<>();
            BigDecimal total = BigDecimal.ZERO;

            for (ItemVenda itemAntigo : venda.getItens()) {

                Produto produto = itemAntigo.getProduto();

                produto.setQuantidade(produto.getQuantidade() - itemAntigo.getQuantidade());

                ItemVenda novoItem = new ItemVenda();
                novoItem.setVenda(novaVenda);
                novoItem.setProduto(produto);
                novoItem.setQuantidade(itemAntigo.getQuantidade());
                novoItem.setPrecoUnitario(itemAntigo.getPrecoUnitario());

                BigDecimal subtotal = itemAntigo.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(itemAntigo.getQuantidade()));

                novoItem.setSubtotal(subtotal);

                total = total.add(subtotal);
                novosItens.add(novoItem);
            }

            novaVenda.setItens(novosItens);
            novaVenda.setValorTotal(total);

            vendaRepository.save(novaVenda);
        }

        DevolucaoTroca mov = new DevolucaoTroca();
        mov.setVenda(venda);
        mov.setTipo(dto.getTipo());
        mov.setMotivo(dto.getMotivo());
        mov.setData(LocalDateTime.now());

        repository.save(mov);
    }
}