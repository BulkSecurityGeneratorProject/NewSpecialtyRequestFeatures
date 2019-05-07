package com.java.web.rest;

import com.java.service.WoShippingServicesService;
import com.java.web.rest.errors.BadRequestAlertException;
import com.java.service.dto.WoShippingServicesDTO;

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
 * REST controller for managing {@link com.java.domain.WoShippingServices}.
 */
@RestController
@RequestMapping("/api")
public class WoShippingServicesResource {

    private final Logger log = LoggerFactory.getLogger(WoShippingServicesResource.class);

    private static final String ENTITY_NAME = "woShippingServices";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoShippingServicesService woShippingServicesService;

    public WoShippingServicesResource(WoShippingServicesService woShippingServicesService) {
        this.woShippingServicesService = woShippingServicesService;
    }

    /**
     * {@code POST  /wo-shipping-services} : Create a new woShippingServices.
     *
     * @param woShippingServicesDTO the woShippingServicesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woShippingServicesDTO, or with status {@code 400 (Bad Request)} if the woShippingServices has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-shipping-services")
    public ResponseEntity<WoShippingServicesDTO> createWoShippingServices(@Valid @RequestBody WoShippingServicesDTO woShippingServicesDTO) throws URISyntaxException {
        log.debug("REST request to save WoShippingServices : {}", woShippingServicesDTO);
        if (woShippingServicesDTO.getId() != null) {
            throw new BadRequestAlertException("A new woShippingServices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoShippingServicesDTO result = woShippingServicesService.save(woShippingServicesDTO);
        return ResponseEntity.created(new URI("/api/wo-shipping-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-shipping-services} : Updates an existing woShippingServices.
     *
     * @param woShippingServicesDTO the woShippingServicesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woShippingServicesDTO,
     * or with status {@code 400 (Bad Request)} if the woShippingServicesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woShippingServicesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-shipping-services")
    public ResponseEntity<WoShippingServicesDTO> updateWoShippingServices(@Valid @RequestBody WoShippingServicesDTO woShippingServicesDTO) throws URISyntaxException {
        log.debug("REST request to update WoShippingServices : {}", woShippingServicesDTO);
        if (woShippingServicesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoShippingServicesDTO result = woShippingServicesService.save(woShippingServicesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woShippingServicesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-shipping-services} : get all the woShippingServices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woShippingServices in body.
     */
    @GetMapping("/wo-shipping-services")
    public List<WoShippingServicesDTO> getAllWoShippingServices() {
        log.debug("REST request to get all WoShippingServices");
        return woShippingServicesService.findAll();
    }

    /**
     * {@code GET  /wo-shipping-services/:id} : get the "id" woShippingServices.
     *
     * @param id the id of the woShippingServicesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woShippingServicesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-shipping-services/{id}")
    public ResponseEntity<WoShippingServicesDTO> getWoShippingServices(@PathVariable Long id) {
        log.debug("REST request to get WoShippingServices : {}", id);
        Optional<WoShippingServicesDTO> woShippingServicesDTO = woShippingServicesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woShippingServicesDTO);
    }

    /**
     * {@code DELETE  /wo-shipping-services/:id} : delete the "id" woShippingServices.
     *
     * @param id the id of the woShippingServicesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-shipping-services/{id}")
    public ResponseEntity<Void> deleteWoShippingServices(@PathVariable Long id) {
        log.debug("REST request to delete WoShippingServices : {}", id);
        woShippingServicesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
