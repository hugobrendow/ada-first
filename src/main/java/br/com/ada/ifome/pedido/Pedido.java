package br.com.ada.ifome.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Long id;
    private String nomeCliente;
    private Integer quantidadeProdutos;
    private Double valorTotal;
    private String estado;
}
