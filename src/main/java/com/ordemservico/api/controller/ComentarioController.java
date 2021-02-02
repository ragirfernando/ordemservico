package com.ordemservico.api.controller;

import com.ordemservico.api.exceptionhandler.EntidadeNaoEncontradaException;
import com.ordemservico.api.model.ComentarioInput;
import com.ordemservico.api.model.ComentarioModel;
import com.ordemservico.domain.Comentario;
import com.ordemservico.domain.OrdemServico;
import com.ordemservico.repository.OrdemServicoRepository;
import com.ordemservico.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    private OrdemServicoRepository ordemServicoRepository;

    private ModelMapper modelMapper;

    @Autowired
    public ComentarioController(GestaoOrdemServicoService gestaoOrdemServicoService, OrdemServicoRepository ordemServicoRepository, ModelMapper modelMapper) {
        this.gestaoOrdemServicoService = gestaoOrdemServicoService;
        this.ordemServicoRepository = ordemServicoRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ComentarioModel> adicionar(@Valid @PathVariable Long ordemServicoId, @RequestBody ComentarioInput comentarioInput) {
        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

        return ResponseEntity.ok().body(toModel(comentario));
    }

    @GetMapping
    public ResponseEntity<List<ComentarioModel>> listarComentario(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

        return ResponseEntity.ok().body(toCollectionModel(ordemServico.getComentarios()));

    }

    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }


    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }
}
