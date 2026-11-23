package br.edu.exemplo.controlecursos.controller;

import br.edu.exemplo.controlecursos.model.Curso;
import br.edu.exemplo.controlecursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "lista-cursos";
    }

    @GetMapping("/cursos/novo")
    public String novoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "form-curso";
    }

    @PostMapping("/cursos/salvar")
    public String salvarCurso(@Valid Curso curso, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "form-curso";
        }
        cursoService.salvar(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/cursos/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        Curso curso = cursoService.buscarPorId(id);
        if (curso == null) {
            return "redirect:/cursos";
        }
        model.addAttribute("curso", curso);
        return "form-curso";
    }

    @GetMapping("/cursos/excluir/{id}")
    public String excluirCurso(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            cursoService.excluir(id);
        } catch (IllegalStateException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/cursos";
    }
}
