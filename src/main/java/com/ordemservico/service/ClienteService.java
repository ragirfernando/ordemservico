package com.ordemservico.service;

import com.ordemservico.domain.Cliente;
import com.ordemservico.exceptionhandler.NegocioException;
import com.ordemservico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente adicionar (Cliente cliente){
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteExistente != null && !clienteExistente.equals(cliente)){
            throw new NegocioException("Já existe um cliente cadastrado com este E-mail.");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir (Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
