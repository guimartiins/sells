package io.github.guimartiins.sells.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer client;
    private BigDecimal total;
    private List<OrderItemsDTO> items;
}
