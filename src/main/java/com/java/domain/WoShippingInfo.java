package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoShippingInfo.
 */
@Entity
@Table(name = "wo_shipping_info")
public class WoShippingInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Max(value = 20)
    @Column(name = "monthly_ship_volume")
    private Integer monthlyShipVolume;

    @Column(name = "is_customs_brokerage")
    private Boolean isCustomsBrokerage;

    @ManyToMany
    @JoinTable(name = "wo_shipping_info_wo_shipping_services",
               joinColumns = @JoinColumn(name = "wo_shipping_info_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "wo_shipping_services_id", referencedColumnName = "id"))
    private Set<WoShippingServices> woShippingServices = new HashSet<>();

    @OneToOne(mappedBy = "woShippingInfo")
    @JsonIgnore
    private WoWorkOrder woWorkOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonthlyShipVolume() {
        return monthlyShipVolume;
    }

    public WoShippingInfo monthlyShipVolume(Integer monthlyShipVolume) {
        this.monthlyShipVolume = monthlyShipVolume;
        return this;
    }

    public void setMonthlyShipVolume(Integer monthlyShipVolume) {
        this.monthlyShipVolume = monthlyShipVolume;
    }

    public Boolean isIsCustomsBrokerage() {
        return isCustomsBrokerage;
    }

    public WoShippingInfo isCustomsBrokerage(Boolean isCustomsBrokerage) {
        this.isCustomsBrokerage = isCustomsBrokerage;
        return this;
    }

    public void setIsCustomsBrokerage(Boolean isCustomsBrokerage) {
        this.isCustomsBrokerage = isCustomsBrokerage;
    }

    public Set<WoShippingServices> getWoShippingServices() {
        return woShippingServices;
    }

    public WoShippingInfo woShippingServices(Set<WoShippingServices> woShippingServices) {
        this.woShippingServices = woShippingServices;
        return this;
    }

    public WoShippingInfo addWoShippingServices(WoShippingServices woShippingServices) {
        this.woShippingServices.add(woShippingServices);
        woShippingServices.getWoShippingInfos().add(this);
        return this;
    }

    public WoShippingInfo removeWoShippingServices(WoShippingServices woShippingServices) {
        this.woShippingServices.remove(woShippingServices);
        woShippingServices.getWoShippingInfos().remove(this);
        return this;
    }

    public void setWoShippingServices(Set<WoShippingServices> woShippingServices) {
        this.woShippingServices = woShippingServices;
    }

    public WoWorkOrder getWoWorkOrder() {
        return woWorkOrder;
    }

    public WoShippingInfo woWorkOrder(WoWorkOrder woWorkOrder) {
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
        if (!(o instanceof WoShippingInfo)) {
            return false;
        }
        return id != null && id.equals(((WoShippingInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoShippingInfo{" +
            "id=" + getId() +
            ", monthlyShipVolume=" + getMonthlyShipVolume() +
            ", isCustomsBrokerage='" + isIsCustomsBrokerage() + "'" +
            "}";
    }
}
