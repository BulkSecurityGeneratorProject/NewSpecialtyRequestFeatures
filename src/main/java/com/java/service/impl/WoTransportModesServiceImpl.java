package com.java.service.impl;

import com.java.service.WoTransportModesService;
import com.java.domain.WoTransportModes;
import com.java.repository.WoTransportModesRepository;
import com.java.service.dto.WoTransportModesDTO;
import com.java.service.mapper.WoTransportModesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WoTransportModes}.
 */
@Service
@Transactional
public class WoTransportModesServiceImpl implements WoTransportModesService {

    private final Logger log = LoggerFactory.getLogger(WoTransportModesServiceImpl.class);

    private final WoTransportModesRepository woTransportModesRepository;

    private final WoTransportModesMapper woTransportModesMapper;

    public WoTransportModesServiceImpl(WoTransportModesRepository woTransportModesRepository, WoTransportModesMapper woTransportModesMapper) {
        this.woTransportModesRepository = woTransportModesRepository;
        this.woTransportModesMapper = woTransportModesMapper;
    }

    /**
     * Save a woTransportModes.
     *
     * @param woTransportModesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoTransportModesDTO save(WoTransportModesDTO woTransportModesDTO) {
        log.debug("Request to save WoTransportModes : {}", woTransportModesDTO);
        WoTransportModes woTransportModes = woTransportModesMapper.toEntity(woTransportModesDTO);
        woTransportModes = woTransportModesRepository.save(woTransportModes);
        return woTransportModesMapper.toDto(woTransportModes);
    }

    /**
     * Get all the woTransportModes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoTransportModesDTO> findAll() {
        log.debug("Request to get all WoTransportModes");
        return woTransportModesRepository.findAll().stream()
            .map(woTransportModesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one woTransportModes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoTransportModesDTO> findOne(Long id) {
        log.debug("Request to get WoTransportModes : {}", id);
        return woTransportModesRepository.findById(id)
            .map(woTransportModesMapper::toDto);
    }

    /**
     * Delete the woTransportModes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoTransportModes : {}", id);
        woTransportModesRepository.deleteById(id);
    }
}
