package br.edu.exemplo.controlecursos.repository;

import br.edu.exemplo.controlecursos.model.Matricula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Quantos alunos estão matriculados em um curso
    long countByCursoId(Long cursoId);

    // Quantas matrículas um aluno possui
    long countByAlunoId(Long alunoId);

    // O aluno já está matriculado neste curso?
    boolean existsByCursoIdAndAlunoId(Long cursoId, Long alunoId);

    // Todas as matrículas de um curso
    List<Matricula> findByCursoId(Long cursoId);
}
