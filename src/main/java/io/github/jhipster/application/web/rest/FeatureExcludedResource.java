package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.FeatureExcluded;
import io.github.jhipster.application.repository.FeatureExcludedRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FeatureExcluded.
 */
@RestController
@RequestMapping("/api")
public class FeatureExcludedResource {

    private final Logger log = LoggerFactory.getLogger(FeatureExcludedResource.class);

    private static final String ENTITY_NAME = "featureExcluded";

    private final FeatureExcludedRepository featureExcludedRepository;

    public FeatureExcludedResource(FeatureExcludedRepository featureExcludedRepository) {
        this.featureExcludedRepository = featureExcludedRepository;
    }

    /**
     * POST  /feature-excludeds : Create a new featureExcluded.
     *
     * @param featureExcluded the featureExcluded to create
     * @return the ResponseEntity with status 201 (Created) and with body the new featureExcluded, or with status 400 (Bad Request) if the featureExcluded has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/feature-excludeds")
    @Timed
    public ResponseEntity<FeatureExcluded> createFeatureExcluded(@RequestBody FeatureExcluded featureExcluded) throws URISyntaxException {
        log.debug("REST request to save FeatureExcluded : {}", featureExcluded);
        if (featureExcluded.getId() != null) {
            throw new BadRequestAlertException("A new featureExcluded cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeatureExcluded result = featureExcludedRepository.save(featureExcluded);
        return ResponseEntity.created(new URI("/api/feature-excludeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /feature-excludeds : Updates an existing featureExcluded.
     *
     * @param featureExcluded the featureExcluded to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated featureExcluded,
     * or with status 400 (Bad Request) if the featureExcluded is not valid,
     * or with status 500 (Internal Server Error) if the featureExcluded couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/feature-excludeds")
    @Timed
    public ResponseEntity<FeatureExcluded> updateFeatureExcluded(@RequestBody FeatureExcluded featureExcluded) throws URISyntaxException {
        log.debug("REST request to update FeatureExcluded : {}", featureExcluded);
        if (featureExcluded.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeatureExcluded result = featureExcludedRepository.save(featureExcluded);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, featureExcluded.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feature-excludeds : get all the featureExcludeds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of featureExcludeds in body
     */
    @GetMapping("/feature-excludeds")
    @Timed
    public List<FeatureExcluded> getAllFeatureExcludeds() {
        log.debug("REST request to get all FeatureExcludeds");
        return featureExcludedRepository.findAll();
    }

    /**
     * GET  /feature-excludeds/:id : get the "id" featureExcluded.
     *
     * @param id the id of the featureExcluded to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the featureExcluded, or with status 404 (Not Found)
     */
    @GetMapping("/feature-excludeds/{id}")
    @Timed
    public ResponseEntity<FeatureExcluded> getFeatureExcluded(@PathVariable Long id) {
        log.debug("REST request to get FeatureExcluded : {}", id);
        Optional<FeatureExcluded> featureExcluded = featureExcludedRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(featureExcluded);
    }

    /**
     * DELETE  /feature-excludeds/:id : delete the "id" featureExcluded.
     *
     * @param id the id of the featureExcluded to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/feature-excludeds/{id}")
    @Timed
    public ResponseEntity<Void> deleteFeatureExcluded(@PathVariable Long id) {
        log.debug("REST request to delete FeatureExcluded : {}", id);

        featureExcludedRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
