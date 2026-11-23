package br.edu.exemplo.controlecursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
    @Column(length = 1000)
    private String descricao;

    @NotBlank(message = "O instrutor é obrigatório")
    private String instrutor;

    @NotNull(message = "A carga horária é obrigatória")
    @Min(value = 1, message = "A carga horária deve ser de pelo menos 1 hora")
    private Integer cargaHoraria;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "O horário é obrigatório")
    private LocalTime horario;

    @NotBlank(message = "O local é obrigatório")
    private String local;

    @NotNull(message = "A quantidade de vagas é obrigatória")
    @Min(value = 1, message = "O curso deve possuir pelo menos uma vaga")
    private Integer quantidadeVagas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCurso status = StatusCurso.ABERTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(Integer quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }

    public StatusCurso getStatus() {
        return status;
    }

    public void setStatus(StatusCurso status) {
        this.status = status;
    }
}
