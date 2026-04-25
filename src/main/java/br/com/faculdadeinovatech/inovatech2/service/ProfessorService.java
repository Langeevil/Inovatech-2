package br.com.faculdadeinovatech.inovatech2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.faculdadeinovatech.inovatech2.entity.Professor;
import br.com.faculdadeinovatech.inovatech2.repository.ProfessorRepository;

@Service
public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Integer id) {
        return professorRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        professorRepository.deleteById(id);
    }
}
