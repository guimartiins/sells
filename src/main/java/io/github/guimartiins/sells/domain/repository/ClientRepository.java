package io.github.guimartiins.sells.domain.repository;

import io.github.guimartiins.sells.domain.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Client insert(Client client) {
        entityManager.persist(client);
        return client;
    }

    @Transactional
    public List<Client> findAll() {
        String jpql = "from Client";
        return entityManager
                .createQuery(jpql, Client.class)
                .getResultList();
    }

    @Transactional
    public List<Client> findByName(String name) {
        String jpql = "select c from Client c where c.name like :name";
        return entityManager
                .createQuery(jpql, Client.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Transactional
    public Client update(Client client) {
        entityManager.merge(client);
        return client;
    }

    @Transactional
    public void delete(Integer id) {
        Client client = entityManager.find(Client.class, id);
        this.delete(client);
    }

    @Transactional
    public void delete(Client client) {
        if(entityManager.contains(client)) {
            entityManager.remove(client);
        } else {
            entityManager.remove(entityManager.merge(client));
        }
    }
}
