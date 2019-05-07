package com.java.repository;

import com.java.domain.WoPickPack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the WoPickPack entity.
 */
@Repository
public interface WoPickPackRepository extends JpaRepository<WoPickPack, Long> {

    @Query(value = "select distinct woPickPack from WoPickPack woPickPack left join fetch woPickPack.woAdditionalServices",
        countQuery = "select count(distinct woPickPack) from WoPickPack woPickPack")
    Page<WoPickPack> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct woPickPack from WoPickPack woPickPack left join fetch woPickPack.woAdditionalServices")
    List<WoPickPack> findAllWithEagerRelationships();

    @Query("select woPickPack from WoPickPack woPickPack left join fetch woPickPack.woAdditionalServices where woPickPack.id =:id")
    Optional<WoPickPack> findOneWithEagerRelationships(@Param("id") Long id);

}
