package com.codal.erp.repository;

import com.codal.erp.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByEan(String ean);


    List<Produto> findByStatusTrue();
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
