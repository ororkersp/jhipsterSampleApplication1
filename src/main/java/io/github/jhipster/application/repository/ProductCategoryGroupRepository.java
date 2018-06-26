package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ProductCategoryGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductCategoryGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductCategoryGroupRepository extends JpaRepository<ProductCategoryGroup, Long> {

}
