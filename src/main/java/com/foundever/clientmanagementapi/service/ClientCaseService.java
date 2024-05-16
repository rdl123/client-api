package com.foundever.clientmanagementapi.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundever.clientmanagementapi.model.ClientCase;
import com.foundever.clientmanagementapi.repository.ClientCaseRepository;

@Service
@Transactional
public class ClientCaseService {

    private final ClientCaseRepository clientCaseRepository;

    @Autowired
    public ClientCaseService(ClientCaseRepository clientCaseRepository) {
        this.clientCaseRepository = clientCaseRepository;
    }

    public List<ClientCase> getAllClientCases() {
        return clientCaseRepository.findAll();
    }

    public Optional<ClientCase> getClientCaseById(Long id) {
        return clientCaseRepository.findById(id);
    }

    public ClientCase saveClientCase(ClientCase clientCase) {
        return clientCaseRepository.save(clientCase);
    }

    public void deleteClientCase(Long id) {
        clientCaseRepository.deleteById(id);
    }
}