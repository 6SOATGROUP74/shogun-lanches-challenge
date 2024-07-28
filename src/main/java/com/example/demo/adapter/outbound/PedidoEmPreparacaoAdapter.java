package com.example.demo.adapter.outbound;

import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.adapter.outbound.repository.entity.PedidoEntity;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.outbound.pedido.PedidoEmPreparacaoAdapterPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.demo.core.domain.StatusPedido.EM_PREPARACAO;

@AllArgsConstructor
@Component
public class PedidoEmPreparacaoAdapter implements PedidoEmPreparacaoAdapterPort {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido execute(Long idPedido) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(idPedido).orElseThrow();
        pedidoEntity.setEtapa(EM_PREPARACAO.name());
        PedidoEntity pedidoAlterado = pedidoRepository.save(pedidoEntity);
        return PedidoEntityMapper.INSTANCE.mapFrom(pedidoAlterado);
    }
}
