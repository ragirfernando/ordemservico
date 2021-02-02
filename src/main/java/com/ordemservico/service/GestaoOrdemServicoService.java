package com.ordemservico.service;

import com.ordemservico.api.exceptionhandler.EntidadeNaoEncontradaException;
import com.ordemservico.domain.Cliente;
import com.ordemservico.domain.Comentario;
import com.ordemservico.domain.OrdemServico;
import com.ordemservico.domain.StatusOrdemServico;
import com.ordemservico.api.exceptionhandler.NegocioException;
import com.ordemservico.repository.ClienteRepository;
import com.ordemservico.repository.ComentarioRepository;
import com.ordemservico.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;

    private ClienteRepository clienteRepository;

    private ComentarioRepository comentarioRepository;

    @Autowired
    public GestaoOrdemServicoService(OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository, ComentarioRepository comentarioRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.clienteRepository = clienteRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscar(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }


    public void finalizar(Long ordemServicoId) {
        OrdemServico ordemServico = buscar(ordemServicoId);
        ordemServico.finalizar();
        ordemServicoRepository.save(ordemServico);
    }

    private OrdemServico buscar(Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrado"));
        return ordemServico;
    }


}
