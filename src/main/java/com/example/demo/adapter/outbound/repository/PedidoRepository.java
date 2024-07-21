package com.example.demo.adapter.outbound.repository;


import com.example.demo.adapter.outbound.repository.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query(value = "SELECT p FROM PedidoEntity p WHERE p.etapa != 'FINALIZADO' ORDER BY CASE WHEN p.etapa = 'PRONTO' THEN 1 WHEN p.etapa = 'EM_PREPARACAO' THEN 2 WHEN p.etapa = 'RECEBIDO' THEN 3 ELSE 4 END, p.dataPedido ASC")
    List<PedidoEntity> ordenaPedidos();
}
