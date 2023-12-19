package io.github.guimartiins.sells.impl;

import io.github.guimartiins.sells.domain.entity.Client;
import io.github.guimartiins.sells.domain.entity.Order;
import io.github.guimartiins.sells.domain.entity.OrderItem;
import io.github.guimartiins.sells.domain.entity.Product;
import io.github.guimartiins.sells.domain.repository.ClientRepository;
import io.github.guimartiins.sells.domain.repository.OrderItemRepository;
import io.github.guimartiins.sells.domain.repository.OrderRepository;
import io.github.guimartiins.sells.domain.repository.ProductRepository;
import io.github.guimartiins.sells.dto.OrderDTO;
import io.github.guimartiins.sells.dto.OrderItemsDTO;
import io.github.guimartiins.sells.exception.EmptyItemsException;
import io.github.guimartiins.sells.exception.NotFoundException;
import io.github.guimartiins.sells.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order save(OrderDTO orderDTO) {
        Integer client_id = orderDTO.getClient();
        Client client = clientRepository
                .findById(client_id)
                .orElseThrow(() -> new NotFoundException("Client not found for this ID" + client_id));


        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setCreated_at(LocalDate.now());
        order.setTotal(orderDTO.getTotal());
        order.setClient(client);

        List<OrderItem> orderItems = convertItems(order, orderDTO.getItems());
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        order.setItems(orderItems);
        return order;
    }

    private List<OrderItem> convertItems(Order order, List<OrderItemsDTO> items) {
        if(items.isEmpty()) {
            throw new EmptyItemsException("Order items cannot be empty");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer product_id = dto.getProduct();
                    Product product = productRepository
                            .findById(product_id)
                            .orElseThrow(() -> new NotFoundException("Product not found for this ID" + dto.getProduct()));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(dto.getQuantity());

                    return orderItem;
                }).collect(Collectors.toList());
    }
}
