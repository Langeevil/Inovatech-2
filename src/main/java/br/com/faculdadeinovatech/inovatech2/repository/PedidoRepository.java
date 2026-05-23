package br.com.faculdadeinovatech.inovatech2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadeinovatech.inovatech2.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @EntityGraph(attributePaths = {"aluno", "itens", "itens.produto"})
    Optional<Pedido> findById(Integer id);

}
