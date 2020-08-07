package com.vivek.orderservice.web.rest.services;


import com.vivek.orderservice.service.dto.OrderItemsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@FeignClient(name="orderlist",url="${orderItems.orderListUrl}")
public interface OrderListService {

    @GetMapping("/api/order-items")
    Set<OrderItemsDTO> getOrderItems();


    @GetMapping("/api/order-items/product/{sku}")
    Optional<OrderItemsDTO> getOrderItemBySku(@PathVariable("sku") String sku);

//    @GetMapping("/core/service/customer/locations/states/{customerId}")
//    List<String> getRegisteredStates(@PathVariable("customerId") Long customerId);

}

