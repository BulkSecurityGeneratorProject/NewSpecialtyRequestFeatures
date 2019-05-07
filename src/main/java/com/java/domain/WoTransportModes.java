package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoTransportModes.
 */
@Entity
@Table(name = "wo_transport_modes")
public class WoTransportModes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @ManyToMany(mappedBy = "woTransportModes")
    @JsonIgnore
    private Set<WoCustomsBrokerage> woCustomsBrokerages = new HashSet<>();

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

    public WoTransportModes name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WoCustomsBrokerage> getWoCustomsBrokerages() {
        return woCustomsBrokerages;
    }

    public WoTransportModes woCustomsBrokerages(Set<WoCustomsBrokerage> woCustomsBrokerages) {
        this.woCustomsBrokerages = woCustomsBrokerages;
        return this;
    }

    public WoTransportModes addWoCustomsBrokerage(WoCustomsBrokerage woCustomsBrokerage) {
        this.woCustomsBrokerages.add(woCustomsBrokerage);
        woCustomsBrokerage.getWoTransportModes().add(this);
        return this;
    }

    public WoTransportModes removeWoCustomsBrokerage(WoCustomsBrokerage woCustomsBrokerage) {
        this.woCustomsBrokerages.remove(woCustomsBrokerage);
        woCustomsBrokerage.getWoTransportModes().remove(this);
        return this;
    }

    public void setWoCustomsBrokerages(Set<WoCustomsBrokerage> woCustomsBrokerages) {
        this.woCustomsBrokerages = woCustomsBrokerages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoTransportModes)) {
            return false;
        }
        return id != null && id.equals(((WoTransportModes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoTransportModes{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
