package br.com.faculdadeinovatech.inovatech2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faculdadeinovatech.inovatech2.entity.Disciplina;
import br.com.faculdadeinovatech.inovatech2.service.CursoService;
import br.com.faculdadeinovatech.inovatech2.service.DisciplinaService;
import br.com.faculdadeinovatech.inovatech2.service.ProfessorService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Disciplina disciplina) {
        disciplinaService.save(disciplina);
        return "redirect:/disciplinas/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
         List<Disciplina> disciplinas = disciplinaService.findAll();
         model.addAttribute("disciplinas", disciplinas);
         return "disciplina/listaDisciplinas";
    }
    
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        model.addAttribute("professores", professorService.findAll());
        model.addAttribute("cursos", cursoService.findAll());
        return "disciplina/formularioDisciplina";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Disciplina disciplina = disciplinaService.findById(id);
        model.addAttribute("disciplina", disciplina);
        return "disciplina/formularioDisciplina";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        disciplinaService.deleteById(id);
        return "redirect:/disciplinas/listar";
    }
    
    
    
}
