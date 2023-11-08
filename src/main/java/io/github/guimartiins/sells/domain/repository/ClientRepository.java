package io.github.guimartiins.sells.domain.repository;

import io.github.guimartiins.sells.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByNameLike(String name);

    @Query(value = "select c from Client c where c.name like '%:name%'", nativeQuery = true)
    List<Client> findByNameUsingQuery(String name);

    @Query("select c from Client c left join fetch c.orders where c.id = :id")
    Client findClientFetchOrders(Integer id);
}
