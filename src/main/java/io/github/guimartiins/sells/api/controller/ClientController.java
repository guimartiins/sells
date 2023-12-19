package io.github.guimartiins.sells.api.controller;

import io.github.guimartiins.sells.domain.entity.Client;
import io.github.guimartiins.sells.domain.repository.ClientRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/clients/")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository ClientRepository) {
        this.clientRepository = ClientRepository;
    }

    @GetMapping("{id}")
    public Client getById(@PathVariable Integer id) {
        return clientRepository.findById(id).orElseThrow(() ->
         new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        // functional return statement
//        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientRepository
                .findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return Void.TYPE;
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody Client client) {
        clientRepository.findById(id)
                .map(clientFound -> {
                    client.setId(clientFound.getId());
                    clientRepository.save(client);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @GetMapping
    public List<Client> getAll(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Client> example = Example.of(filter, matcher);

        return clientRepository.findAll(example);
    }
}
