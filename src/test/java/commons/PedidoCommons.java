package commons;

import com.example.demo.core.domain.Cliente;
import com.example.demo.core.domain.Pedido;

import java.util.Random;

public class PedidoCommons {

    private static Random random = new Random();

    public static Pedido buildPedido(){

        Pedido pedido = new Pedido();
        pedido.setNumeroPedido((long) random.nextInt(10000));
        pedido.setCliente(generateRandomCliente());
        pedido.setValorTotal(random.nextDouble() * 500); // Valor total entre 0 e 500
        pedido.setComposicao(Arrays.asList(generateRandomComposicao(), generateRandomComposicao()));
        pedido.setEtapa(generateRandomEtapa());
        pedido.setIdPagamento((long) random.nextInt(10000));
        pedido.setDataPedido(generateRandomDate());
        pedido.setCodPedido(generateRandomCodPedido());
        pedido.setCodReferenciaPedido(generateRandomCodReferencia());
        pedido.setDataMudancaEtapa(generateRandomDate());

    }

    private static Cliente generateRandomCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente " + random.nextInt(100));
        cliente.setId((long) random.nextInt(1000));
        return cliente;
    }

}
