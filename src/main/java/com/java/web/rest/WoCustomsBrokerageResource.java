package com.java.web.rest;

import com.java.service.WoCustomsBrokerageService;
import com.java.web.rest.errors.BadRequestAlertException;
import com.java.service.dto.WoCustomsBrokerageDTO;

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
 * REST controller for managing {@link com.java.domain.WoCustomsBrokerage}.
 */
@RestController
@RequestMapping("/api")
public class WoCustomsBrokerageResource {

    private final Logger log = LoggerFactory.getLogger(WoCustomsBrokerageResource.class);

    private static final String ENTITY_NAME = "woCustomsBrokerage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoCustomsBrokerageService woCustomsBrokerageService;

    public WoCustomsBrokerageResource(WoCustomsBrokerageService woCustomsBrokerageService) {
        this.woCustomsBrokerageService = woCustomsBrokerageService;
    }

    /**
     * {@code POST  /wo-customs-brokerages} : Create a new woCustomsBrokerage.
     *
     * @param woCustomsBrokerageDTO the woCustomsBrokerageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woCustomsBrokerageDTO, or with status {@code 400 (Bad Request)} if the woCustomsBrokerage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-customs-brokerages")
    public ResponseEntity<WoCustomsBrokerageDTO> createWoCustomsBrokerage(@Valid @RequestBody WoCustomsBrokerageDTO woCustomsBrokerageDTO) throws URISyntaxException {
        log.debug("REST request to save WoCustomsBrokerage : {}", woCustomsBrokerageDTO);
        if (woCustomsBrokerageDTO.getId() != null) {
            throw new BadRequestAlertException("A new woCustomsBrokerage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoCustomsBrokerageDTO result = woCustomsBrokerageService.save(woCustomsBrokerageDTO);
        return ResponseEntity.created(new URI("/api/wo-customs-brokerages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-customs-brokerages} : Updates an existing woCustomsBrokerage.
     *
     * @param woCustomsBrokerageDTO the woCustomsBrokerageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woCustomsBrokerageDTO,
     * or with status {@code 400 (Bad Request)} if the woCustomsBrokerageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woCustomsBrokerageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-customs-brokerages")
    public ResponseEntity<WoCustomsBrokerageDTO> updateWoCustomsBrokerage(@Valid @RequestBody WoCustomsBrokerageDTO woCustomsBrokerageDTO) throws URISyntaxException {
        log.debug("REST request to update WoCustomsBrokerage : {}", woCustomsBrokerageDTO);
        if (woCustomsBrokerageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoCustomsBrokerageDTO result = woCustomsBrokerageService.save(woCustomsBrokerageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woCustomsBrokerageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-customs-brokerages} : get all the woCustomsBrokerages.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woCustomsBrokerages in body.
     */
    @GetMapping("/wo-customs-brokerages")
    public List<WoCustomsBrokerageDTO> getAllWoCustomsBrokerages(@RequestParam(required = false) String filter,@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("woworkorder-is-null".equals(filter)) {
            log.debug("REST request to get all WoCustomsBrokerages where woWorkOrder is null");
            return woCustomsBrokerageService.findAllWhereWoWorkOrderIsNull();
        }
        log.debug("REST request to get all WoCustomsBrokerages");
        return woCustomsBrokerageService.findAll();
    }

    /**
     * {@code GET  /wo-customs-brokerages/:id} : get the "id" woCustomsBrokerage.
     *
     * @param id the id of the woCustomsBrokerageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woCustomsBrokerageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-customs-brokerages/{id}")
    public ResponseEntity<WoCustomsBrokerageDTO> getWoCustomsBrokerage(@PathVariable Long id) {
        log.debug("REST request to get WoCustomsBrokerage : {}", id);
        Optional<WoCustomsBrokerageDTO> woCustomsBrokerageDTO = woCustomsBrokerageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woCustomsBrokerageDTO);
    }

    /**
     * {@code DELETE  /wo-customs-brokerages/:id} : delete the "id" woCustomsBrokerage.
     *
     * @param id the id of the woCustomsBrokerageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-customs-brokerages/{id}")
    public ResponseEntity<Void> deleteWoCustomsBrokerage(@PathVariable Long id) {
        log.debug("REST request to delete WoCustomsBrokerage : {}", id);
        woCustomsBrokerageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
