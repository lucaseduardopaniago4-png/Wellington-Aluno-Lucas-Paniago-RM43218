package br.edu.exemplo.controlecursos.controller;

import br.edu.exemplo.controlecursos.model.Aluno;
import br.edu.exemplo.controlecursos.model.Curso;
import br.edu.exemplo.controlecursos.model.Matricula;
import br.edu.exemplo.controlecursos.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final CursoService cursoService;
    private final AlunoService alunoService;

    public MatriculaController(MatriculaService matriculaService,
            CursoService cursoService,
            AlunoService alunoService) {
        this.matriculaService = matriculaService;
        this.cursoService = cursoService;
        this.alunoService = alunoService;
    }

    @GetMapping("/matriculas")
    public String listar(Model model) {
        model.addAttribute("matriculas", matriculaService.listarTodas());
        return "lista-matriculas";
    }

    @GetMapping("/matriculas/nova")
    public String nova(@RequestParam(required = false) Long cursoId, Model model) {
        Matricula matricula = new Matricula();
        matricula.setCurso(new Curso());
        matricula.setAluno(new Aluno());
        // Quando vem da tela "Matriculados" o curso já chega selecionado
        matricula.getCurso().setId(cursoId);
        model.addAttribute("matricula", matricula);
        carregarListas(model);
        return "form-matricula";
    }

    @PostMapping("/matriculas/salvar")
    public String salvar(Matricula matricula, Model model) {
        try {
            matriculaService.salvar(matricula);
            return "redirect:/matriculas";
        } catch (IllegalStateException e) {
            model.addAttribute("erro", e.getMessage());
            carregarListas(model);
            return "form-matricula";
        }
    }

    private void carregarListas(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        model.addAttribute("alunos", alunoService.listarTodos());
    }

    @GetMapping("/matriculas/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        matriculaService.excluir(id);
        return "redirect:/matriculas";
    }

    @GetMapping("/cursos/{id}/matriculados")
    public String matriculadosDoCurso(@PathVariable Long id, Model model) {
        Curso curso = cursoService.buscarPorId(id);
        if (curso == null) {
            return "redirect:/cursos";
        }
        var matriculas = matriculaService.listarPorCurso(id);
        long total = matriculaService.contarPorCurso(id);
        model.addAttribute("curso", curso);
        model.addAttribute("matriculas", matriculas);
        model.addAttribute("totalMatriculados", total);
        return "matriculados-curso";
    }
}
