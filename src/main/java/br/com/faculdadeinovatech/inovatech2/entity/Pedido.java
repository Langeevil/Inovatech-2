package br.com.faculdadeinovatech.inovatech2.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPedido;

    @Column(nullable = false)
    private LocalDate dataPedido;

    @Column(nullable = false)
    private Double totalPedido;

    @ManyToOne
    @JoinColumn(name = "idAluno_fk")
    private Aluno aluno;

    // Relacionamento com os itens
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDoPedido> itens;

    public double calcularTotal() {
        double total = 0.0;
        if (itens != null) {
            for (ItemDoPedido item : itens) {
                total += item.getSubtotal();
            }
        }
        return total;
    }

    //Médoto para atualizar o total
    public void atualizarTotal() {
        this.totalPedido = calcularTotal();
    }
}
