package io.github.guimartiins.sells;

import io.github.guimartiins.sells.domain.entity.Client;
import io.github.guimartiins.sells.domain.entity.Order;
import io.github.guimartiins.sells.domain.repository.ClientRepository;
import io.github.guimartiins.sells.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@SpringBootApplication
public class SellsApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository, @Autowired OrderRepository orderRepository) {
        return args -> {
            Client client = new Client("Guilherme");
            clientRepository.save(client);

            Order order = new Order();
            order.setClient(client);
            order.setTotal(BigDecimal.valueOf(100));

            orderRepository.save(order);

            Client clients = clientRepository.findClientFetchOrders(1);
            System.out.println(clients.getOrders().toString());



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SellsApplication.class, args);
    }

}
