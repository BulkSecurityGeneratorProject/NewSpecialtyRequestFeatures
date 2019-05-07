package com.java.service.impl;

import com.java.service.WoPackageTypeService;
import com.java.domain.WoPackageType;
import com.java.repository.WoPackageTypeRepository;
import com.java.service.dto.WoPackageTypeDTO;
import com.java.service.mapper.WoPackageTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link WoPackageType}.
 */
@Service
@Transactional
public class WoPackageTypeServiceImpl implements WoPackageTypeService {

    private final Logger log = LoggerFactory.getLogger(WoPackageTypeServiceImpl.class);

    private final WoPackageTypeRepository woPackageTypeRepository;

    private final WoPackageTypeMapper woPackageTypeMapper;

    public WoPackageTypeServiceImpl(WoPackageTypeRepository woPackageTypeRepository, WoPackageTypeMapper woPackageTypeMapper) {
        this.woPackageTypeRepository = woPackageTypeRepository;
        this.woPackageTypeMapper = woPackageTypeMapper;
    }

    /**
     * Save a woPackageType.
     *
     * @param woPackageTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoPackageTypeDTO save(WoPackageTypeDTO woPackageTypeDTO) {
        log.debug("Request to save WoPackageType : {}", woPackageTypeDTO);
        WoPackageType woPackageType = woPackageTypeMapper.toEntity(woPackageTypeDTO);
        woPackageType = woPackageTypeRepository.save(woPackageType);
        return woPackageTypeMapper.toDto(woPackageType);
    }

    /**
     * Get all the woPackageTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoPackageTypeDTO> findAll() {
        log.debug("Request to get all WoPackageTypes");
        return woPackageTypeRepository.findAll().stream()
            .map(woPackageTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the woPackageTypes where WoStorage is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoPackageTypeDTO> findAllWhereWoStorageIsNull() {
        log.debug("Request to get all woPackageTypes where WoStorage is null");
        return StreamSupport
            .stream(woPackageTypeRepository.findAll().spliterator(), false)
            .filter(woPackageType -> woPackageType.getWoStorage() == null)
            .map(woPackageTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woPackageType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoPackageTypeDTO> findOne(Long id) {
        log.debug("Request to get WoPackageType : {}", id);
        return woPackageTypeRepository.findById(id)
            .map(woPackageTypeMapper::toDto);
    }

    /**
     * Delete the woPackageType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoPackageType : {}", id);
        woPackageTypeRepository.deleteById(id);
    }
}
