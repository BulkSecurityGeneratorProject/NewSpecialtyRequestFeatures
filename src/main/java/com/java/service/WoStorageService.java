package com.java.service;

import com.java.service.dto.WoStorageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.domain.WoStorage}.
 */
public interface WoStorageService {

    /**
     * Save a woStorage.
     *
     * @param woStorageDTO the entity to save.
     * @return the persisted entity.
     */
    WoStorageDTO save(WoStorageDTO woStorageDTO);

    /**
     * Get all the woStorages.
     *
     * @return the list of entities.
     */
    List<WoStorageDTO> findAll();
    /**
     * Get all the WoStorageDTO where WoWorkOrder is {@code null}.
     *
     * @return the list of entities.
     */
    List<WoStorageDTO> findAllWhereWoWorkOrderIsNull();


    /**
     * Get the "id" woStorage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoStorageDTO> findOne(Long id);

    /**
     * Delete the "id" woStorage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
