package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FeatureIncluded;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FeatureIncluded entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeatureIncludedRepository extends JpaRepository<FeatureIncluded, Long> {

}
