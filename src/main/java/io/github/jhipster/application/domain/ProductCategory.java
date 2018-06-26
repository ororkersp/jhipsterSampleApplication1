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
 * A ProductCategory.
 */
@Entity
@Table(name = "product_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "terms_and_conditions_url")
    private String termsAndConditionsUrl;

    @Column(name = "default_product_id")
    private String defaultProductId;

    @OneToMany(mappedBy = "productCategory")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Product> products = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("productCategories")
    private ProductCategoryGroup categoryGroup;

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

    public ProductCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTermsAndConditionsUrl() {
        return termsAndConditionsUrl;
    }

    public ProductCategory termsAndConditionsUrl(String termsAndConditionsUrl) {
        this.termsAndConditionsUrl = termsAndConditionsUrl;
        return this;
    }

    public void setTermsAndConditionsUrl(String termsAndConditionsUrl) {
        this.termsAndConditionsUrl = termsAndConditionsUrl;
    }

    public String getDefaultProductId() {
        return defaultProductId;
    }

    public ProductCategory defaultProductId(String defaultProductId) {
        this.defaultProductId = defaultProductId;
        return this;
    }

    public void setDefaultProductId(String defaultProductId) {
        this.defaultProductId = defaultProductId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public ProductCategory products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public ProductCategory addProducts(Product product) {
        this.products.add(product);
        product.setProductCategory(this);
        return this;
    }

    public ProductCategory removeProducts(Product product) {
        this.products.remove(product);
        product.setProductCategory(null);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public ProductCategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    public ProductCategory categoryGroup(ProductCategoryGroup productCategoryGroup) {
        this.categoryGroup = productCategoryGroup;
        return this;
    }

    public void setCategoryGroup(ProductCategoryGroup productCategoryGroup) {
        this.categoryGroup = productCategoryGroup;
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
        ProductCategory productCategory = (ProductCategory) o;
        if (productCategory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productCategory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", termsAndConditionsUrl='" + getTermsAndConditionsUrl() + "'" +
            ", defaultProductId='" + getDefaultProductId() + "'" +
            "}";
    }
}
