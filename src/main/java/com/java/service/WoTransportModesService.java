package com.java.service;

import com.java.service.dto.WoTransportModesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.domain.WoTransportModes}.
 */
public interface WoTransportModesService {

    /**
     * Save a woTransportModes.
     *
     * @param woTransportModesDTO the entity to save.
     * @return the persisted entity.
     */
    WoTransportModesDTO save(WoTransportModesDTO woTransportModesDTO);

    /**
     * Get all the woTransportModes.
     *
     * @return the list of entities.
     */
    List<WoTransportModesDTO> findAll();


    /**
     * Get the "id" woTransportModes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoTransportModesDTO> findOne(Long id);

    /**
     * Delete the "id" woTransportModes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
