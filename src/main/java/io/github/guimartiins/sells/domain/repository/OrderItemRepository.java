package io.github.guimartiins.sells.domain.repository;

import io.github.guimartiins.sells.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
