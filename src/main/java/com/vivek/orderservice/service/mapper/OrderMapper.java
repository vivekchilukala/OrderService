package com.vivek.orderservice.service.mapper;

import com.vivek.orderservice.domain.*;
import com.vivek.orderservice.service.dto.OrderCreateRequest;
import com.vivek.orderservice.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {


    Order toEntity(OrderDTO orderDTO);

    Order toEntity(OrderCreateRequest request);

    @Mapping(source = "orderItems", target = "orderedItems")
    OrderDTO toDto(Order order);


}
