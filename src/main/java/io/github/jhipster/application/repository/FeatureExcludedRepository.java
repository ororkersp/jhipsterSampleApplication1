package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FeatureExcluded;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FeatureExcluded entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeatureExcludedRepository extends JpaRepository<FeatureExcluded, Long> {

}
