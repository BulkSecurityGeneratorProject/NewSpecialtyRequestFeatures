package com.java.service.impl;

import com.java.service.WoShippingInfoService;
import com.java.domain.WoShippingInfo;
import com.java.repository.WoShippingInfoRepository;
import com.java.service.dto.WoShippingInfoDTO;
import com.java.service.mapper.WoShippingInfoMapper;
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
 * Service Implementation for managing {@link WoShippingInfo}.
 */
@Service
@Transactional
public class WoShippingInfoServiceImpl implements WoShippingInfoService {

    private final Logger log = LoggerFactory.getLogger(WoShippingInfoServiceImpl.class);

    private final WoShippingInfoRepository woShippingInfoRepository;

    private final WoShippingInfoMapper woShippingInfoMapper;

    public WoShippingInfoServiceImpl(WoShippingInfoRepository woShippingInfoRepository, WoShippingInfoMapper woShippingInfoMapper) {
        this.woShippingInfoRepository = woShippingInfoRepository;
        this.woShippingInfoMapper = woShippingInfoMapper;
    }

    /**
     * Save a woShippingInfo.
     *
     * @param woShippingInfoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoShippingInfoDTO save(WoShippingInfoDTO woShippingInfoDTO) {
        log.debug("Request to save WoShippingInfo : {}", woShippingInfoDTO);
        WoShippingInfo woShippingInfo = woShippingInfoMapper.toEntity(woShippingInfoDTO);
        woShippingInfo = woShippingInfoRepository.save(woShippingInfo);
        return woShippingInfoMapper.toDto(woShippingInfo);
    }

    /**
     * Get all the woShippingInfos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoShippingInfoDTO> findAll() {
        log.debug("Request to get all WoShippingInfos");
        return woShippingInfoRepository.findAllWithEagerRelationships().stream()
            .map(woShippingInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the woShippingInfos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<WoShippingInfoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return woShippingInfoRepository.findAllWithEagerRelationships(pageable).map(woShippingInfoMapper::toDto);
    }
    


    /**
    *  Get all the woShippingInfos where WoWorkOrder is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoShippingInfoDTO> findAllWhereWoWorkOrderIsNull() {
        log.debug("Request to get all woShippingInfos where WoWorkOrder is null");
        return StreamSupport
            .stream(woShippingInfoRepository.findAll().spliterator(), false)
            .filter(woShippingInfo -> woShippingInfo.getWoWorkOrder() == null)
            .map(woShippingInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woShippingInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoShippingInfoDTO> findOne(Long id) {
        log.debug("Request to get WoShippingInfo : {}", id);
        return woShippingInfoRepository.findOneWithEagerRelationships(id)
            .map(woShippingInfoMapper::toDto);
    }

    /**
     * Delete the woShippingInfo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoShippingInfo : {}", id);
        woShippingInfoRepository.deleteById(id);
    }
}
