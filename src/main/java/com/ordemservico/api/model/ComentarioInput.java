package com.ordemservico.api.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ComentarioInput implements Serializable {

    @NotBlank
    private String descricao;



    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
