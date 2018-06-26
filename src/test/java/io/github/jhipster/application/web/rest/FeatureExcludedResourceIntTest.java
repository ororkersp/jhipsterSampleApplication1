package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication1App;

import io.github.jhipster.application.domain.FeatureExcluded;
import io.github.jhipster.application.repository.FeatureExcludedRepository;
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
 * Test class for the FeatureExcludedResource REST controller.
 *
 * @see FeatureExcludedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication1App.class)
public class FeatureExcludedResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private FeatureExcludedRepository featureExcludedRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFeatureExcludedMockMvc;

    private FeatureExcluded featureExcluded;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeatureExcludedResource featureExcludedResource = new FeatureExcludedResource(featureExcludedRepository);
        this.restFeatureExcludedMockMvc = MockMvcBuilders.standaloneSetup(featureExcludedResource)
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
    public static FeatureExcluded createEntity(EntityManager em) {
        FeatureExcluded featureExcluded = new FeatureExcluded()
            .name(DEFAULT_NAME);
        return featureExcluded;
    }

    @Before
    public void initTest() {
        featureExcluded = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeatureExcluded() throws Exception {
        int databaseSizeBeforeCreate = featureExcludedRepository.findAll().size();

        // Create the FeatureExcluded
        restFeatureExcludedMockMvc.perform(post("/api/feature-excludeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(featureExcluded)))
            .andExpect(status().isCreated());

        // Validate the FeatureExcluded in the database
        List<FeatureExcluded> featureExcludedList = featureExcludedRepository.findAll();
        assertThat(featureExcludedList).hasSize(databaseSizeBeforeCreate + 1);
        FeatureExcluded testFeatureExcluded = featureExcludedList.get(featureExcludedList.size() - 1);
        assertThat(testFeatureExcluded.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createFeatureExcludedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = featureExcludedRepository.findAll().size();

        // Create the FeatureExcluded with an existing ID
        featureExcluded.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeatureExcludedMockMvc.perform(post("/api/feature-excludeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(featureExcluded)))
            .andExpect(status().isBadRequest());

        // Validate the FeatureExcluded in the database
        List<FeatureExcluded> featureExcludedList = featureExcludedRepository.findAll();
        assertThat(featureExcludedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFeatureExcludeds() throws Exception {
        // Initialize the database
        featureExcludedRepository.saveAndFlush(featureExcluded);

        // Get all the featureExcludedList
        restFeatureExcludedMockMvc.perform(get("/api/feature-excludeds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(featureExcluded.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getFeatureExcluded() throws Exception {
        // Initialize the database
        featureExcludedRepository.saveAndFlush(featureExcluded);

        // Get the featureExcluded
        restFeatureExcludedMockMvc.perform(get("/api/feature-excludeds/{id}", featureExcluded.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(featureExcluded.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFeatureExcluded() throws Exception {
        // Get the featureExcluded
        restFeatureExcludedMockMvc.perform(get("/api/feature-excludeds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeatureExcluded() throws Exception {
        // Initialize the database
        featureExcludedRepository.saveAndFlush(featureExcluded);

        int databaseSizeBeforeUpdate = featureExcludedRepository.findAll().size();

        // Update the featureExcluded
        FeatureExcluded updatedFeatureExcluded = featureExcludedRepository.findById(featureExcluded.getId()).get();
        // Disconnect from session so that the updates on updatedFeatureExcluded are not directly saved in db
        em.detach(updatedFeatureExcluded);
        updatedFeatureExcluded
            .name(UPDATED_NAME);

        restFeatureExcludedMockMvc.perform(put("/api/feature-excludeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFeatureExcluded)))
            .andExpect(status().isOk());

        // Validate the FeatureExcluded in the database
        List<FeatureExcluded> featureExcludedList = featureExcludedRepository.findAll();
        assertThat(featureExcludedList).hasSize(databaseSizeBeforeUpdate);
        FeatureExcluded testFeatureExcluded = featureExcludedList.get(featureExcludedList.size() - 1);
        assertThat(testFeatureExcluded.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFeatureExcluded() throws Exception {
        int databaseSizeBeforeUpdate = featureExcludedRepository.findAll().size();

        // Create the FeatureExcluded

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFeatureExcludedMockMvc.perform(put("/api/feature-excludeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(featureExcluded)))
            .andExpect(status().isBadRequest());

        // Validate the FeatureExcluded in the database
        List<FeatureExcluded> featureExcludedList = featureExcludedRepository.findAll();
        assertThat(featureExcludedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFeatureExcluded() throws Exception {
        // Initialize the database
        featureExcludedRepository.saveAndFlush(featureExcluded);

        int databaseSizeBeforeDelete = featureExcludedRepository.findAll().size();

        // Get the featureExcluded
        restFeatureExcludedMockMvc.perform(delete("/api/feature-excludeds/{id}", featureExcluded.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FeatureExcluded> featureExcludedList = featureExcludedRepository.findAll();
        assertThat(featureExcludedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeatureExcluded.class);
        FeatureExcluded featureExcluded1 = new FeatureExcluded();
        featureExcluded1.setId(1L);
        FeatureExcluded featureExcluded2 = new FeatureExcluded();
        featureExcluded2.setId(featureExcluded1.getId());
        assertThat(featureExcluded1).isEqualTo(featureExcluded2);
        featureExcluded2.setId(2L);
        assertThat(featureExcluded1).isNotEqualTo(featureExcluded2);
        featureExcluded1.setId(null);
        assertThat(featureExcluded1).isNotEqualTo(featureExcluded2);
    }
}
