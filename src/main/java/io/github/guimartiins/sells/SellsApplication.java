package io.github.guimartiins.sells;

import io.github.guimartiins.sells.domain.entity.Client;
import io.github.guimartiins.sells.domain.repository.ClientRepository;
import io.github.guimartiins.sells.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SellsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellsApplication.class, args);
    }

}
