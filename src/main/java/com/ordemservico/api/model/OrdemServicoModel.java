package com.ordemservico.api.model;


import com.ordemservico.domain.StatusOrdemServico;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class OrdemServicoModel {

    private Long id;

    private ClienteResumoModel cliente;

    private String descricao;

    private StatusOrdemServico status;

    private BigDecimal preco;

    private OffsetDateTime dataAbertura;

    private OffsetDateTime dataFinalizacao;

    public ClienteResumoModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumoModel cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
}
