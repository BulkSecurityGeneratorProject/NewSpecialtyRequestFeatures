package com.java.service;

import com.java.service.dto.WoAdditionalServicesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.domain.WoAdditionalServices}.
 */
public interface WoAdditionalServicesService {

    /**
     * Save a woAdditionalServices.
     *
     * @param woAdditionalServicesDTO the entity to save.
     * @return the persisted entity.
     */
    WoAdditionalServicesDTO save(WoAdditionalServicesDTO woAdditionalServicesDTO);

    /**
     * Get all the woAdditionalServices.
     *
     * @return the list of entities.
     */
    List<WoAdditionalServicesDTO> findAll();


    /**
     * Get the "id" woAdditionalServices.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoAdditionalServicesDTO> findOne(Long id);

    /**
     * Delete the "id" woAdditionalServices.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
