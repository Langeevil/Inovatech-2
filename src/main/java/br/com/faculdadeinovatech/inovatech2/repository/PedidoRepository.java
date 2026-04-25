package br.com.faculdadeinovatech.inovatech2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadeinovatech.inovatech2.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
