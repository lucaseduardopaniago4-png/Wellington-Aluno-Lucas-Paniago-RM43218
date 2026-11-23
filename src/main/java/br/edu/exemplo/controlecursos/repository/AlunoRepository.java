package br.edu.exemplo.controlecursos.repository;

import br.edu.exemplo.controlecursos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
