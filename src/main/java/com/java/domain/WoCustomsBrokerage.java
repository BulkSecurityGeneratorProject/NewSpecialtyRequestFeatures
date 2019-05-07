package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoCustomsBrokerage.
 */
@Entity
@Table(name = "wo_customs_brokerage")
public class WoCustomsBrokerage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "monthly_import_shipment", length = 255)
    private String monthlyImportShipment;

    @Size(max = 255)
    @Column(name = "monthly_export_shipment", length = 255)
    private String monthlyExportShipment;

    @Size(max = 255)
    @Column(name = "shipment_value", length = 255)
    private String shipmentValue;

    @Size(max = 255)
    @Column(name = "product_info", length = 255)
    private String productInfo;

    @ManyToMany
    @JoinTable(name = "wo_customs_brokerage_wo_transport_modes",
               joinColumns = @JoinColumn(name = "wo_customs_brokerage_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "wo_transport_modes_id", referencedColumnName = "id"))
    private Set<WoTransportModes> woTransportModes = new HashSet<>();

    @OneToOne(mappedBy = "woCustomsBrokerage")
    @JsonIgnore
    private WoWorkOrder woWorkOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonthlyImportShipment() {
        return monthlyImportShipment;
    }

    public WoCustomsBrokerage monthlyImportShipment(String monthlyImportShipment) {
        this.monthlyImportShipment = monthlyImportShipment;
        return this;
    }

    public void setMonthlyImportShipment(String monthlyImportShipment) {
        this.monthlyImportShipment = monthlyImportShipment;
    }

    public String getMonthlyExportShipment() {
        return monthlyExportShipment;
    }

    public WoCustomsBrokerage monthlyExportShipment(String monthlyExportShipment) {
        this.monthlyExportShipment = monthlyExportShipment;
        return this;
    }

    public void setMonthlyExportShipment(String monthlyExportShipment) {
        this.monthlyExportShipment = monthlyExportShipment;
    }

    public String getShipmentValue() {
        return shipmentValue;
    }

    public WoCustomsBrokerage shipmentValue(String shipmentValue) {
        this.shipmentValue = shipmentValue;
        return this;
    }

    public void setShipmentValue(String shipmentValue) {
        this.shipmentValue = shipmentValue;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public WoCustomsBrokerage productInfo(String productInfo) {
        this.productInfo = productInfo;
        return this;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Set<WoTransportModes> getWoTransportModes() {
        return woTransportModes;
    }

    public WoCustomsBrokerage woTransportModes(Set<WoTransportModes> woTransportModes) {
        this.woTransportModes = woTransportModes;
        return this;
    }

    public WoCustomsBrokerage addWoTransportModes(WoTransportModes woTransportModes) {
        this.woTransportModes.add(woTransportModes);
        woTransportModes.getWoCustomsBrokerages().add(this);
        return this;
    }

    public WoCustomsBrokerage removeWoTransportModes(WoTransportModes woTransportModes) {
        this.woTransportModes.remove(woTransportModes);
        woTransportModes.getWoCustomsBrokerages().remove(this);
        return this;
    }

    public void setWoTransportModes(Set<WoTransportModes> woTransportModes) {
        this.woTransportModes = woTransportModes;
    }

    public WoWorkOrder getWoWorkOrder() {
        return woWorkOrder;
    }

    public WoCustomsBrokerage woWorkOrder(WoWorkOrder woWorkOrder) {
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
        if (!(o instanceof WoCustomsBrokerage)) {
            return false;
        }
        return id != null && id.equals(((WoCustomsBrokerage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoCustomsBrokerage{" +
            "id=" + getId() +
            ", monthlyImportShipment='" + getMonthlyImportShipment() + "'" +
            ", monthlyExportShipment='" + getMonthlyExportShipment() + "'" +
            ", shipmentValue='" + getShipmentValue() + "'" +
            ", productInfo='" + getProductInfo() + "'" +
            "}";
    }
}
