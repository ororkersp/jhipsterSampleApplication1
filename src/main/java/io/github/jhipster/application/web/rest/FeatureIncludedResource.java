package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.FeatureIncluded;
import io.github.jhipster.application.repository.FeatureIncludedRepository;
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
 * REST controller for managing FeatureIncluded.
 */
@RestController
@RequestMapping("/api")
public class FeatureIncludedResource {

    private final Logger log = LoggerFactory.getLogger(FeatureIncludedResource.class);

    private static final String ENTITY_NAME = "featureIncluded";

    private final FeatureIncludedRepository featureIncludedRepository;

    public FeatureIncludedResource(FeatureIncludedRepository featureIncludedRepository) {
        this.featureIncludedRepository = featureIncludedRepository;
    }

    /**
     * POST  /feature-includeds : Create a new featureIncluded.
     *
     * @param featureIncluded the featureIncluded to create
     * @return the ResponseEntity with status 201 (Created) and with body the new featureIncluded, or with status 400 (Bad Request) if the featureIncluded has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/feature-includeds")
    @Timed
    public ResponseEntity<FeatureIncluded> createFeatureIncluded(@RequestBody FeatureIncluded featureIncluded) throws URISyntaxException {
        log.debug("REST request to save FeatureIncluded : {}", featureIncluded);
        if (featureIncluded.getId() != null) {
            throw new BadRequestAlertException("A new featureIncluded cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeatureIncluded result = featureIncludedRepository.save(featureIncluded);
        return ResponseEntity.created(new URI("/api/feature-includeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /feature-includeds : Updates an existing featureIncluded.
     *
     * @param featureIncluded the featureIncluded to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated featureIncluded,
     * or with status 400 (Bad Request) if the featureIncluded is not valid,
     * or with status 500 (Internal Server Error) if the featureIncluded couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/feature-includeds")
    @Timed
    public ResponseEntity<FeatureIncluded> updateFeatureIncluded(@RequestBody FeatureIncluded featureIncluded) throws URISyntaxException {
        log.debug("REST request to update FeatureIncluded : {}", featureIncluded);
        if (featureIncluded.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeatureIncluded result = featureIncludedRepository.save(featureIncluded);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, featureIncluded.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feature-includeds : get all the featureIncludeds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of featureIncludeds in body
     */
    @GetMapping("/feature-includeds")
    @Timed
    public List<FeatureIncluded> getAllFeatureIncludeds() {
        log.debug("REST request to get all FeatureIncludeds");
        return featureIncludedRepository.findAll();
    }

    /**
     * GET  /feature-includeds/:id : get the "id" featureIncluded.
     *
     * @param id the id of the featureIncluded to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the featureIncluded, or with status 404 (Not Found)
     */
    @GetMapping("/feature-includeds/{id}")
    @Timed
    public ResponseEntity<FeatureIncluded> getFeatureIncluded(@PathVariable Long id) {
        log.debug("REST request to get FeatureIncluded : {}", id);
        Optional<FeatureIncluded> featureIncluded = featureIncludedRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(featureIncluded);
    }

    /**
     * DELETE  /feature-includeds/:id : delete the "id" featureIncluded.
     *
     * @param id the id of the featureIncluded to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/feature-includeds/{id}")
    @Timed
    public ResponseEntity<Void> deleteFeatureIncluded(@PathVariable Long id) {
        log.debug("REST request to delete FeatureIncluded : {}", id);

        featureIncludedRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
