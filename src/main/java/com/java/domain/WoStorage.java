package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WoStorage.
 */
@Entity
@Table(name = "wo_storage")
public class WoStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Max(value = 11)
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "space_requirement")
    private Float spaceRequirement;

    @Size(max = 255)
    @Column(name = "product_info", length = 255)
    private String productInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private WoPackageType woPackageType;

    @OneToOne(mappedBy = "woStorage")
    @JsonIgnore
    private WoWorkOrder woWorkOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public WoStorage quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getSpaceRequirement() {
        return spaceRequirement;
    }

    public WoStorage spaceRequirement(Float spaceRequirement) {
        this.spaceRequirement = spaceRequirement;
        return this;
    }

    public void setSpaceRequirement(Float spaceRequirement) {
        this.spaceRequirement = spaceRequirement;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public WoStorage productInfo(String productInfo) {
        this.productInfo = productInfo;
        return this;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public WoPackageType getWoPackageType() {
        return woPackageType;
    }

    public WoStorage woPackageType(WoPackageType woPackageType) {
        this.woPackageType = woPackageType;
        return this;
    }

    public void setWoPackageType(WoPackageType woPackageType) {
        this.woPackageType = woPackageType;
    }

    public WoWorkOrder getWoWorkOrder() {
        return woWorkOrder;
    }

    public WoStorage woWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrder = woWorkOrder;
        return this;
    }

    public void setWoWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrder = woWorkOrder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoStorage)) {
            return false;
        }
        return id != null && id.equals(((WoStorage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoStorage{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", spaceRequirement=" + getSpaceRequirement() +
            ", productInfo='" + getProductInfo() + "'" +
            "}";
    }
}
