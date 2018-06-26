package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ProductCategoryGroup.
 */
@Entity
@Table(name = "product_category_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductCategoryGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "categoryGroup")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductCategory> productCategories = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("categoryGroups")
    private ProductType productType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public ProductCategoryGroup productCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
        return this;
    }

    public ProductCategoryGroup addProductCategories(ProductCategory productCategory) {
        this.productCategories.add(productCategory);
        productCategory.setCategoryGroup(this);
        return this;
    }

    public ProductCategoryGroup removeProductCategories(ProductCategory productCategory) {
        this.productCategories.remove(productCategory);
        productCategory.setCategoryGroup(null);
        return this;
    }

    public void setProductCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    public ProductType getProductType() {
        return productType;
    }

    public ProductCategoryGroup productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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
        ProductCategoryGroup productCategoryGroup = (ProductCategoryGroup) o;
        if (productCategoryGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productCategoryGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductCategoryGroup{" +
            "id=" + getId() +
            "}";
    }
}
