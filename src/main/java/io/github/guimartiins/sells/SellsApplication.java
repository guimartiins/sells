package io.github.guimartiins.sells;

import io.github.guimartiins.sells.domain.entity.Client;
import io.github.guimartiins.sells.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class SellsApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository) {
        return args -> {
            Client client = new Client("Guilherme");
            clientRepository.save(client);
            Client client2 = new Client("João");
            clientRepository.save(client2);
            client.setName("Guilherme Martins");

            List<Client> clients = clientRepository.findAll();
            clients.forEach(c -> {
                c.setName(c.getName() + " updated");
                clientRepository.save(c);
            });

            Client clientToDelete = clients.get(0);
            clientRepository.delete(clientToDelete);

            clientRepository.findAll().forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SellsApplication.class, args);
    }

}
