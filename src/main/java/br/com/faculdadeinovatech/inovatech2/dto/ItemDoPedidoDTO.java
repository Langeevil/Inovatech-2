package br.com.faculdadeinovatech.inovatech2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDoPedidoDTO {

    private Integer idItem;
    private Integer idPedido;
    private Integer idProduto;
    private Integer quantidade;
    private Double preco;
    private Double subtotal;
}
