package io.github.guimartiins.sells.domain.repository;

import io.github.guimartiins.sells.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
