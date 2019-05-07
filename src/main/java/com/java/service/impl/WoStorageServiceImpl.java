package com.java.service.impl;

import com.java.service.WoStorageService;
import com.java.domain.WoStorage;
import com.java.repository.WoStorageRepository;
import com.java.service.dto.WoStorageDTO;
import com.java.service.mapper.WoStorageMapper;
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
 * Service Implementation for managing {@link WoStorage}.
 */
@Service
@Transactional
public class WoStorageServiceImpl implements WoStorageService {

    private final Logger log = LoggerFactory.getLogger(WoStorageServiceImpl.class);

    private final WoStorageRepository woStorageRepository;

    private final WoStorageMapper woStorageMapper;

    public WoStorageServiceImpl(WoStorageRepository woStorageRepository, WoStorageMapper woStorageMapper) {
        this.woStorageRepository = woStorageRepository;
        this.woStorageMapper = woStorageMapper;
    }

    /**
     * Save a woStorage.
     *
     * @param woStorageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoStorageDTO save(WoStorageDTO woStorageDTO) {
        log.debug("Request to save WoStorage : {}", woStorageDTO);
        WoStorage woStorage = woStorageMapper.toEntity(woStorageDTO);
        woStorage = woStorageRepository.save(woStorage);
        return woStorageMapper.toDto(woStorage);
    }

    /**
     * Get all the woStorages.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoStorageDTO> findAll() {
        log.debug("Request to get all WoStorages");
        return woStorageRepository.findAll().stream()
            .map(woStorageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the woStorages where WoWorkOrder is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoStorageDTO> findAllWhereWoWorkOrderIsNull() {
        log.debug("Request to get all woStorages where WoWorkOrder is null");
        return StreamSupport
            .stream(woStorageRepository.findAll().spliterator(), false)
            .filter(woStorage -> woStorage.getWoWorkOrder() == null)
            .map(woStorageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woStorage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoStorageDTO> findOne(Long id) {
        log.debug("Request to get WoStorage : {}", id);
        return woStorageRepository.findById(id)
            .map(woStorageMapper::toDto);
    }

    /**
     * Delete the woStorage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoStorage : {}", id);
        woStorageRepository.deleteById(id);
    }
}
