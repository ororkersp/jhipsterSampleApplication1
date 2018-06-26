package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication1App;

import io.github.jhipster.application.domain.FeatureIncluded;
import io.github.jhipster.application.repository.FeatureIncludedRepository;
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
 * Test class for the FeatureIncludedResource REST controller.
 *
 * @see FeatureIncludedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication1App.class)
public class FeatureIncludedResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAVEAT = "AAAAAAAAAA";
    private static final String UPDATED_CAVEAT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HIGHLIGHTED = false;
    private static final Boolean UPDATED_HIGHLIGHTED = true;

    @Autowired
    private FeatureIncludedRepository featureIncludedRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFeatureIncludedMockMvc;

    private FeatureIncluded featureIncluded;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeatureIncludedResource featureIncludedResource = new FeatureIncludedResource(featureIncludedRepository);
        this.restFeatureIncludedMockMvc = MockMvcBuilders.standaloneSetup(featureIncludedResource)
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
    public static FeatureIncluded createEntity(EntityManager em) {
        FeatureIncluded featureIncluded = new FeatureIncluded()
            .name(DEFAULT_NAME)
            .caveat(DEFAULT_CAVEAT)
            .highlighted(DEFAULT_HIGHLIGHTED);
        return featureIncluded;
    }

    @Before
    public void initTest() {
        featureIncluded = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeatureIncluded() throws Exception {
        int databaseSizeBeforeCreate = featureIncludedRepository.findAll().size();

        // Create the FeatureIncluded
        restFeatureIncludedMockMvc.perform(post("/api/feature-includeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(featureIncluded)))
            .andExpect(status().isCreated());

        // Validate the FeatureIncluded in the database
        List<FeatureIncluded> featureIncludedList = featureIncludedRepository.findAll();
        assertThat(featureIncludedList).hasSize(databaseSizeBeforeCreate + 1);
        FeatureIncluded testFeatureIncluded = featureIncludedList.get(featureIncludedList.size() - 1);
        assertThat(testFeatureIncluded.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFeatureIncluded.getCaveat()).isEqualTo(DEFAULT_CAVEAT);
        assertThat(testFeatureIncluded.isHighlighted()).isEqualTo(DEFAULT_HIGHLIGHTED);
    }

    @Test
    @Transactional
    public void createFeatureIncludedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = featureIncludedRepository.findAll().size();

        // Create the FeatureIncluded with an existing ID
        featureIncluded.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeatureIncludedMockMvc.perform(post("/api/feature-includeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(featureIncluded)))
            .andExpect(status().isBadRequest());

        // Validate the FeatureIncluded in the database
        List<FeatureIncluded> featureIncludedList = featureIncludedRepository.findAll();
        assertThat(featureIncludedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFeatureIncludeds() throws Exception {
        // Initialize the database
        featureIncludedRepository.saveAndFlush(featureIncluded);

        // Get all the featureIncludedList
        restFeatureIncludedMockMvc.perform(get("/api/feature-includeds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(featureIncluded.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].caveat").value(hasItem(DEFAULT_CAVEAT.toString())))
            .andExpect(jsonPath("$.[*].highlighted").value(hasItem(DEFAULT_HIGHLIGHTED.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getFeatureIncluded() throws Exception {
        // Initialize the database
        featureIncludedRepository.saveAndFlush(featureIncluded);

        // Get the featureIncluded
        restFeatureIncludedMockMvc.perform(get("/api/feature-includeds/{id}", featureIncluded.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(featureIncluded.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.caveat").value(DEFAULT_CAVEAT.toString()))
            .andExpect(jsonPath("$.highlighted").value(DEFAULT_HIGHLIGHTED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFeatureIncluded() throws Exception {
        // Get the featureIncluded
        restFeatureIncludedMockMvc.perform(get("/api/feature-includeds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeatureIncluded() throws Exception {
        // Initialize the database
        featureIncludedRepository.saveAndFlush(featureIncluded);

        int databaseSizeBeforeUpdate = featureIncludedRepository.findAll().size();

        // Update the featureIncluded
        FeatureIncluded updatedFeatureIncluded = featureIncludedRepository.findById(featureIncluded.getId()).get();
        // Disconnect from session so that the updates on updatedFeatureIncluded are not directly saved in db
        em.detach(updatedFeatureIncluded);
        updatedFeatureIncluded
            .name(UPDATED_NAME)
            .caveat(UPDATED_CAVEAT)
            .highlighted(UPDATED_HIGHLIGHTED);

        restFeatureIncludedMockMvc.perform(put("/api/feature-includeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFeatureIncluded)))
            .andExpect(status().isOk());

        // Validate the FeatureIncluded in the database
        List<FeatureIncluded> featureIncludedList = featureIncludedRepository.findAll();
        assertThat(featureIncludedList).hasSize(databaseSizeBeforeUpdate);
        FeatureIncluded testFeatureIncluded = featureIncludedList.get(featureIncludedList.size() - 1);
        assertThat(testFeatureIncluded.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFeatureIncluded.getCaveat()).isEqualTo(UPDATED_CAVEAT);
        assertThat(testFeatureIncluded.isHighlighted()).isEqualTo(UPDATED_HIGHLIGHTED);
    }

    @Test
    @Transactional
    public void updateNonExistingFeatureIncluded() throws Exception {
        int databaseSizeBeforeUpdate = featureIncludedRepository.findAll().size();

        // Create the FeatureIncluded

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFeatureIncludedMockMvc.perform(put("/api/feature-includeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(featureIncluded)))
            .andExpect(status().isBadRequest());

        // Validate the FeatureIncluded in the database
        List<FeatureIncluded> featureIncludedList = featureIncludedRepository.findAll();
        assertThat(featureIncludedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFeatureIncluded() throws Exception {
        // Initialize the database
        featureIncludedRepository.saveAndFlush(featureIncluded);

        int databaseSizeBeforeDelete = featureIncludedRepository.findAll().size();

        // Get the featureIncluded
        restFeatureIncludedMockMvc.perform(delete("/api/feature-includeds/{id}", featureIncluded.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FeatureIncluded> featureIncludedList = featureIncludedRepository.findAll();
        assertThat(featureIncludedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeatureIncluded.class);
        FeatureIncluded featureIncluded1 = new FeatureIncluded();
        featureIncluded1.setId(1L);
        FeatureIncluded featureIncluded2 = new FeatureIncluded();
        featureIncluded2.setId(featureIncluded1.getId());
        assertThat(featureIncluded1).isEqualTo(featureIncluded2);
        featureIncluded2.setId(2L);
        assertThat(featureIncluded1).isNotEqualTo(featureIncluded2);
        featureIncluded1.setId(null);
        assertThat(featureIncluded1).isNotEqualTo(featureIncluded2);
    }
}
