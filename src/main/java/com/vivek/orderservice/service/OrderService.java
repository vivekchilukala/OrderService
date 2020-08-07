package com.vivek.orderservice.service;

import com.vivek.orderservice.domain.Order;
import com.vivek.orderservice.repository.OrderRepository;
import com.vivek.orderservice.service.dto.OrderCreateRequest;
import com.vivek.orderservice.service.dto.OrderDTO;
import com.vivek.orderservice.service.dto.OrderItemsDTO;
import com.vivek.orderservice.service.mapper.OrderMapper;
import com.vivek.orderservice.web.rest.errors.AlertException;
import com.vivek.orderservice.web.rest.services.OrderListService;
import io.jsonwebtoken.lang.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Order}.
 */
@Service
@Transactional
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderListService orderListService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, OrderListService orderListService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderListService = orderListService;
    }

    /**
     * Save a order.
     *
     * @param orderDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderDTO save(OrderCreateRequest orderDTO) {
        log.debug("Request to save Order : {}", orderDTO);
        Order order = orderMapper.toEntity(orderDTO);

        Set<OrderItemsDTO> itemsDTOS = verifyProductAvailability(order);

        order = orderRepository.save(order);

        return toOrder(order, itemsDTOS);
    }

    /**
     * Get all the orders.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        log.debug("Request to get all Orders");
        Set<OrderItemsDTO> orderItemsDTOS = orderListService.getOrderItems();
        List<Order> getAllOrders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        getAllOrders.forEach(o -> {
            orderDTOS.add(toOrder(o,orderItemsDTOS));
        });
        return orderDTOS;
    }


    /**
     * Get one order by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderDTO> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id)
            .map(orderMapper::toDto);
    }

    /**
     * Delete the order by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }

    private Set<OrderItemsDTO> verifyProductAvailability(Order order){
        Set<OrderItemsDTO> items = new HashSet<>();
        if(!Collections.isEmpty(order.getOrderItems())) {
            order.getOrderItems().forEach( p -> {
                Optional<OrderItemsDTO> itemsDTO = orderListService.getOrderItemBySku(p.getProductCode());
                if(!itemsDTO.isPresent()){
                    throw new AlertException(HttpStatus.NO_CONTENT, "NoProduct","product with this Product Code "+p.getProductCode()+" is not found");
                }
                items.add(itemsDTO.get());
            });

        }
        return items;
    }

    private OrderDTO toOrder(Order order, Set<OrderItemsDTO> itemsDTOS){
        OrderDTO orderDTO = orderMapper.toDto(order);

        orderDTO.getOrderedItems().stream()
            .forEach(orderItems -> itemsDTOS.stream()
            .filter(i -> i.getProductCode().equalsIgnoreCase(orderItems.getProductCode()))
                .forEach(orders -> {
                    orderItems.setProductName(orders.getProductName());
                    orderItems.setQuantity(orders.getQuantity());
                }));
        return orderDTO;
    }
}
