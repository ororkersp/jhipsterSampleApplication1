package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication1App;

import io.github.jhipster.application.domain.ProductCategoryGroup;
import io.github.jhipster.application.repository.ProductCategoryGroupRepository;
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
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductCategoryGroupResource REST controller.
 *
 * @see ProductCategoryGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication1App.class)
public class ProductCategoryGroupResourceIntTest {

    @Autowired
    private ProductCategoryGroupRepository productCategoryGroupRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductCategoryGroupMockMvc;

    private ProductCategoryGroup productCategoryGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductCategoryGroupResource productCategoryGroupResource = new ProductCategoryGroupResource(productCategoryGroupRepository);
        this.restProductCategoryGroupMockMvc = MockMvcBuilders.standaloneSetup(productCategoryGroupResource)
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
    public static ProductCategoryGroup createEntity(EntityManager em) {
        ProductCategoryGroup productCategoryGroup = new ProductCategoryGroup();
        return productCategoryGroup;
    }

    @Before
    public void initTest() {
        productCategoryGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductCategoryGroup() throws Exception {
        int databaseSizeBeforeCreate = productCategoryGroupRepository.findAll().size();

        // Create the ProductCategoryGroup
        restProductCategoryGroupMockMvc.perform(post("/api/product-category-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productCategoryGroup)))
            .andExpect(status().isCreated());

        // Validate the ProductCategoryGroup in the database
        List<ProductCategoryGroup> productCategoryGroupList = productCategoryGroupRepository.findAll();
        assertThat(productCategoryGroupList).hasSize(databaseSizeBeforeCreate + 1);
        ProductCategoryGroup testProductCategoryGroup = productCategoryGroupList.get(productCategoryGroupList.size() - 1);
    }

    @Test
    @Transactional
    public void createProductCategoryGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productCategoryGroupRepository.findAll().size();

        // Create the ProductCategoryGroup with an existing ID
        productCategoryGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductCategoryGroupMockMvc.perform(post("/api/product-category-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productCategoryGroup)))
            .andExpect(status().isBadRequest());

        // Validate the ProductCategoryGroup in the database
        List<ProductCategoryGroup> productCategoryGroupList = productCategoryGroupRepository.findAll();
        assertThat(productCategoryGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProductCategoryGroups() throws Exception {
        // Initialize the database
        productCategoryGroupRepository.saveAndFlush(productCategoryGroup);

        // Get all the productCategoryGroupList
        restProductCategoryGroupMockMvc.perform(get("/api/product-category-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productCategoryGroup.getId().intValue())));
    }
    

    @Test
    @Transactional
    public void getProductCategoryGroup() throws Exception {
        // Initialize the database
        productCategoryGroupRepository.saveAndFlush(productCategoryGroup);

        // Get the productCategoryGroup
        restProductCategoryGroupMockMvc.perform(get("/api/product-category-groups/{id}", productCategoryGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productCategoryGroup.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProductCategoryGroup() throws Exception {
        // Get the productCategoryGroup
        restProductCategoryGroupMockMvc.perform(get("/api/product-category-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductCategoryGroup() throws Exception {
        // Initialize the database
        productCategoryGroupRepository.saveAndFlush(productCategoryGroup);

        int databaseSizeBeforeUpdate = productCategoryGroupRepository.findAll().size();

        // Update the productCategoryGroup
        ProductCategoryGroup updatedProductCategoryGroup = productCategoryGroupRepository.findById(productCategoryGroup.getId()).get();
        // Disconnect from session so that the updates on updatedProductCategoryGroup are not directly saved in db
        em.detach(updatedProductCategoryGroup);

        restProductCategoryGroupMockMvc.perform(put("/api/product-category-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductCategoryGroup)))
            .andExpect(status().isOk());

        // Validate the ProductCategoryGroup in the database
        List<ProductCategoryGroup> productCategoryGroupList = productCategoryGroupRepository.findAll();
        assertThat(productCategoryGroupList).hasSize(databaseSizeBeforeUpdate);
        ProductCategoryGroup testProductCategoryGroup = productCategoryGroupList.get(productCategoryGroupList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingProductCategoryGroup() throws Exception {
        int databaseSizeBeforeUpdate = productCategoryGroupRepository.findAll().size();

        // Create the ProductCategoryGroup

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductCategoryGroupMockMvc.perform(put("/api/product-category-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productCategoryGroup)))
            .andExpect(status().isBadRequest());

        // Validate the ProductCategoryGroup in the database
        List<ProductCategoryGroup> productCategoryGroupList = productCategoryGroupRepository.findAll();
        assertThat(productCategoryGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductCategoryGroup() throws Exception {
        // Initialize the database
        productCategoryGroupRepository.saveAndFlush(productCategoryGroup);

        int databaseSizeBeforeDelete = productCategoryGroupRepository.findAll().size();

        // Get the productCategoryGroup
        restProductCategoryGroupMockMvc.perform(delete("/api/product-category-groups/{id}", productCategoryGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductCategoryGroup> productCategoryGroupList = productCategoryGroupRepository.findAll();
        assertThat(productCategoryGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductCategoryGroup.class);
        ProductCategoryGroup productCategoryGroup1 = new ProductCategoryGroup();
        productCategoryGroup1.setId(1L);
        ProductCategoryGroup productCategoryGroup2 = new ProductCategoryGroup();
        productCategoryGroup2.setId(productCategoryGroup1.getId());
        assertThat(productCategoryGroup1).isEqualTo(productCategoryGroup2);
        productCategoryGroup2.setId(2L);
        assertThat(productCategoryGroup1).isNotEqualTo(productCategoryGroup2);
        productCategoryGroup1.setId(null);
        assertThat(productCategoryGroup1).isNotEqualTo(productCategoryGroup2);
    }
}
