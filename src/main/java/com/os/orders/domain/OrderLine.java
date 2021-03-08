package com.os.orders.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A OrderLine.
 */
@Entity
@Table(name = "order_line")
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "order_line_no")
    private Long orderLineNo;

    @Column(name = "status")
    private String status;

    @Column(name = "ordered_qty", precision = 21, scale = 2)
    private BigDecimal orderedQty;

    @Column(name = "line_price", precision = 21, scale = 2)
    private BigDecimal linePrice;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderLineNo() {
        return orderLineNo;
    }

    public OrderLine orderLineNo(Long orderLineNo) {
        this.orderLineNo = orderLineNo;
        return this;
    }

    public void setOrderLineNo(Long orderLineNo) {
        this.orderLineNo = orderLineNo;
    }

    public String getStatus() {
        return status;
    }

    public OrderLine status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getOrderedQty() {
        return orderedQty;
    }

    public OrderLine orderedQty(BigDecimal orderedQty) {
        this.orderedQty = orderedQty;
        return this;
    }

    public void setOrderedQty(BigDecimal orderedQty) {
        this.orderedQty = orderedQty;
    }

    public BigDecimal getLinePrice() {
        return linePrice;
    }

    public OrderLine linePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
        return this;
    }

    public void setLinePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
    }

    public Set<Item> getItems() {
        return items;
    }

    public OrderLine items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderLine)) {
            return false;
        }
        return id != null && id.equals(((OrderLine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderLine{" +
            "id=" + getId() +
            ", orderLineNo=" + getOrderLineNo() +
            ", status='" + getStatus() + "'" +
            ", orderedQty=" + getOrderedQty() +
            ", linePrice=" + getLinePrice() +
            "}";
    }
}
