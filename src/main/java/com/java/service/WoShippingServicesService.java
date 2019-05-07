package com.java.service;

import com.java.service.dto.WoShippingServicesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.domain.WoShippingServices}.
 */
public interface WoShippingServicesService {

    /**
     * Save a woShippingServices.
     *
     * @param woShippingServicesDTO the entity to save.
     * @return the persisted entity.
     */
    WoShippingServicesDTO save(WoShippingServicesDTO woShippingServicesDTO);

    /**
     * Get all the woShippingServices.
     *
     * @return the list of entities.
     */
    List<WoShippingServicesDTO> findAll();


    /**
     * Get the "id" woShippingServices.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoShippingServicesDTO> findOne(Long id);

    /**
     * Delete the "id" woShippingServices.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
