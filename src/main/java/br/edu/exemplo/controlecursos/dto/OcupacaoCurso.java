package br.edu.exemplo.controlecursos.dto;

public record OcupacaoCurso(
        String titulo,
        Integer vagas,
        long matriculados,
        long disponiveis) {
}
