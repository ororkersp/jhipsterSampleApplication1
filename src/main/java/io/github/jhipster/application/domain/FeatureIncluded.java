package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FeatureIncluded.
 */
@Entity
@Table(name = "feature_included")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FeatureIncluded implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "caveat")
    private String caveat;

    @Column(name = "highlighted")
    private Boolean highlighted;

    @ManyToOne
    @JsonIgnoreProperties("featuresIncludeds")
    private Product products;

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

    public FeatureIncluded name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaveat() {
        return caveat;
    }

    public FeatureIncluded caveat(String caveat) {
        this.caveat = caveat;
        return this;
    }

    public void setCaveat(String caveat) {
        this.caveat = caveat;
    }

    public Boolean isHighlighted() {
        return highlighted;
    }

    public FeatureIncluded highlighted(Boolean highlighted) {
        this.highlighted = highlighted;
        return this;
    }

    public void setHighlighted(Boolean highlighted) {
        this.highlighted = highlighted;
    }

    public Product getProducts() {
        return products;
    }

    public FeatureIncluded products(Product product) {
        this.products = product;
        return this;
    }

    public void setProducts(Product product) {
        this.products = product;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeatureIncluded featureIncluded = (FeatureIncluded) o;
        if (featureIncluded.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), featureIncluded.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeatureIncluded{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", caveat='" + getCaveat() + "'" +
            ", highlighted='" + isHighlighted() + "'" +
            "}";
    }
}
