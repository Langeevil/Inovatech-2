package br.com.faculdadeinovatech.inovatech2.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.faculdadeinovatech.inovatech2.entity.Aluno;
import br.com.faculdadeinovatech.inovatech2.entity.Pedido;
import br.com.faculdadeinovatech.inovatech2.entity.Produto;
import br.com.faculdadeinovatech.inovatech2.service.AlunoService;
import br.com.faculdadeinovatech.inovatech2.service.PedidoService;
import br.com.faculdadeinovatech.inovatech2.service.ProdutoService;


@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pedido pedido) {
        pedidoService.save(pedido);
        return "redirect:/pedidos/listar";        
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Pedido> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedido/listaPedidos";
    }

    @GetMapping("/criar")
    public String criarForm(Model model) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        model.addAttribute("pedido", pedido);

        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);

        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);

        return "pedido/formularioPedido";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Pedido pedido = pedidoService.findById(id);
        model.addAttribute("pedido", pedido);

        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);

        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);

        return "pedido/formularioPedido";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        pedidoService.deleteById(id);
        return "redirect:/pedidos/listar";
    }
    
}
