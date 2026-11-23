package br.edu.exemplo.controlecursos.controller;

import br.edu.exemplo.controlecursos.model.Aluno;
import br.edu.exemplo.controlecursos.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/alunos")
    public String listar(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        return "lista-alunos";
    }

    @GetMapping("/alunos/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "form-aluno";
    }

    @PostMapping("/alunos/salvar")
    public String salvar(@Valid Aluno aluno, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "form-aluno";
        }
        alunoService.salvar(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Aluno aluno = alunoService.buscarPorId(id);
        if (aluno == null) {
            return "redirect:/alunos";
        }
        model.addAttribute("aluno", aluno);
        return "form-aluno";
    }

    @GetMapping("/alunos/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            alunoService.excluir(id);
        } catch (IllegalStateException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/alunos";
    }
}
