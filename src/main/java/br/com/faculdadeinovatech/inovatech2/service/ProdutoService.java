package br.com.faculdadeinovatech.inovatech2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.faculdadeinovatech.inovatech2.entity.Produto;
import br.com.faculdadeinovatech.inovatech2.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Integer id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        produtoRepository.deleteById(id);
    }
}
