package com.java.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WoPackageType.
 */
@Entity
@Table(name = "wo_package_type")
public class WoPackageType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @OneToOne(mappedBy = "woPackageType")
    @JsonIgnore
    private WoStorage woStorage;

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

    public WoPackageType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WoStorage getWoStorage() {
        return woStorage;
    }

    public WoPackageType woStorage(WoStorage woStorage) {
        this.woStorage = woStorage;
        return this;
    }

    public void setWoStorage(WoStorage woStorage) {
        this.woStorage = woStorage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoPackageType)) {
            return false;
        }
        return id != null && id.equals(((WoPackageType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoPackageType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
