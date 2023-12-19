package io.github.guimartiins.sells.service;

import io.github.guimartiins.sells.domain.entity.Order;
import io.github.guimartiins.sells.dto.OrderDTO;

public interface OrderService {
    Order save(OrderDTO orderDTO);
}
