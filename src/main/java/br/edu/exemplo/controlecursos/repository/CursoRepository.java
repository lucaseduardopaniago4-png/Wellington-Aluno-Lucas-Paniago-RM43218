package br.edu.exemplo.controlecursos.repository;

import br.edu.exemplo.controlecursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
