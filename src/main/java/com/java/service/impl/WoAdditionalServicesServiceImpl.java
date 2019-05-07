package com.java.service.impl;

import com.java.service.WoAdditionalServicesService;
import com.java.domain.WoAdditionalServices;
import com.java.repository.WoAdditionalServicesRepository;
import com.java.service.dto.WoAdditionalServicesDTO;
import com.java.service.mapper.WoAdditionalServicesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WoAdditionalServices}.
 */
@Service
@Transactional
public class WoAdditionalServicesServiceImpl implements WoAdditionalServicesService {

    private final Logger log = LoggerFactory.getLogger(WoAdditionalServicesServiceImpl.class);

    private final WoAdditionalServicesRepository woAdditionalServicesRepository;

    private final WoAdditionalServicesMapper woAdditionalServicesMapper;

    public WoAdditionalServicesServiceImpl(WoAdditionalServicesRepository woAdditionalServicesRepository, WoAdditionalServicesMapper woAdditionalServicesMapper) {
        this.woAdditionalServicesRepository = woAdditionalServicesRepository;
        this.woAdditionalServicesMapper = woAdditionalServicesMapper;
    }

    /**
     * Save a woAdditionalServices.
     *
     * @param woAdditionalServicesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoAdditionalServicesDTO save(WoAdditionalServicesDTO woAdditionalServicesDTO) {
        log.debug("Request to save WoAdditionalServices : {}", woAdditionalServicesDTO);
        WoAdditionalServices woAdditionalServices = woAdditionalServicesMapper.toEntity(woAdditionalServicesDTO);
        woAdditionalServices = woAdditionalServicesRepository.save(woAdditionalServices);
        return woAdditionalServicesMapper.toDto(woAdditionalServices);
    }

    /**
     * Get all the woAdditionalServices.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoAdditionalServicesDTO> findAll() {
        log.debug("Request to get all WoAdditionalServices");
        return woAdditionalServicesRepository.findAll().stream()
            .map(woAdditionalServicesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one woAdditionalServices by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoAdditionalServicesDTO> findOne(Long id) {
        log.debug("Request to get WoAdditionalServices : {}", id);
        return woAdditionalServicesRepository.findById(id)
            .map(woAdditionalServicesMapper::toDto);
    }

    /**
     * Delete the woAdditionalServices by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoAdditionalServices : {}", id);
        woAdditionalServicesRepository.deleteById(id);
    }
}
