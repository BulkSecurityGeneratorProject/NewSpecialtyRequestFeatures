package com.java.service;

import com.java.service.dto.WoPickPackDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.domain.WoPickPack}.
 */
public interface WoPickPackService {

    /**
     * Save a woPickPack.
     *
     * @param woPickPackDTO the entity to save.
     * @return the persisted entity.
     */
    WoPickPackDTO save(WoPickPackDTO woPickPackDTO);

    /**
     * Get all the woPickPacks.
     *
     * @return the list of entities.
     */
    List<WoPickPackDTO> findAll();
    /**
     * Get all the WoPickPackDTO where WoWorkOrder is {@code null}.
     *
     * @return the list of entities.
     */
    List<WoPickPackDTO> findAllWhereWoWorkOrderIsNull();

    /**
     * Get all the woPickPacks with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<WoPickPackDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" woPickPack.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoPickPackDTO> findOne(Long id);

    /**
     * Delete the "id" woPickPack.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
