package com.java.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.java.domain.WoPickPack} entity.
 */
public class WoPickPackDTO implements Serializable {

    private Long id;

    private Float sku;

    private Float avgOrders;

    private Float shipmentWeight;

    @Max(value = 20)
    private Integer shipmentSize;

    @Max(value = 20)
    private Integer monthlyVolume;

    private Boolean isPromotionalInserts;

    @Size(max = 255)
    private String description;


    private Set<WoAdditionalServicesDTO> woAdditionalServices = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSku() {
        return sku;
    }

    public void setSku(Float sku) {
        this.sku = sku;
    }

    public Float getAvgOrders() {
        return avgOrders;
    }

    public void setAvgOrders(Float avgOrders) {
        this.avgOrders = avgOrders;
    }

    public Float getShipmentWeight() {
        return shipmentWeight;
    }

    public void setShipmentWeight(Float shipmentWeight) {
        this.shipmentWeight = shipmentWeight;
    }

    public Integer getShipmentSize() {
        return shipmentSize;
    }

    public void setShipmentSize(Integer shipmentSize) {
        this.shipmentSize = shipmentSize;
    }

    public Integer getMonthlyVolume() {
        return monthlyVolume;
    }

    public void setMonthlyVolume(Integer monthlyVolume) {
        this.monthlyVolume = monthlyVolume;
    }

    public Boolean isIsPromotionalInserts() {
        return isPromotionalInserts;
    }

    public void setIsPromotionalInserts(Boolean isPromotionalInserts) {
        this.isPromotionalInserts = isPromotionalInserts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<WoAdditionalServicesDTO> getWoAdditionalServices() {
        return woAdditionalServices;
    }

    public void setWoAdditionalServices(Set<WoAdditionalServicesDTO> woAdditionalServices) {
        this.woAdditionalServices = woAdditionalServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoPickPackDTO woPickPackDTO = (WoPickPackDTO) o;
        if (woPickPackDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woPickPackDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoPickPackDTO{" +
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
