package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoPickPack.
 */
@Entity
@Table(name = "wo_pick_pack")
public class WoPickPack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku")
    private Float sku;

    @Column(name = "avg_orders")
    private Float avgOrders;

    @Column(name = "shipment_weight")
    private Float shipmentWeight;

    @Max(value = 20)
    @Column(name = "shipment_size")
    private Integer shipmentSize;

    @Max(value = 20)
    @Column(name = "monthly_volume")
    private Integer monthlyVolume;

    @Column(name = "is_promotional_inserts")
    private Boolean isPromotionalInserts;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @ManyToMany
    @JoinTable(name = "wo_pick_pack_wo_additional_services",
               joinColumns = @JoinColumn(name = "wo_pick_pack_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "wo_additional_services_id", referencedColumnName = "id"))
    private Set<WoAdditionalServices> woAdditionalServices = new HashSet<>();

    @OneToOne(mappedBy = "woPickPack")
    @JsonIgnore
    private WoWorkOrder woWorkOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSku() {
        return sku;
    }

    public WoPickPack sku(Float sku) {
        this.sku = sku;
        return this;
    }

    public void setSku(Float sku) {
        this.sku = sku;
    }

    public Float getAvgOrders() {
        return avgOrders;
    }

    public WoPickPack avgOrders(Float avgOrders) {
        this.avgOrders = avgOrders;
        return this;
    }

    public void setAvgOrders(Float avgOrders) {
        this.avgOrders = avgOrders;
    }

    public Float getShipmentWeight() {
        return shipmentWeight;
    }

    public WoPickPack shipmentWeight(Float shipmentWeight) {
        this.shipmentWeight = shipmentWeight;
        return this;
    }

    public void setShipmentWeight(Float shipmentWeight) {
        this.shipmentWeight = shipmentWeight;
    }

    public Integer getShipmentSize() {
        return shipmentSize;
    }

    public WoPickPack shipmentSize(Integer shipmentSize) {
        this.shipmentSize = shipmentSize;
        return this;
    }

    public void setShipmentSize(Integer shipmentSize) {
        this.shipmentSize = shipmentSize;
    }

    public Integer getMonthlyVolume() {
        return monthlyVolume;
    }

    public WoPickPack monthlyVolume(Integer monthlyVolume) {
        this.monthlyVolume = monthlyVolume;
        return this;
    }

    public void setMonthlyVolume(Integer monthlyVolume) {
        this.monthlyVolume = monthlyVolume;
    }

    public Boolean isIsPromotionalInserts() {
        return isPromotionalInserts;
    }

    public WoPickPack isPromotionalInserts(Boolean isPromotionalInserts) {
        this.isPromotionalInserts = isPromotionalInserts;
        return this;
    }

    public void setIsPromotionalInserts(Boolean isPromotionalInserts) {
        this.isPromotionalInserts = isPromotionalInserts;
    }

    public String getDescription() {
        return description;
    }

    public WoPickPack description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<WoAdditionalServices> getWoAdditionalServices() {
        return woAdditionalServices;
    }

    public WoPickPack woAdditionalServices(Set<WoAdditionalServices> woAdditionalServices) {
        this.woAdditionalServices = woAdditionalServices;
        return this;
    }

    public WoPickPack addWoAdditionalServices(WoAdditionalServices woAdditionalServices) {
        this.woAdditionalServices.add(woAdditionalServices);
        woAdditionalServices.getWoPickPacks().add(this);
        return this;
    }

    public WoPickPack removeWoAdditionalServices(WoAdditionalServices woAdditionalServices) {
        this.woAdditionalServices.remove(woAdditionalServices);
        woAdditionalServices.getWoPickPacks().remove(this);
        return this;
    }

    public void setWoAdditionalServices(Set<WoAdditionalServices> woAdditionalServices) {
        this.woAdditionalServices = woAdditionalServices;
    }

    public WoWorkOrder getWoWorkOrder() {
        return woWorkOrder;
    }

    public WoPickPack woWorkOrder(WoWorkOrder woWorkOrder) {
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
        if (!(o instanceof WoPickPack)) {
            return false;
        }
        return id != null && id.equals(((WoPickPack) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoPickPack{" +
            "id=" + getId() +
            ", sku=" + getSku() +
            ", avgOrders=" + getAvgOrders() +
            ", shipmentWeight=" + getShipmentWeight() +
            ", shipmentSize=" + getShipmentSize() +
            ", monthlyVolume=" + getMonthlyVolume() +
            ", isPromotionalInserts='" + isIsPromotionalInserts() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
