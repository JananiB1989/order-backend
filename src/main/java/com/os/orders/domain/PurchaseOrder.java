package com.os.orders.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A PurchaseOrder.
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long purchaseOrderId;

    @Column(name = "order_no")
    private Long orderNo;

    @Column(name = "status")
    private String status;

    @Column(name = "orderdatetime")
    private ZonedDateTime orderdatetime;

    @Column(name = "buyer")
    private String buyer;

    @Column(name = "seller")
    private String seller;

    @Column(name = "ordertotal", precision = 21, scale = 2)
    private BigDecimal ordertotal;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines = new HashSet<>();

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public PurchaseOrder orderNo(Long orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public PurchaseOrder status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getOrderdatetime() {
        return orderdatetime;
    }

    public PurchaseOrder orderdatetime(ZonedDateTime orderdatetime) {
        this.orderdatetime = orderdatetime;
        return this;
    }

    public void setOrderdatetime(ZonedDateTime orderdatetime) {
        this.orderdatetime = orderdatetime;
    }

    public String getBuyer() {
        return buyer;
    }

    public PurchaseOrder buyer(String buyer) {
        this.buyer = buyer;
        return this;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public PurchaseOrder seller(String seller) {
        this.seller = seller;
        return this;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public BigDecimal getOrdertotal() {
        return ordertotal;
    }

    public PurchaseOrder ordertotal(BigDecimal ordertotal) {
        this.ordertotal = ordertotal;
        return this;
    }

    public void setOrdertotal(BigDecimal ordertotal) {
        this.ordertotal = ordertotal;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public PurchaseOrder orderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
        return this;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrder)) {
            return false;
        }
        return purchaseOrderId != null && purchaseOrderId.equals(((PurchaseOrder) o).purchaseOrderId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrder{" +
            "purchaseOrderId=" + getPurchaseOrderId() +
            ", orderNo=" + getOrderNo() +
            ", status='" + getStatus() + "'" +
            ", orderdatetime='" + getOrderdatetime() + "'" +
            ", buyer='" + getBuyer() + "'" +
            ", seller='" + getSeller() + "'" +
            ", ordertotal=" + getOrdertotal() +
            "}";
    }
}
