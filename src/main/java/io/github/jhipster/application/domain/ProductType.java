package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ProductType.
 */
@Entity
@Table(name = "product_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "productType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductCategoryGroup> categoryGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ProductCategoryGroup> getCategoryGroups() {
        return categoryGroups;
    }

    public ProductType categoryGroups(Set<ProductCategoryGroup> productCategoryGroups) {
        this.categoryGroups = productCategoryGroups;
        return this;
    }

    public ProductType addCategoryGroups(ProductCategoryGroup productCategoryGroup) {
        this.categoryGroups.add(productCategoryGroup);
        productCategoryGroup.setProductType(this);
        return this;
    }

    public ProductType removeCategoryGroups(ProductCategoryGroup productCategoryGroup) {
        this.categoryGroups.remove(productCategoryGroup);
        productCategoryGroup.setProductType(null);
        return this;
    }

    public void setCategoryGroups(Set<ProductCategoryGroup> productCategoryGroups) {
        this.categoryGroups = productCategoryGroups;
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
        ProductType productType = (ProductType) o;
        if (productType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductType{" +
            "id=" + getId() +
            "}";
    }
}
