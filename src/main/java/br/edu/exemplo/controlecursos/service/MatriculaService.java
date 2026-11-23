package br.edu.exemplo.controlecursos.service;

import br.edu.exemplo.controlecursos.model.*;
import br.edu.exemplo.controlecursos.repository.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final CursoRepository cursoRepository;
    private final AlunoRepository alunoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository,
            CursoRepository cursoRepository,
            AlunoRepository alunoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.cursoRepository = cursoRepository;
        this.alunoRepository = alunoRepository;
    }

    // Regras de negócio da matrícula ficam concentradas aqui no Service
    public Matricula salvar(Matricula matricula) {
        if (matricula.getCurso() == null || matricula.getCurso().getId() == null) {
            throw new IllegalStateException("Selecione um curso.");
        }
        if (matricula.getAluno() == null || matricula.getAluno().getId() == null) {
            throw new IllegalStateException("Selecione um aluno.");
        }

        Curso curso = cursoRepository.findById(matricula.getCurso().getId())
                .orElseThrow(() -> new IllegalStateException("Curso não encontrado."));
        Aluno aluno = alunoRepository.findById(matricula.getAluno().getId())
                .orElseThrow(() -> new IllegalStateException("Aluno não encontrado."));

        // Regra 1: o curso precisa estar aberto
        if (curso.getStatus() != StatusCurso.ABERTO) {
            throw new IllegalStateException("Este curso não está aberto para matrículas.");
        }

        // Regra 2: o aluno não pode se matricular duas vezes no mesmo curso
        boolean jaMatriculado = matriculaRepository
                .existsByCursoIdAndAlunoId(curso.getId(), aluno.getId());
        if (jaMatriculado) {
            throw new IllegalStateException("Este aluno já está matriculado neste curso.");
        }

        // Regra 3: precisa haver vagas disponíveis
        long totalMatriculados = matriculaRepository.countByCursoId(curso.getId());
        if (totalMatriculados >= curso.getQuantidadeVagas()) {
            throw new IllegalStateException("Não há vagas disponíveis para este curso.");
        }

        matricula.setCurso(curso);
        matricula.setAluno(aluno);
        matricula.setDataMatricula(LocalDateTime.now());
        return matriculaRepository.save(matricula);
    }

    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    public List<Matricula> listarPorCurso(Long cursoId) {
        return matriculaRepository.findByCursoId(cursoId);
    }

    public long contarPorCurso(Long cursoId) {
        return matriculaRepository.countByCursoId(cursoId);
    }

    public void excluir(Long id) {
        matriculaRepository.deleteById(id);
    }
}
