package com.java.web.rest;

import com.java.service.WoShippingInfoService;
import com.java.web.rest.errors.BadRequestAlertException;
import com.java.service.dto.WoShippingInfoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.java.domain.WoShippingInfo}.
 */
@RestController
@RequestMapping("/api")
public class WoShippingInfoResource {

    private final Logger log = LoggerFactory.getLogger(WoShippingInfoResource.class);

    private static final String ENTITY_NAME = "woShippingInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoShippingInfoService woShippingInfoService;

    public WoShippingInfoResource(WoShippingInfoService woShippingInfoService) {
        this.woShippingInfoService = woShippingInfoService;
    }

    /**
     * {@code POST  /wo-shipping-infos} : Create a new woShippingInfo.
     *
     * @param woShippingInfoDTO the woShippingInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woShippingInfoDTO, or with status {@code 400 (Bad Request)} if the woShippingInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-shipping-infos")
    public ResponseEntity<WoShippingInfoDTO> createWoShippingInfo(@Valid @RequestBody WoShippingInfoDTO woShippingInfoDTO) throws URISyntaxException {
        log.debug("REST request to save WoShippingInfo : {}", woShippingInfoDTO);
        if (woShippingInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new woShippingInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoShippingInfoDTO result = woShippingInfoService.save(woShippingInfoDTO);
        return ResponseEntity.created(new URI("/api/wo-shipping-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-shipping-infos} : Updates an existing woShippingInfo.
     *
     * @param woShippingInfoDTO the woShippingInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woShippingInfoDTO,
     * or with status {@code 400 (Bad Request)} if the woShippingInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woShippingInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-shipping-infos")
    public ResponseEntity<WoShippingInfoDTO> updateWoShippingInfo(@Valid @RequestBody WoShippingInfoDTO woShippingInfoDTO) throws URISyntaxException {
        log.debug("REST request to update WoShippingInfo : {}", woShippingInfoDTO);
        if (woShippingInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoShippingInfoDTO result = woShippingInfoService.save(woShippingInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woShippingInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-shipping-infos} : get all the woShippingInfos.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woShippingInfos in body.
     */
    @GetMapping("/wo-shipping-infos")
    public List<WoShippingInfoDTO> getAllWoShippingInfos(@RequestParam(required = false) String filter,@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("woworkorder-is-null".equals(filter)) {
            log.debug("REST request to get all WoShippingInfos where woWorkOrder is null");
            return woShippingInfoService.findAllWhereWoWorkOrderIsNull();
        }
        log.debug("REST request to get all WoShippingInfos");
        return woShippingInfoService.findAll();
    }

    /**
     * {@code GET  /wo-shipping-infos/:id} : get the "id" woShippingInfo.
     *
     * @param id the id of the woShippingInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woShippingInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-shipping-infos/{id}")
    public ResponseEntity<WoShippingInfoDTO> getWoShippingInfo(@PathVariable Long id) {
        log.debug("REST request to get WoShippingInfo : {}", id);
        Optional<WoShippingInfoDTO> woShippingInfoDTO = woShippingInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woShippingInfoDTO);
    }

    /**
     * {@code DELETE  /wo-shipping-infos/:id} : delete the "id" woShippingInfo.
     *
     * @param id the id of the woShippingInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-shipping-infos/{id}")
    public ResponseEntity<Void> deleteWoShippingInfo(@PathVariable Long id) {
        log.debug("REST request to delete WoShippingInfo : {}", id);
        woShippingInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
