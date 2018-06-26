package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication1App;

import io.github.jhipster.application.domain.Product;
import io.github.jhipster.application.repository.ProductRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication1App.class)
public class ProductResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_EXCESS_CHARGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXCESS_CHARGE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE_MONTHLY = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE_MONTHLY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE_YEARLY = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE_YEARLY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INTRO_PRICE_MONTHLY = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTRO_PRICE_MONTHLY = new BigDecimal(2);

    private static final Integer DEFAULT_INTRO_MONTHS = 1;
    private static final Integer UPDATED_INTRO_MONTHS = 2;

    private static final Boolean DEFAULT_DEFAULT_TARIFF = false;
    private static final Boolean UPDATED_DEFAULT_TARIFF = true;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductMockMvc;

    private Product product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductResource productResource = new ProductResource(productRepository);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createEntity(EntityManager em) {
        Product product = new Product()
            .name(DEFAULT_NAME)
            .excessCharge(DEFAULT_EXCESS_CHARGE)
            .priceMonthly(DEFAULT_PRICE_MONTHLY)
            .priceYearly(DEFAULT_PRICE_YEARLY)
            .introPriceMonthly(DEFAULT_INTRO_PRICE_MONTHLY)
            .introMonths(DEFAULT_INTRO_MONTHS)
            .defaultTariff(DEFAULT_DEFAULT_TARIFF);
        return product;
    }

    @Before
    public void initTest() {
        product = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProduct.getExcessCharge()).isEqualTo(DEFAULT_EXCESS_CHARGE);
        assertThat(testProduct.getPriceMonthly()).isEqualTo(DEFAULT_PRICE_MONTHLY);
        assertThat(testProduct.getPriceYearly()).isEqualTo(DEFAULT_PRICE_YEARLY);
        assertThat(testProduct.getIntroPriceMonthly()).isEqualTo(DEFAULT_INTRO_PRICE_MONTHLY);
        assertThat(testProduct.getIntroMonths()).isEqualTo(DEFAULT_INTRO_MONTHS);
        assertThat(testProduct.isDefaultTariff()).isEqualTo(DEFAULT_DEFAULT_TARIFF);
    }

    @Test
    @Transactional
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].excessCharge").value(hasItem(DEFAULT_EXCESS_CHARGE.intValue())))
            .andExpect(jsonPath("$.[*].priceMonthly").value(hasItem(DEFAULT_PRICE_MONTHLY.intValue())))
            .andExpect(jsonPath("$.[*].priceYearly").value(hasItem(DEFAULT_PRICE_YEARLY.intValue())))
            .andExpect(jsonPath("$.[*].introPriceMonthly").value(hasItem(DEFAULT_INTRO_PRICE_MONTHLY.intValue())))
            .andExpect(jsonPath("$.[*].introMonths").value(hasItem(DEFAULT_INTRO_MONTHS)))
            .andExpect(jsonPath("$.[*].defaultTariff").value(hasItem(DEFAULT_DEFAULT_TARIFF.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.excessCharge").value(DEFAULT_EXCESS_CHARGE.intValue()))
            .andExpect(jsonPath("$.priceMonthly").value(DEFAULT_PRICE_MONTHLY.intValue()))
            .andExpect(jsonPath("$.priceYearly").value(DEFAULT_PRICE_YEARLY.intValue()))
            .andExpect(jsonPath("$.introPriceMonthly").value(DEFAULT_INTRO_PRICE_MONTHLY.intValue()))
            .andExpect(jsonPath("$.introMonths").value(DEFAULT_INTRO_MONTHS))
            .andExpect(jsonPath("$.defaultTariff").value(DEFAULT_DEFAULT_TARIFF.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct
            .name(UPDATED_NAME)
            .excessCharge(UPDATED_EXCESS_CHARGE)
            .priceMonthly(UPDATED_PRICE_MONTHLY)
            .priceYearly(UPDATED_PRICE_YEARLY)
            .introPriceMonthly(UPDATED_INTRO_PRICE_MONTHLY)
            .introMonths(UPDATED_INTRO_MONTHS)
            .defaultTariff(UPDATED_DEFAULT_TARIFF);

        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProduct.getExcessCharge()).isEqualTo(UPDATED_EXCESS_CHARGE);
        assertThat(testProduct.getPriceMonthly()).isEqualTo(UPDATED_PRICE_MONTHLY);
        assertThat(testProduct.getPriceYearly()).isEqualTo(UPDATED_PRICE_YEARLY);
        assertThat(testProduct.getIntroPriceMonthly()).isEqualTo(UPDATED_INTRO_PRICE_MONTHLY);
        assertThat(testProduct.getIntroMonths()).isEqualTo(UPDATED_INTRO_MONTHS);
        assertThat(testProduct.isDefaultTariff()).isEqualTo(UPDATED_DEFAULT_TARIFF);
    }

    @Test
    @Transactional
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Get the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);
        product2.setId(2L);
        assertThat(product1).isNotEqualTo(product2);
        product1.setId(null);
        assertThat(product1).isNotEqualTo(product2);
    }
}
