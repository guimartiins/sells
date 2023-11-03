package io.github.guimartiins.sells.domain.repository;

import io.github.guimartiins.sells.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByNameLike(String name);
}
