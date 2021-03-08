package com.os.orders.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "description")
    private String description;

    @Column(name = "itemprice", precision = 21, scale = 2)
    private BigDecimal itemprice;

    @Column(name = "weight", precision = 21, scale = 2)
    private BigDecimal weight;

    @Column(name = "weight_uom")
    private String weightUOM;

    @Column(name = "volume", precision = 21, scale = 2)
    private BigDecimal volume;

    @Column(name = "volume_uom")
    private String volumeUOM;

    @ManyToOne
    private OrderLine orderLine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public Item itemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String description() {
        return description;
    }

    public Item description(String description) {
        this.description = description;
        return this;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public BigDecimal getItemprice() {
        return itemprice;
    }

    public Item itemprice(BigDecimal itemprice) {
        this.itemprice = itemprice;
        return this;
    }

    public void setItemprice(BigDecimal itemprice) {
        this.itemprice = itemprice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public Item weight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getWeightUOM() {
        return weightUOM;
    }

    public Item weightUOM(String weightUOM) {
        this.weightUOM = weightUOM;
        return this;
    }

    public void setWeightUOM(String weightUOM) {
        this.weightUOM = weightUOM;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public Item volume(BigDecimal volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getVolumeUOM() {
        return volumeUOM;
    }

    public Item volumeUOM(String volumeUOM) {
        this.volumeUOM = volumeUOM;
        return this;
    }

    public void setVolumeUOM(String volumeUOM) {
        this.volumeUOM = volumeUOM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return id != null && id.equals(((Item) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", itemId=" + getItemId() +
            ", description='" + description() + "'" +
            ", itemprice=" + getItemprice() +
            ", weight=" + getWeight() +
            ", weightUOM='" + getWeightUOM() + "'" +
            ", volume=" + getVolume() +
            ", volumeUOM='" + getVolumeUOM() + "'" +
            "}";
    }
}
