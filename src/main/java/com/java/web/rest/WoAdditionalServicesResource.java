package com.java.web.rest;

import com.java.service.WoAdditionalServicesService;
import com.java.web.rest.errors.BadRequestAlertException;
import com.java.service.dto.WoAdditionalServicesDTO;

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
 * REST controller for managing {@link com.java.domain.WoAdditionalServices}.
 */
@RestController
@RequestMapping("/api")
public class WoAdditionalServicesResource {

    private final Logger log = LoggerFactory.getLogger(WoAdditionalServicesResource.class);

    private static final String ENTITY_NAME = "woAdditionalServices";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoAdditionalServicesService woAdditionalServicesService;

    public WoAdditionalServicesResource(WoAdditionalServicesService woAdditionalServicesService) {
        this.woAdditionalServicesService = woAdditionalServicesService;
    }

    /**
     * {@code POST  /wo-additional-services} : Create a new woAdditionalServices.
     *
     * @param woAdditionalServicesDTO the woAdditionalServicesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woAdditionalServicesDTO, or with status {@code 400 (Bad Request)} if the woAdditionalServices has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-additional-services")
    public ResponseEntity<WoAdditionalServicesDTO> createWoAdditionalServices(@Valid @RequestBody WoAdditionalServicesDTO woAdditionalServicesDTO) throws URISyntaxException {
        log.debug("REST request to save WoAdditionalServices : {}", woAdditionalServicesDTO);
        if (woAdditionalServicesDTO.getId() != null) {
            throw new BadRequestAlertException("A new woAdditionalServices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoAdditionalServicesDTO result = woAdditionalServicesService.save(woAdditionalServicesDTO);
        return ResponseEntity.created(new URI("/api/wo-additional-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-additional-services} : Updates an existing woAdditionalServices.
     *
     * @param woAdditionalServicesDTO the woAdditionalServicesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woAdditionalServicesDTO,
     * or with status {@code 400 (Bad Request)} if the woAdditionalServicesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woAdditionalServicesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-additional-services")
    public ResponseEntity<WoAdditionalServicesDTO> updateWoAdditionalServices(@Valid @RequestBody WoAdditionalServicesDTO woAdditionalServicesDTO) throws URISyntaxException {
        log.debug("REST request to update WoAdditionalServices : {}", woAdditionalServicesDTO);
        if (woAdditionalServicesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoAdditionalServicesDTO result = woAdditionalServicesService.save(woAdditionalServicesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woAdditionalServicesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-additional-services} : get all the woAdditionalServices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woAdditionalServices in body.
     */
    @GetMapping("/wo-additional-services")
    public List<WoAdditionalServicesDTO> getAllWoAdditionalServices() {
        log.debug("REST request to get all WoAdditionalServices");
        return woAdditionalServicesService.findAll();
    }

    /**
     * {@code GET  /wo-additional-services/:id} : get the "id" woAdditionalServices.
     *
     * @param id the id of the woAdditionalServicesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woAdditionalServicesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-additional-services/{id}")
    public ResponseEntity<WoAdditionalServicesDTO> getWoAdditionalServices(@PathVariable Long id) {
        log.debug("REST request to get WoAdditionalServices : {}", id);
        Optional<WoAdditionalServicesDTO> woAdditionalServicesDTO = woAdditionalServicesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woAdditionalServicesDTO);
    }

    /**
     * {@code DELETE  /wo-additional-services/:id} : delete the "id" woAdditionalServices.
     *
     * @param id the id of the woAdditionalServicesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-additional-services/{id}")
    public ResponseEntity<Void> deleteWoAdditionalServices(@PathVariable Long id) {
        log.debug("REST request to delete WoAdditionalServices : {}", id);
        woAdditionalServicesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
