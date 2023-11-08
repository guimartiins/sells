package io.github.guimartiins.sells.domain.repository;

import io.github.guimartiins.sells.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {


}
