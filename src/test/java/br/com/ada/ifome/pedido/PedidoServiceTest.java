package br.com.ada.ifome.pedido;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    private final Double VALOR_FRETE = 15d;
    private Pedido pedido = new Pedido(1L, "Hugo", 15, 85.55, "SP");
    @InjectMocks
    private PedidoService pedidoService;


    @Test
    public void salvarPedidoCobrandoFrete() {
        var pedidoSalvo = pedidoService.salvarPedido(pedido);
        assertEquals(pedidoSalvo.getValorTotal(), 100.55);
    }

    @Test
    public void salvarPedidoComDesconto20Porcentagem() {
        var pedido = this.pedido;
        pedido.setValorTotal(520.00d);
        var pedidoSalvo = pedidoService.salvarPedido(pedido);
        assertEquals(pedidoSalvo.getValorTotal(), 428d);
    }

    @Test
    public void salvarPedidoDFComFreteGratis() {
        var pedido = this.pedido;
        pedido.setEstado("DF");
        pedido.setValorTotal(95d);
        // Para pedidos do DF E com o valor total acima de R$ 100,00
        // não será cobrado frete.
        var pedidoSalvo = pedidoService.salvarPedido(pedido);
        assertEquals(95d + VALOR_FRETE, pedidoSalvo.getValorTotal());
    }
}
