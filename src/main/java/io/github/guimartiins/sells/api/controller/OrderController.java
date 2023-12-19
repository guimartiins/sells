package io.github.guimartiins.sells.api.controller;

import io.github.guimartiins.sells.domain.entity.Order;
import io.github.guimartiins.sells.dto.OrderDTO;
import io.github.guimartiins.sells.service.OrderService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/orders/")
public class OrderController {

    private OrderService orderService;

    private OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.save(orderDTO);

        return order.getId();
    }
}
