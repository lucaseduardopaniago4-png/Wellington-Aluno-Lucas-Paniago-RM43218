package br.edu.exemplo.controlecursos.service;

import br.edu.exemplo.controlecursos.model.Aluno;
import br.edu.exemplo.controlecursos.repository.AlunoRepository;
import br.edu.exemplo.controlecursos.repository.MatriculaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;
    private final MatriculaRepository matriculaRepository;

    public AlunoService(AlunoRepository repository, MatriculaRepository matriculaRepository) {
        this.repository = repository;
        this.matriculaRepository = matriculaRepository;
    }

    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        if (matriculaRepository.countByAlunoId(id) > 0) {
            throw new IllegalStateException(
                    "Não é possível excluir um aluno que possui matrículas."
            );
        }
        repository.deleteById(id);
    }
}
