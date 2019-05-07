package com.java.service.impl;

import com.java.service.WoPickPackService;
import com.java.domain.WoPickPack;
import com.java.repository.WoPickPackRepository;
import com.java.service.dto.WoPickPackDTO;
import com.java.service.mapper.WoPickPackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link WoPickPack}.
 */
@Service
@Transactional
public class WoPickPackServiceImpl implements WoPickPackService {

    private final Logger log = LoggerFactory.getLogger(WoPickPackServiceImpl.class);

    private final WoPickPackRepository woPickPackRepository;

    private final WoPickPackMapper woPickPackMapper;

    public WoPickPackServiceImpl(WoPickPackRepository woPickPackRepository, WoPickPackMapper woPickPackMapper) {
        this.woPickPackRepository = woPickPackRepository;
        this.woPickPackMapper = woPickPackMapper;
    }

    /**
     * Save a woPickPack.
     *
     * @param woPickPackDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoPickPackDTO save(WoPickPackDTO woPickPackDTO) {
        log.debug("Request to save WoPickPack : {}", woPickPackDTO);
        WoPickPack woPickPack = woPickPackMapper.toEntity(woPickPackDTO);
        woPickPack = woPickPackRepository.save(woPickPack);
        return woPickPackMapper.toDto(woPickPack);
    }

    /**
     * Get all the woPickPacks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoPickPackDTO> findAll() {
        log.debug("Request to get all WoPickPacks");
        return woPickPackRepository.findAllWithEagerRelationships().stream()
            .map(woPickPackMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the woPickPacks with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<WoPickPackDTO> findAllWithEagerRelationships(Pageable pageable) {
        return woPickPackRepository.findAllWithEagerRelationships(pageable).map(woPickPackMapper::toDto);
    }
    


    /**
    *  Get all the woPickPacks where WoWorkOrder is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoPickPackDTO> findAllWhereWoWorkOrderIsNull() {
        log.debug("Request to get all woPickPacks where WoWorkOrder is null");
        return StreamSupport
            .stream(woPickPackRepository.findAll().spliterator(), false)
            .filter(woPickPack -> woPickPack.getWoWorkOrder() == null)
            .map(woPickPackMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woPickPack by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoPickPackDTO> findOne(Long id) {
        log.debug("Request to get WoPickPack : {}", id);
        return woPickPackRepository.findOneWithEagerRelationships(id)
            .map(woPickPackMapper::toDto);
    }

    /**
     * Delete the woPickPack by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoPickPack : {}", id);
        woPickPackRepository.deleteById(id);
    }
}
