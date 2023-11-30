package br.com.ada.ifome.pedido;

import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final Double DESCONTO_ACIMA_500 = 0.20;

    public Pedido salvarPedido(Pedido pedido) {
        pedido = this.adicionarFrete(pedido);
        pedido = this.aplicaDesconto(pedido);
        return pedido;
    }

    public Pedido adicionarFrete(Pedido pedido) {
        if (pedido.getEstado().equals("DF") && pedido.getValorTotal() >= 100) {
            return pedido;
        }
        pedido.setValorTotal(pedido.getValorTotal() + 15d);
        return pedido;
    }

    public Pedido aplicaDesconto(Pedido pedido) {
        if (pedido.getValorTotal() > 500) {
            double desconto = pedido.getValorTotal() * DESCONTO_ACIMA_500;
            pedido.setValorTotal(pedido.getValorTotal() - desconto);
        }
        return pedido;
    }
}
