package com.foundever.clientmanagementapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foundever.clientmanagementapi.dto.ClientCaseRequestDTO;
import com.foundever.clientmanagementapi.model.ClientCase;
import com.foundever.clientmanagementapi.model.Message;
import com.foundever.clientmanagementapi.service.ClientCaseService;
import com.foundever.clientmanagementapi.service.MessageService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class ClientCaseController {

    private final ClientCaseService clientCaseService;
    private final MessageService messageService;

    @Autowired
    public ClientCaseController(ClientCaseService clientCaseService, MessageService messageService) {
        this.clientCaseService = clientCaseService;
        this.messageService = messageService;
    }

    @PostMapping("/messages/add")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.saveMessage(message);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    @PostMapping("/client-cases")
    public ResponseEntity<ClientCase> createClientCase(@RequestBody  ClientCaseRequestDTO clientCaseRequestDTO) {
        Long messageId = clientCaseRequestDTO.getMessageId();
        Message message = messageService.getMessageById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found with id: " + messageId));

        ClientCase createdClientCase = ClientCase.builder()
                .clientName(clientCaseRequestDTO.getClientName())
                .messages(List.of(message)) 
                .build();
        clientCaseService.saveClientCase(createdClientCase);
        message.setClientCase(createdClientCase);
        messageService.saveMessage(message);

        return new ResponseEntity<>(createdClientCase, HttpStatus.CREATED);
    }

    @PostMapping("/client-cases/{clientId}/messages")
    public ResponseEntity<Message> createMessageForClientCase(@PathVariable Long clientId, @RequestBody Message message) {
        ClientCase clientCase = clientCaseService.getClientCaseById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client case not found with id: " + clientId));
        message.setClientCase(clientCase);
        Message createdMessage = messageService.saveMessage(message);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    @PutMapping("/client-cases/{clientId}")
    public ResponseEntity<ClientCase> updateClientCase(@PathVariable Long clientId, @RequestBody ClientCase clientCase) {
        ClientCase existingClientCase = clientCaseService.getClientCaseById(clientId)
                .orElseThrow(() -> new RuntimeException("Client case not found with id: " + clientId));
        existingClientCase.setClientReference(clientCase.getClientReference());
        ClientCase updatedClientCase = clientCaseService.saveClientCase(existingClientCase);

        return new ResponseEntity<>(updatedClientCase, HttpStatus.OK);
    }

    @GetMapping("/client-cases")
    public ResponseEntity<List<ClientCase>> getAllClientCases() {
        List<ClientCase> clientCases = clientCaseService.getAllClientCases();
        return new ResponseEntity<>(clientCases, HttpStatus.OK);
    }
}