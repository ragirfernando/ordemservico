package com.ordemservico.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<Campo> campos;


    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
