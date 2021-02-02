package com.ordemservico.api.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ClienteIdInput implements Serializable {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
