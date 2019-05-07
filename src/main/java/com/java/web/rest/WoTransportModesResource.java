package com.java.web.rest;

import com.java.service.WoTransportModesService;
import com.java.web.rest.errors.BadRequestAlertException;
import com.java.service.dto.WoTransportModesDTO;

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

/**
 * REST controller for managing {@link com.java.domain.WoTransportModes}.
 */
@RestController
@RequestMapping("/api")
public class WoTransportModesResource {

    private final Logger log = LoggerFactory.getLogger(WoTransportModesResource.class);

    private static final String ENTITY_NAME = "woTransportModes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoTransportModesService woTransportModesService;

    public WoTransportModesResource(WoTransportModesService woTransportModesService) {
        this.woTransportModesService = woTransportModesService;
    }

    /**
     * {@code POST  /wo-transport-modes} : Create a new woTransportModes.
     *
     * @param woTransportModesDTO the woTransportModesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woTransportModesDTO, or with status {@code 400 (Bad Request)} if the woTransportModes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-transport-modes")
    public ResponseEntity<WoTransportModesDTO> createWoTransportModes(@Valid @RequestBody WoTransportModesDTO woTransportModesDTO) throws URISyntaxException {
        log.debug("REST request to save WoTransportModes : {}", woTransportModesDTO);
        if (woTransportModesDTO.getId() != null) {
            throw new BadRequestAlertException("A new woTransportModes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoTransportModesDTO result = woTransportModesService.save(woTransportModesDTO);
        return ResponseEntity.created(new URI("/api/wo-transport-modes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-transport-modes} : Updates an existing woTransportModes.
     *
     * @param woTransportModesDTO the woTransportModesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woTransportModesDTO,
     * or with status {@code 400 (Bad Request)} if the woTransportModesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woTransportModesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-transport-modes")
    public ResponseEntity<WoTransportModesDTO> updateWoTransportModes(@Valid @RequestBody WoTransportModesDTO woTransportModesDTO) throws URISyntaxException {
        log.debug("REST request to update WoTransportModes : {}", woTransportModesDTO);
        if (woTransportModesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoTransportModesDTO result = woTransportModesService.save(woTransportModesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woTransportModesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-transport-modes} : get all the woTransportModes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woTransportModes in body.
     */
    @GetMapping("/wo-transport-modes")
    public List<WoTransportModesDTO> getAllWoTransportModes() {
        log.debug("REST request to get all WoTransportModes");
        return woTransportModesService.findAll();
    }

    /**
     * {@code GET  /wo-transport-modes/:id} : get the "id" woTransportModes.
     *
     * @param id the id of the woTransportModesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woTransportModesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-transport-modes/{id}")
    public ResponseEntity<WoTransportModesDTO> getWoTransportModes(@PathVariable Long id) {
        log.debug("REST request to get WoTransportModes : {}", id);
        Optional<WoTransportModesDTO> woTransportModesDTO = woTransportModesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woTransportModesDTO);
    }

    /**
     * {@code DELETE  /wo-transport-modes/:id} : delete the "id" woTransportModes.
     *
     * @param id the id of the woTransportModesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-transport-modes/{id}")
    public ResponseEntity<Void> deleteWoTransportModes(@PathVariable Long id) {
        log.debug("REST request to delete WoTransportModes : {}", id);
        woTransportModesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
