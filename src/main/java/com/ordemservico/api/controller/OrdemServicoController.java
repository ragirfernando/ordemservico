package com.ordemservico.api.controller;

import com.ordemservico.api.model.OrdemServicoInput;
import com.ordemservico.api.model.OrdemServicoModel;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    private OrdemServicoRepository ordemServicoRepository;

    private ModelMapper modelMapper;

    @Autowired
    public OrdemServicoController(GestaoOrdemServicoService gestaoOrdemServicoService, OrdemServicoRepository ordemServicoRepository, ModelMapper modelMapper) {
        this.gestaoOrdemServicoService = gestaoOrdemServicoService;
        this.ordemServicoRepository = ordemServicoRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrdemServicoModel> criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
        OrdemServico ordemServico = toEntity(ordemServicoInput);
        return ResponseEntity.ok(toModel(gestaoOrdemServicoService.criar(ordemServico)));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoModel>> listar() {
        return ResponseEntity.ok(toCollectionModel(ordemServicoRepository.findAll()));
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServicoModel> listarId(@PathVariable Long ordemServicoId) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);

        if (ordemServico.isPresent()){
            OrdemServicoModel model = toModel(ordemServico.get());
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.notFound().build();
    }

    private OrdemServicoModel toModel(OrdemServico ordemServico){
        return modelMapper.map(ordemServico, OrdemServicoModel.class);
    }

    private OrdemServico toEntity(OrdemServicoInput ordemServicoInput){
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
    }

    private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordemServico){
        return ordemServico.stream()
                .map(ordemServico1 -> toModel(ordemServico1))
                .collect(Collectors.toList());
    }
}
