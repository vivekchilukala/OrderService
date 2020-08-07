package com.vivek.orderservice.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.vivek.orderservice.domain.Order} entity.
 */
public class OrderDTO implements Serializable {

    private Long id;

    private String customerName;

    private ZonedDateTime orderDate;

    private List<OrderItemsDTO> orderedItems;

    private String shippingAddress;

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

    public List<OrderItemsDTO> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderItemsDTO> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
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

        OrderDTO orderDTO = (OrderDTO) o;
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
            "id=" + id +
            ", customerName='" + customerName + '\'' +
            ", orderDate=" + orderDate +
            ", orderedItems=" + orderedItems +
            ", shippingAddress='" + shippingAddress + '\'' +
            ", total=" + total +
            '}';
    }
}
