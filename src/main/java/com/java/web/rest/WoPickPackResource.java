package com.java.web.rest;

import com.java.service.WoPickPackService;
import com.java.web.rest.errors.BadRequestAlertException;
import com.java.service.dto.WoPickPackDTO;

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
 * REST controller for managing {@link com.java.domain.WoPickPack}.
 */
@RestController
@RequestMapping("/api")
public class WoPickPackResource {

    private final Logger log = LoggerFactory.getLogger(WoPickPackResource.class);

    private static final String ENTITY_NAME = "woPickPack";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WoPickPackService woPickPackService;

    public WoPickPackResource(WoPickPackService woPickPackService) {
        this.woPickPackService = woPickPackService;
    }

    /**
     * {@code POST  /wo-pick-packs} : Create a new woPickPack.
     *
     * @param woPickPackDTO the woPickPackDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new woPickPackDTO, or with status {@code 400 (Bad Request)} if the woPickPack has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wo-pick-packs")
    public ResponseEntity<WoPickPackDTO> createWoPickPack(@Valid @RequestBody WoPickPackDTO woPickPackDTO) throws URISyntaxException {
        log.debug("REST request to save WoPickPack : {}", woPickPackDTO);
        if (woPickPackDTO.getId() != null) {
            throw new BadRequestAlertException("A new woPickPack cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoPickPackDTO result = woPickPackService.save(woPickPackDTO);
        return ResponseEntity.created(new URI("/api/wo-pick-packs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wo-pick-packs} : Updates an existing woPickPack.
     *
     * @param woPickPackDTO the woPickPackDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated woPickPackDTO,
     * or with status {@code 400 (Bad Request)} if the woPickPackDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the woPickPackDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wo-pick-packs")
    public ResponseEntity<WoPickPackDTO> updateWoPickPack(@Valid @RequestBody WoPickPackDTO woPickPackDTO) throws URISyntaxException {
        log.debug("REST request to update WoPickPack : {}", woPickPackDTO);
        if (woPickPackDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoPickPackDTO result = woPickPackService.save(woPickPackDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, woPickPackDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wo-pick-packs} : get all the woPickPacks.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of woPickPacks in body.
     */
    @GetMapping("/wo-pick-packs")
    public List<WoPickPackDTO> getAllWoPickPacks(@RequestParam(required = false) String filter,@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("woworkorder-is-null".equals(filter)) {
            log.debug("REST request to get all WoPickPacks where woWorkOrder is null");
            return woPickPackService.findAllWhereWoWorkOrderIsNull();
        }
        log.debug("REST request to get all WoPickPacks");
        return woPickPackService.findAll();
    }

    /**
     * {@code GET  /wo-pick-packs/:id} : get the "id" woPickPack.
     *
     * @param id the id of the woPickPackDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the woPickPackDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wo-pick-packs/{id}")
    public ResponseEntity<WoPickPackDTO> getWoPickPack(@PathVariable Long id) {
        log.debug("REST request to get WoPickPack : {}", id);
        Optional<WoPickPackDTO> woPickPackDTO = woPickPackService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woPickPackDTO);
    }

    /**
     * {@code DELETE  /wo-pick-packs/:id} : delete the "id" woPickPack.
     *
     * @param id the id of the woPickPackDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wo-pick-packs/{id}")
    public ResponseEntity<Void> deleteWoPickPack(@PathVariable Long id) {
        log.debug("REST request to delete WoPickPack : {}", id);
        woPickPackService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
