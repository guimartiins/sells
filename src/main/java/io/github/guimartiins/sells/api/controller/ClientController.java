package io.github.guimartiins.sells.api.controller;

import io.github.guimartiins.sells.domain.entity.Client;
import io.github.guimartiins.sells.domain.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository ClientRepository) {
        this.clientRepository = ClientRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        // functional return statement
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);

        return ResponseEntity.ok(savedClient);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Client client) {
        return clientRepository.findById(id)
                .map(clientFound -> {
                    clientFound.setName(client.getName());
                    clientRepository.save(clientFound);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
