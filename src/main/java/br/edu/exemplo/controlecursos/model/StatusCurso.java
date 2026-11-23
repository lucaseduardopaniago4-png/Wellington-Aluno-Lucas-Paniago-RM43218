package br.edu.exemplo.controlecursos.model;

public enum StatusCurso {
    ABERTO("Aberto"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String rotulo;

    StatusCurso(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}
