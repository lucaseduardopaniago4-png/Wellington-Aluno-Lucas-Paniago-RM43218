package br.edu.exemplo.controlecursos.service;

import br.edu.exemplo.controlecursos.model.Curso;
import br.edu.exemplo.controlecursos.repository.CursoRepository;
import br.edu.exemplo.controlecursos.repository.MatriculaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private final CursoRepository repository;
    private final MatriculaRepository matriculaRepository;

    public CursoService(CursoRepository repository, MatriculaRepository matriculaRepository) {
        this.repository = repository;
        this.matriculaRepository = matriculaRepository;
    }

    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        if (matriculaRepository.countByCursoId(id) > 0) {
            throw new IllegalStateException(
                    "Não é possível excluir um curso que possui alunos matriculados."
            );
        }
        repository.deleteById(id);
    }
}
