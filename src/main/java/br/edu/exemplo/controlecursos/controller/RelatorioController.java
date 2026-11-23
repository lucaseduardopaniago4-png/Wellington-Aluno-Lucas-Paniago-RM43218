package br.edu.exemplo.controlecursos.controller;

import br.edu.exemplo.controlecursos.dto.OcupacaoCurso;
import br.edu.exemplo.controlecursos.service.AlunoService;
import br.edu.exemplo.controlecursos.service.CursoService;
import br.edu.exemplo.controlecursos.service.MatriculaService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioController {

    private final CursoService cursoService;
    private final AlunoService alunoService;
    private final MatriculaService matriculaService;

    public RelatorioController(CursoService cursoService,
            AlunoService alunoService,
            MatriculaService matriculaService) {
        this.cursoService = cursoService;
        this.alunoService = alunoService;
        this.matriculaService = matriculaService;
    }

    @GetMapping("/relatorios")
    public String relatorioGeral(Model model) {
        var cursos = cursoService.listarTodos();

        List<OcupacaoCurso> ocupacoes = cursos.stream()
                .map(curso -> {
                    long matriculados = matriculaService.contarPorCurso(curso.getId());
                    long disponiveis = curso.getQuantidadeVagas() - matriculados;

                    return new OcupacaoCurso(
                            curso.getTitulo(),
                            curso.getQuantidadeVagas(),
                            matriculados,
                            disponiveis
                    );
                })
                .toList();

        model.addAttribute("totalCursos", cursos.size());
        model.addAttribute("totalAlunos", alunoService.listarTodos().size());
        model.addAttribute("totalMatriculas", matriculaService.listarTodas().size());
        model.addAttribute("ocupacoes", ocupacoes);
        return "relatorio-geral";
    }
}
