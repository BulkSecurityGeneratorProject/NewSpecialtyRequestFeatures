package com.java.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.java.domain.WoCustomsBrokerage} entity.
 */
public class WoCustomsBrokerageDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String monthlyImportShipment;

    @Size(max = 255)
    private String monthlyExportShipment;

    @Size(max = 255)
    private String shipmentValue;

    @Size(max = 255)
    private String productInfo;


    private Set<WoTransportModesDTO> woTransportModes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonthlyImportShipment() {
        return monthlyImportShipment;
    }

    public void setMonthlyImportShipment(String monthlyImportShipment) {
        this.monthlyImportShipment = monthlyImportShipment;
    }

    public String getMonthlyExportShipment() {
        return monthlyExportShipment;
    }

    public void setMonthlyExportShipment(String monthlyExportShipment) {
        this.monthlyExportShipment = monthlyExportShipment;
    }

    public String getShipmentValue() {
        return shipmentValue;
    }

    public void setShipmentValue(String shipmentValue) {
        this.shipmentValue = shipmentValue;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Set<WoTransportModesDTO> getWoTransportModes() {
        return woTransportModes;
    }

    public void setWoTransportModes(Set<WoTransportModesDTO> woTransportModes) {
        this.woTransportModes = woTransportModes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoCustomsBrokerageDTO woCustomsBrokerageDTO = (WoCustomsBrokerageDTO) o;
        if (woCustomsBrokerageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woCustomsBrokerageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoCustomsBrokerageDTO{" +
            "id=" + getId() +
            ", monthlyImportShipment='" + getMonthlyImportShipment() + "'" +
            ", monthlyExportShipment='" + getMonthlyExportShipment() + "'" +
            ", shipmentValue='" + getShipmentValue() + "'" +
            ", productInfo='" + getProductInfo() + "'" +
            "}";
    }
}
