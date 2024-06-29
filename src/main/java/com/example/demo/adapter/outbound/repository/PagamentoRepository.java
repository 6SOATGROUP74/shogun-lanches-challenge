package com.example.demo.adapter.outbound.repository;

import com.example.demo.adapter.outbound.repository.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
}
