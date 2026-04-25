package br.com.faculdadeinovatech.inovatech2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadeinovatech.inovatech2.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
