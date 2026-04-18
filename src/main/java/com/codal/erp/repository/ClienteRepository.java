package com.codal.erp.repository;

import com.codal.erp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    boolean existsByCpf(String cpf);
    Cliente findByCpf(String cpf);
    List <Cliente> findByNomeContaining(String nome);
    List<Cliente> findByStatusTrue();
}
