package com.java.service;

import com.java.service.dto.WoShippingInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.domain.WoShippingInfo}.
 */
public interface WoShippingInfoService {

    /**
     * Save a woShippingInfo.
     *
     * @param woShippingInfoDTO the entity to save.
     * @return the persisted entity.
     */
    WoShippingInfoDTO save(WoShippingInfoDTO woShippingInfoDTO);

    /**
     * Get all the woShippingInfos.
     *
     * @return the list of entities.
     */
    List<WoShippingInfoDTO> findAll();
    /**
     * Get all the WoShippingInfoDTO where WoWorkOrder is {@code null}.
     *
     * @return the list of entities.
     */
    List<WoShippingInfoDTO> findAllWhereWoWorkOrderIsNull();

    /**
     * Get all the woShippingInfos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<WoShippingInfoDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" woShippingInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoShippingInfoDTO> findOne(Long id);

    /**
     * Delete the "id" woShippingInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
