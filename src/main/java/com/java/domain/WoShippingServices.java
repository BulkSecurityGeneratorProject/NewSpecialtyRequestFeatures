package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoShippingServices.
 */
@Entity
@Table(name = "wo_shipping_services")
public class WoShippingServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @ManyToMany(mappedBy = "woShippingServices")
    @JsonIgnore
    private Set<WoShippingInfo> woShippingInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public WoShippingServices name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WoShippingInfo> getWoShippingInfos() {
        return woShippingInfos;
    }

    public WoShippingServices woShippingInfos(Set<WoShippingInfo> woShippingInfos) {
        this.woShippingInfos = woShippingInfos;
        return this;
    }

    public WoShippingServices addWoShippingInfo(WoShippingInfo woShippingInfo) {
        this.woShippingInfos.add(woShippingInfo);
        woShippingInfo.getWoShippingServices().add(this);
        return this;
    }

    public WoShippingServices removeWoShippingInfo(WoShippingInfo woShippingInfo) {
        this.woShippingInfos.remove(woShippingInfo);
        woShippingInfo.getWoShippingServices().remove(this);
        return this;
    }

    public void setWoShippingInfos(Set<WoShippingInfo> woShippingInfos) {
        this.woShippingInfos = woShippingInfos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoShippingServices)) {
            return false;
        }
        return id != null && id.equals(((WoShippingServices) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoShippingServices{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
