package br.com.faculdadeinovatech.inovatech2.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Integer idPedido;
    private LocalDate dataPedido;
    private Double totalPedido;
    private Integer idAluno;
}
