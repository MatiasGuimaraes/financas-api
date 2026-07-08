package com.matias.financas_api.repository;

import com.matias.financas_api.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

    // o spring cria a query automaticamente apenas por ler esse nome do metodo
    Iterable<Lancamento> findByUsuarioId(UUID usuarioId);
}

