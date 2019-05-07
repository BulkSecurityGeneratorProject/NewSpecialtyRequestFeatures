package com.java.web.rest;

import com.java.service.WoWorkOrderService;
import com.java.web.rest.errors.BadRequestAlertException;
import com.java.service.dto.WoWorkOrderDTO;

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
 * REST controller for managing {@link com.java.domain.WoWorkOrder}.
 */
@RestController
@RequestMapping("/api")
public class WoWorkOrderResource {

    private final Logger log = LoggerFactory.getLogger(WoWorkOrderResource.class);

    private static final String ENTITY_NAME = "woWorkOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoWorkOrderService woWorkOrderService;

    public WoWorkOrderResource(WoWorkOrderService woWorkOrderService) {
        this.woWorkOrderService = woWorkOrderService;
    }

    /**
     * {@code POST  /wo-work-orders} : Create a new woWorkOrder.
     *
     * @param woWorkOrderDTO the woWorkOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woWorkOrderDTO, or with status {@code 400 (Bad Request)} if the woWorkOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-work-orders")
    public ResponseEntity<WoWorkOrderDTO> createWoWorkOrder(@Valid @RequestBody WoWorkOrderDTO woWorkOrderDTO) throws URISyntaxException {
        log.debug("REST request to save WoWorkOrder : {}", woWorkOrderDTO);
        if (woWorkOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new woWorkOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoWorkOrderDTO result = woWorkOrderService.save(woWorkOrderDTO);
        return ResponseEntity.created(new URI("/api/wo-work-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-work-orders} : Updates an existing woWorkOrder.
     *
     * @param woWorkOrderDTO the woWorkOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woWorkOrderDTO,
     * or with status {@code 400 (Bad Request)} if the woWorkOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woWorkOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-work-orders")
    public ResponseEntity<WoWorkOrderDTO> updateWoWorkOrder(@Valid @RequestBody WoWorkOrderDTO woWorkOrderDTO) throws URISyntaxException {
        log.debug("REST request to update WoWorkOrder : {}", woWorkOrderDTO);
        if (woWorkOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoWorkOrderDTO result = woWorkOrderService.save(woWorkOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woWorkOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-work-orders} : get all the woWorkOrders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woWorkOrders in body.
     */
    @GetMapping("/wo-work-orders")
    public List<WoWorkOrderDTO> getAllWoWorkOrders() {
        log.debug("REST request to get all WoWorkOrders");
        return woWorkOrderService.findAll();
    }

    /**
     * {@code GET  /wo-work-orders/:id} : get the "id" woWorkOrder.
     *
     * @param id the id of the woWorkOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woWorkOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-work-orders/{id}")
    public ResponseEntity<WoWorkOrderDTO> getWoWorkOrder(@PathVariable Long id) {
        log.debug("REST request to get WoWorkOrder : {}", id);
        Optional<WoWorkOrderDTO> woWorkOrderDTO = woWorkOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woWorkOrderDTO);
    }

    /**
     * {@code DELETE  /wo-work-orders/:id} : delete the "id" woWorkOrder.
     *
     * @param id the id of the woWorkOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-work-orders/{id}")
    public ResponseEntity<Void> deleteWoWorkOrder(@PathVariable Long id) {
        log.debug("REST request to delete WoWorkOrder : {}", id);
        woWorkOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
