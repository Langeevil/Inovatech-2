package br.com.faculdadeinovatech.inovatech2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.faculdadeinovatech.inovatech2.entity.ItemDoPedido;
import br.com.faculdadeinovatech.inovatech2.entity.Pedido;
import br.com.faculdadeinovatech.inovatech2.entity.Produto;
import br.com.faculdadeinovatech.inovatech2.repository.PedidoRepository;
import br.com.faculdadeinovatech.inovatech2.repository.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido save(Pedido pedido) {
        if (pedido.getDataPedido() == null) {
            pedido.setDataPedido(LocalDate.now());
        }

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve ter pelo menos um item");
        }

        for (ItemDoPedido item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            item.setProduto(produto);
            item.setPreco(produto.getValorProduto());
            item.atualizarSubtotal();
            item.setPedido(pedido);
        }
        pedido.atualizarTotal();
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
