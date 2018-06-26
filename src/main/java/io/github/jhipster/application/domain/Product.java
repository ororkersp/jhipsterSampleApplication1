package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "excess_charge", precision = 10, scale = 2)
    private BigDecimal excessCharge;

    @Column(name = "price_monthly", precision = 10, scale = 2)
    private BigDecimal priceMonthly;

    @Column(name = "price_yearly", precision = 10, scale = 2)
    private BigDecimal priceYearly;

    @Column(name = "intro_price_monthly", precision = 10, scale = 2)
    private BigDecimal introPriceMonthly;

    @Column(name = "intro_months")
    private Integer introMonths;

    @Column(name = "default_tariff")
    private Boolean defaultTariff;

    @OneToMany(mappedBy = "products")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FeatureIncluded> featuresIncludeds = new HashSet<>();

    @OneToMany(mappedBy = "products")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FeatureExcluded> featuresExcludeds = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("products")
    private ProductCategory productCategory;

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

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getExcessCharge() {
        return excessCharge;
    }

    public Product excessCharge(BigDecimal excessCharge) {
        this.excessCharge = excessCharge;
        return this;
    }

    public void setExcessCharge(BigDecimal excessCharge) {
        this.excessCharge = excessCharge;
    }

    public BigDecimal getPriceMonthly() {
        return priceMonthly;
    }

    public Product priceMonthly(BigDecimal priceMonthly) {
        this.priceMonthly = priceMonthly;
        return this;
    }

    public void setPriceMonthly(BigDecimal priceMonthly) {
        this.priceMonthly = priceMonthly;
    }

    public BigDecimal getPriceYearly() {
        return priceYearly;
    }

    public Product priceYearly(BigDecimal priceYearly) {
        this.priceYearly = priceYearly;
        return this;
    }

    public void setPriceYearly(BigDecimal priceYearly) {
        this.priceYearly = priceYearly;
    }

    public BigDecimal getIntroPriceMonthly() {
        return introPriceMonthly;
    }

    public Product introPriceMonthly(BigDecimal introPriceMonthly) {
        this.introPriceMonthly = introPriceMonthly;
        return this;
    }

    public void setIntroPriceMonthly(BigDecimal introPriceMonthly) {
        this.introPriceMonthly = introPriceMonthly;
    }

    public Integer getIntroMonths() {
        return introMonths;
    }

    public Product introMonths(Integer introMonths) {
        this.introMonths = introMonths;
        return this;
    }

    public void setIntroMonths(Integer introMonths) {
        this.introMonths = introMonths;
    }

    public Boolean isDefaultTariff() {
        return defaultTariff;
    }

    public Product defaultTariff(Boolean defaultTariff) {
        this.defaultTariff = defaultTariff;
        return this;
    }

    public void setDefaultTariff(Boolean defaultTariff) {
        this.defaultTariff = defaultTariff;
    }

    public Set<FeatureIncluded> getFeaturesIncludeds() {
        return featuresIncludeds;
    }

    public Product featuresIncludeds(Set<FeatureIncluded> featureIncludeds) {
        this.featuresIncludeds = featureIncludeds;
        return this;
    }

    public Product addFeaturesIncluded(FeatureIncluded featureIncluded) {
        this.featuresIncludeds.add(featureIncluded);
        featureIncluded.setProducts(this);
        return this;
    }

    public Product removeFeaturesIncluded(FeatureIncluded featureIncluded) {
        this.featuresIncludeds.remove(featureIncluded);
        featureIncluded.setProducts(null);
        return this;
    }

    public void setFeaturesIncludeds(Set<FeatureIncluded> featureIncludeds) {
        this.featuresIncludeds = featureIncludeds;
    }

    public Set<FeatureExcluded> getFeaturesExcludeds() {
        return featuresExcludeds;
    }

    public Product featuresExcludeds(Set<FeatureExcluded> featureExcludeds) {
        this.featuresExcludeds = featureExcludeds;
        return this;
    }

    public Product addFeaturesExcluded(FeatureExcluded featureExcluded) {
        this.featuresExcludeds.add(featureExcluded);
        featureExcluded.setProducts(this);
        return this;
    }

    public Product removeFeaturesExcluded(FeatureExcluded featureExcluded) {
        this.featuresExcludeds.remove(featureExcluded);
        featureExcluded.setProducts(null);
        return this;
    }

    public void setFeaturesExcludeds(Set<FeatureExcluded> featureExcludeds) {
        this.featuresExcludeds = featureExcludeds;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Product productCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        return this;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", excessCharge=" + getExcessCharge() +
            ", priceMonthly=" + getPriceMonthly() +
            ", priceYearly=" + getPriceYearly() +
            ", introPriceMonthly=" + getIntroPriceMonthly() +
            ", introMonths=" + getIntroMonths() +
            ", defaultTariff='" + isDefaultTariff() + "'" +
            "}";
    }
}
