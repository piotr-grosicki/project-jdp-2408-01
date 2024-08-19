package com.kodilla.ecommercee.mappers;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getTotalPrice(),
                orderDto.getCreatedAt(),
                orderDto.isOrderCompleted());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getTotalPrice(),
                order.getCreated(),
                order.isOrderCompleted());
    }

    public List<OrderDto> mapToOrderDtoList (List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

}
