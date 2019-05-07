package com.java.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.java.domain.WoStorage} entity.
 */
public class WoStorageDTO implements Serializable {

    private Long id;

    @Max(value = 11)
    private Integer quantity;

    private Float spaceRequirement;

    @Size(max = 255)
    private String productInfo;


    private Long woPackageTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getSpaceRequirement() {
        return spaceRequirement;
    }

    public void setSpaceRequirement(Float spaceRequirement) {
        this.spaceRequirement = spaceRequirement;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Long getWoPackageTypeId() {
        return woPackageTypeId;
    }

    public void setWoPackageTypeId(Long woPackageTypeId) {
        this.woPackageTypeId = woPackageTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoStorageDTO woStorageDTO = (WoStorageDTO) o;
        if (woStorageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woStorageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoStorageDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", spaceRequirement=" + getSpaceRequirement() +
            ", productInfo='" + getProductInfo() + "'" +
            ", woPackageType=" + getWoPackageTypeId() +
            "}";
    }
}
