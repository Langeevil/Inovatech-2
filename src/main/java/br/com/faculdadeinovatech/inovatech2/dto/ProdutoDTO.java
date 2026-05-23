package br.com.faculdadeinovatech.inovatech2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer idProduto;
    private String descricaoProduto;
    private Double valorProduto;
    private String marcaProduto;
}
