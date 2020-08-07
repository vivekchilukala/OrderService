package com.vivek.orderservice.service.dto;

import com.vivek.orderservice.domain.OrderedItems;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.vivek.orderservice.domain.Order} entity.
 */
public class OrderCreateRequest implements Serializable {

    private Long id;

    private String customerName;

    private ZonedDateTime orderDate;

    private String shippingAddress;

    private List<OrderedItems> orderItems;

    private Float total;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderedItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderedItems> orderItems) {
        this.orderItems = orderItems;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderCreateRequest orderDTO = (OrderCreateRequest) o;
        if (orderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", customerName='" + getCustomerName() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", shippingAddress='" + getShippingAddress() + "'" +
            ", total=" + getTotal() +
            "}";
    }
}
