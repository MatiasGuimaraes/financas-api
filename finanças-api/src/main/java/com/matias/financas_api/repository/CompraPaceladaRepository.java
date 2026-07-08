package com.matias.financas_api.repository;

import com.matias.financas_api.model.CompraParcelada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraPaceladaRepository extends JpaRepository<CompraParcelada, Integer> {

}