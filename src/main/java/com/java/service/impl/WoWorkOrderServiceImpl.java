package com.java.service.impl;

import com.java.service.WoWorkOrderService;
import com.java.domain.WoWorkOrder;
import com.java.repository.WoWorkOrderRepository;
import com.java.service.dto.WoWorkOrderDTO;
import com.java.service.mapper.WoWorkOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WoWorkOrder}.
 */
@Service
@Transactional
public class WoWorkOrderServiceImpl implements WoWorkOrderService {

    private final Logger log = LoggerFactory.getLogger(WoWorkOrderServiceImpl.class);

    private final WoWorkOrderRepository woWorkOrderRepository;

    private final WoWorkOrderMapper woWorkOrderMapper;

    public WoWorkOrderServiceImpl(WoWorkOrderRepository woWorkOrderRepository, WoWorkOrderMapper woWorkOrderMapper) {
        this.woWorkOrderRepository = woWorkOrderRepository;
        this.woWorkOrderMapper = woWorkOrderMapper;
    }

    /**
     * Save a woWorkOrder.
     *
     * @param woWorkOrderDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoWorkOrderDTO save(WoWorkOrderDTO woWorkOrderDTO) {
        log.debug("Request to save WoWorkOrder : {}", woWorkOrderDTO);
        WoWorkOrder woWorkOrder = woWorkOrderMapper.toEntity(woWorkOrderDTO);
        woWorkOrder = woWorkOrderRepository.save(woWorkOrder);
        return woWorkOrderMapper.toDto(woWorkOrder);
    }

    /**
     * Get all the woWorkOrders.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoWorkOrderDTO> findAll() {
        log.debug("Request to get all WoWorkOrders");
        return woWorkOrderRepository.findAll().stream()
            .map(woWorkOrderMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one woWorkOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoWorkOrderDTO> findOne(Long id) {
        log.debug("Request to get WoWorkOrder : {}", id);
        return woWorkOrderRepository.findById(id)
            .map(woWorkOrderMapper::toDto);
    }

    /**
     * Delete the woWorkOrder by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoWorkOrder : {}", id);
        woWorkOrderRepository.deleteById(id);
    }
}
