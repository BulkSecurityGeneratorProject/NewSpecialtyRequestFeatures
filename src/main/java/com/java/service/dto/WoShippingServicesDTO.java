package com.java.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.java.domain.WoShippingServices} entity.
 */
public class WoShippingServicesDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoShippingServicesDTO woShippingServicesDTO = (WoShippingServicesDTO) o;
        if (woShippingServicesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woShippingServicesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoShippingServicesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
