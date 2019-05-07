package com.java.service;

import com.java.service.dto.WoPackageTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.domain.WoPackageType}.
 */
public interface WoPackageTypeService {

    /**
     * Save a woPackageType.
     *
     * @param woPackageTypeDTO the entity to save.
     * @return the persisted entity.
     */
    WoPackageTypeDTO save(WoPackageTypeDTO woPackageTypeDTO);

    /**
     * Get all the woPackageTypes.
     *
     * @return the list of entities.
     */
    List<WoPackageTypeDTO> findAll();
    /**
     * Get all the WoPackageTypeDTO where WoStorage is {@code null}.
     *
     * @return the list of entities.
     */
    List<WoPackageTypeDTO> findAllWhereWoStorageIsNull();


    /**
     * Get the "id" woPackageType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoPackageTypeDTO> findOne(Long id);

    /**
     * Delete the "id" woPackageType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
