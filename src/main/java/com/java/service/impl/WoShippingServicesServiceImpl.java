package com.java.service.impl;

import com.java.service.WoShippingServicesService;
import com.java.domain.WoShippingServices;
import com.java.repository.WoShippingServicesRepository;
import com.java.service.dto.WoShippingServicesDTO;
import com.java.service.mapper.WoShippingServicesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WoShippingServices}.
 */
@Service
@Transactional
public class WoShippingServicesServiceImpl implements WoShippingServicesService {

    private final Logger log = LoggerFactory.getLogger(WoShippingServicesServiceImpl.class);

    private final WoShippingServicesRepository woShippingServicesRepository;

    private final WoShippingServicesMapper woShippingServicesMapper;

    public WoShippingServicesServiceImpl(WoShippingServicesRepository woShippingServicesRepository, WoShippingServicesMapper woShippingServicesMapper) {
        this.woShippingServicesRepository = woShippingServicesRepository;
        this.woShippingServicesMapper = woShippingServicesMapper;
    }

    /**
     * Save a woShippingServices.
     *
     * @param woShippingServicesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoShippingServicesDTO save(WoShippingServicesDTO woShippingServicesDTO) {
        log.debug("Request to save WoShippingServices : {}", woShippingServicesDTO);
        WoShippingServices woShippingServices = woShippingServicesMapper.toEntity(woShippingServicesDTO);
        woShippingServices = woShippingServicesRepository.save(woShippingServices);
        return woShippingServicesMapper.toDto(woShippingServices);
    }

    /**
     * Get all the woShippingServices.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoShippingServicesDTO> findAll() {
        log.debug("Request to get all WoShippingServices");
        return woShippingServicesRepository.findAll().stream()
            .map(woShippingServicesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one woShippingServices by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoShippingServicesDTO> findOne(Long id) {
        log.debug("Request to get WoShippingServices : {}", id);
        return woShippingServicesRepository.findById(id)
            .map(woShippingServicesMapper::toDto);
    }

    /**
     * Delete the woShippingServices by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoShippingServices : {}", id);
        woShippingServicesRepository.deleteById(id);
    }
}
