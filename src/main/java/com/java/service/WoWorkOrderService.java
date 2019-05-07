package com.java.service;

import com.java.service.dto.WoWorkOrderDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.domain.WoWorkOrder}.
 */
public interface WoWorkOrderService {

    /**
     * Save a woWorkOrder.
     *
     * @param woWorkOrderDTO the entity to save.
     * @return the persisted entity.
     */
    WoWorkOrderDTO save(WoWorkOrderDTO woWorkOrderDTO);

    /**
     * Get all the woWorkOrders.
     *
     * @return the list of entities.
     */
    List<WoWorkOrderDTO> findAll();


    /**
     * Get the "id" woWorkOrder.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoWorkOrderDTO> findOne(Long id);

    /**
     * Delete the "id" woWorkOrder.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
