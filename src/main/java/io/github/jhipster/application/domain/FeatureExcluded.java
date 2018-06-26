package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FeatureExcluded.
 */
@Entity
@Table(name = "feature_excluded")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FeatureExcluded implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("featuresExcludeds")
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

    public FeatureExcluded name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProducts() {
        return products;
    }

    public FeatureExcluded products(Product product) {
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
        FeatureExcluded featureExcluded = (FeatureExcluded) o;
        if (featureExcluded.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), featureExcluded.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeatureExcluded{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
