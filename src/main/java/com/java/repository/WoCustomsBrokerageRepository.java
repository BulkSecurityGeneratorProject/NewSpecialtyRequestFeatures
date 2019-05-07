package com.java.repository;

import com.java.domain.WoCustomsBrokerage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the WoCustomsBrokerage entity.
 */
@Repository
public interface WoCustomsBrokerageRepository extends JpaRepository<WoCustomsBrokerage, Long> {

    @Query(value = "select distinct woCustomsBrokerage from WoCustomsBrokerage woCustomsBrokerage left join fetch woCustomsBrokerage.woTransportModes",
        countQuery = "select count(distinct woCustomsBrokerage) from WoCustomsBrokerage woCustomsBrokerage")
    Page<WoCustomsBrokerage> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct woCustomsBrokerage from WoCustomsBrokerage woCustomsBrokerage left join fetch woCustomsBrokerage.woTransportModes")
    List<WoCustomsBrokerage> findAllWithEagerRelationships();

    @Query("select woCustomsBrokerage from WoCustomsBrokerage woCustomsBrokerage left join fetch woCustomsBrokerage.woTransportModes where woCustomsBrokerage.id =:id")
    Optional<WoCustomsBrokerage> findOneWithEagerRelationships(@Param("id") Long id);

}
