package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoAdditionalServices.
 */
@Entity
@Table(name = "wo_additional_services")
public class WoAdditionalServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @ManyToMany(mappedBy = "woAdditionalServices")
    @JsonIgnore
    private Set<WoPickPack> woPickPacks = new HashSet<>();

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

    public WoAdditionalServices name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WoPickPack> getWoPickPacks() {
        return woPickPacks;
    }

    public WoAdditionalServices woPickPacks(Set<WoPickPack> woPickPacks) {
        this.woPickPacks = woPickPacks;
        return this;
    }

    public WoAdditionalServices addWoPickPack(WoPickPack woPickPack) {
        this.woPickPacks.add(woPickPack);
        woPickPack.getWoAdditionalServices().add(this);
        return this;
    }

    public WoAdditionalServices removeWoPickPack(WoPickPack woPickPack) {
        this.woPickPacks.remove(woPickPack);
        woPickPack.getWoAdditionalServices().remove(this);
        return this;
    }

    public void setWoPickPacks(Set<WoPickPack> woPickPacks) {
        this.woPickPacks = woPickPacks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoAdditionalServices)) {
            return false;
        }
        return id != null && id.equals(((WoAdditionalServices) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoAdditionalServices{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
