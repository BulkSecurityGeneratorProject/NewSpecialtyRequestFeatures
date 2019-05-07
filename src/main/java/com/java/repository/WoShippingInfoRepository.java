package com.java.repository;

import com.java.domain.WoShippingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the WoShippingInfo entity.
 */
@Repository
public interface WoShippingInfoRepository extends JpaRepository<WoShippingInfo, Long> {

    @Query(value = "select distinct woShippingInfo from WoShippingInfo woShippingInfo left join fetch woShippingInfo.woShippingServices",
        countQuery = "select count(distinct woShippingInfo) from WoShippingInfo woShippingInfo")
    Page<WoShippingInfo> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct woShippingInfo from WoShippingInfo woShippingInfo left join fetch woShippingInfo.woShippingServices")
    List<WoShippingInfo> findAllWithEagerRelationships();

    @Query("select woShippingInfo from WoShippingInfo woShippingInfo left join fetch woShippingInfo.woShippingServices where woShippingInfo.id =:id")
    Optional<WoShippingInfo> findOneWithEagerRelationships(@Param("id") Long id);

}
