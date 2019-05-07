package com.java.web.rest;

import com.java.service.WoStorageService;
import com.java.web.rest.errors.BadRequestAlertException;
import com.java.service.dto.WoStorageDTO;

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
 * REST controller for managing {@link com.java.domain.WoStorage}.
 */
@RestController
@RequestMapping("/api")
public class WoStorageResource {

    private final Logger log = LoggerFactory.getLogger(WoStorageResource.class);

    private static final String ENTITY_NAME = "woStorage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoStorageService woStorageService;

    public WoStorageResource(WoStorageService woStorageService) {
        this.woStorageService = woStorageService;
    }

    /**
     * {@code POST  /wo-storages} : Create a new woStorage.
     *
     * @param woStorageDTO the woStorageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woStorageDTO, or with status {@code 400 (Bad Request)} if the woStorage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-storages")
    public ResponseEntity<WoStorageDTO> createWoStorage(@Valid @RequestBody WoStorageDTO woStorageDTO) throws URISyntaxException {
        log.debug("REST request to save WoStorage : {}", woStorageDTO);
        if (woStorageDTO.getId() != null) {
            throw new BadRequestAlertException("A new woStorage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoStorageDTO result = woStorageService.save(woStorageDTO);
        return ResponseEntity.created(new URI("/api/wo-storages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-storages} : Updates an existing woStorage.
     *
     * @param woStorageDTO the woStorageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woStorageDTO,
     * or with status {@code 400 (Bad Request)} if the woStorageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woStorageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-storages")
    public ResponseEntity<WoStorageDTO> updateWoStorage(@Valid @RequestBody WoStorageDTO woStorageDTO) throws URISyntaxException {
        log.debug("REST request to update WoStorage : {}", woStorageDTO);
        if (woStorageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoStorageDTO result = woStorageService.save(woStorageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woStorageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-storages} : get all the woStorages.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woStorages in body.
     */
    @GetMapping("/wo-storages")
    public List<WoStorageDTO> getAllWoStorages(@RequestParam(required = false) String filter) {
        if ("woworkorder-is-null".equals(filter)) {
            log.debug("REST request to get all WoStorages where woWorkOrder is null");
            return woStorageService.findAllWhereWoWorkOrderIsNull();
        }
        log.debug("REST request to get all WoStorages");
        return woStorageService.findAll();
    }

    /**
     * {@code GET  /wo-storages/:id} : get the "id" woStorage.
     *
     * @param id the id of the woStorageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woStorageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-storages/{id}")
    public ResponseEntity<WoStorageDTO> getWoStorage(@PathVariable Long id) {
        log.debug("REST request to get WoStorage : {}", id);
        Optional<WoStorageDTO> woStorageDTO = woStorageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woStorageDTO);
    }

    /**
     * {@code DELETE  /wo-storages/:id} : delete the "id" woStorage.
     *
     * @param id the id of the woStorageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-storages/{id}")
    public ResponseEntity<Void> deleteWoStorage(@PathVariable Long id) {
        log.debug("REST request to delete WoStorage : {}", id);
        woStorageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
