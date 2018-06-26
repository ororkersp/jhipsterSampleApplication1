package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ProductCategoryGroup;
import io.github.jhipster.application.repository.ProductCategoryGroupRepository;
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
 * REST controller for managing ProductCategoryGroup.
 */
@RestController
@RequestMapping("/api")
public class ProductCategoryGroupResource {

    private final Logger log = LoggerFactory.getLogger(ProductCategoryGroupResource.class);

    private static final String ENTITY_NAME = "productCategoryGroup";

    private final ProductCategoryGroupRepository productCategoryGroupRepository;

    public ProductCategoryGroupResource(ProductCategoryGroupRepository productCategoryGroupRepository) {
        this.productCategoryGroupRepository = productCategoryGroupRepository;
    }

    /**
     * POST  /product-category-groups : Create a new productCategoryGroup.
     *
     * @param productCategoryGroup the productCategoryGroup to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productCategoryGroup, or with status 400 (Bad Request) if the productCategoryGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-category-groups")
    @Timed
    public ResponseEntity<ProductCategoryGroup> createProductCategoryGroup(@RequestBody ProductCategoryGroup productCategoryGroup) throws URISyntaxException {
        log.debug("REST request to save ProductCategoryGroup : {}", productCategoryGroup);
        if (productCategoryGroup.getId() != null) {
            throw new BadRequestAlertException("A new productCategoryGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductCategoryGroup result = productCategoryGroupRepository.save(productCategoryGroup);
        return ResponseEntity.created(new URI("/api/product-category-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-category-groups : Updates an existing productCategoryGroup.
     *
     * @param productCategoryGroup the productCategoryGroup to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productCategoryGroup,
     * or with status 400 (Bad Request) if the productCategoryGroup is not valid,
     * or with status 500 (Internal Server Error) if the productCategoryGroup couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-category-groups")
    @Timed
    public ResponseEntity<ProductCategoryGroup> updateProductCategoryGroup(@RequestBody ProductCategoryGroup productCategoryGroup) throws URISyntaxException {
        log.debug("REST request to update ProductCategoryGroup : {}", productCategoryGroup);
        if (productCategoryGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductCategoryGroup result = productCategoryGroupRepository.save(productCategoryGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productCategoryGroup.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-category-groups : get all the productCategoryGroups.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productCategoryGroups in body
     */
    @GetMapping("/product-category-groups")
    @Timed
    public List<ProductCategoryGroup> getAllProductCategoryGroups() {
        log.debug("REST request to get all ProductCategoryGroups");
        return productCategoryGroupRepository.findAll();
    }

    /**
     * GET  /product-category-groups/:id : get the "id" productCategoryGroup.
     *
     * @param id the id of the productCategoryGroup to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productCategoryGroup, or with status 404 (Not Found)
     */
    @GetMapping("/product-category-groups/{id}")
    @Timed
    public ResponseEntity<ProductCategoryGroup> getProductCategoryGroup(@PathVariable Long id) {
        log.debug("REST request to get ProductCategoryGroup : {}", id);
        Optional<ProductCategoryGroup> productCategoryGroup = productCategoryGroupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(productCategoryGroup);
    }

    /**
     * DELETE  /product-category-groups/:id : delete the "id" productCategoryGroup.
     *
     * @param id the id of the productCategoryGroup to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-category-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductCategoryGroup(@PathVariable Long id) {
        log.debug("REST request to delete ProductCategoryGroup : {}", id);

        productCategoryGroupRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
