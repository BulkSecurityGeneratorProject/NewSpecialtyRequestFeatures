package com.java.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.java.domain.WoShippingInfo} entity.
 */
public class WoShippingInfoDTO implements Serializable {

    private Long id;

    @Max(value = 20)
    private Integer monthlyShipVolume;

    private Boolean isCustomsBrokerage;


    private Set<WoShippingServicesDTO> woShippingServices = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonthlyShipVolume() {
        return monthlyShipVolume;
    }

    public void setMonthlyShipVolume(Integer monthlyShipVolume) {
        this.monthlyShipVolume = monthlyShipVolume;
    }

    public Boolean isIsCustomsBrokerage() {
        return isCustomsBrokerage;
    }

    public void setIsCustomsBrokerage(Boolean isCustomsBrokerage) {
        this.isCustomsBrokerage = isCustomsBrokerage;
    }

    public Set<WoShippingServicesDTO> getWoShippingServices() {
        return woShippingServices;
    }

    public void setWoShippingServices(Set<WoShippingServicesDTO> woShippingServices) {
        this.woShippingServices = woShippingServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoShippingInfoDTO woShippingInfoDTO = (WoShippingInfoDTO) o;
        if (woShippingInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woShippingInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoShippingInfoDTO{" +
            "id=" + getId() +
            ", monthlyShipVolume=" + getMonthlyShipVolume() +
            ", isCustomsBrokerage='" + isIsCustomsBrokerage() + "'" +
            "}";
    }
}
