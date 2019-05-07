package com.java.web.rest;

import com.java.NewSpecialtyRequestFeaturesApp;
import com.java.domain.WoTransportModes;
import com.java.repository.WoTransportModesRepository;
import com.java.service.WoTransportModesService;
import com.java.service.dto.WoTransportModesDTO;
import com.java.service.mapper.WoTransportModesMapper;
import com.java.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.java.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link WoTransportModesResource} REST controller.
 */
@SpringBootTest(classes = NewSpecialtyRequestFeaturesApp.class)
public class WoTransportModesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private WoTransportModesRepository woTransportModesRepository;

    @Autowired
    private WoTransportModesMapper woTransportModesMapper;

    @Autowired
    private WoTransportModesService woTransportModesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restWoTransportModesMockMvc;

    private WoTransportModes woTransportModes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoTransportModesResource woTransportModesResource = new WoTransportModesResource(woTransportModesService);
        this.restWoTransportModesMockMvc = MockMvcBuilders.standaloneSetup(woTransportModesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoTransportModes createEntity(EntityManager em) {
        WoTransportModes woTransportModes = new WoTransportModes()
            .name(DEFAULT_NAME);
        return woTransportModes;
    }

    @BeforeEach
    public void initTest() {
        woTransportModes = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoTransportModes() throws Exception {
        int databaseSizeBeforeCreate = woTransportModesRepository.findAll().size();

        // Create the WoTransportModes
        WoTransportModesDTO woTransportModesDTO = woTransportModesMapper.toDto(woTransportModes);
        restWoTransportModesMockMvc.perform(post("/api/wo-transport-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woTransportModesDTO)))
            .andExpect(status().isCreated());

        // Validate the WoTransportModes in the database
        List<WoTransportModes> woTransportModesList = woTransportModesRepository.findAll();
        assertThat(woTransportModesList).hasSize(databaseSizeBeforeCreate + 1);
        WoTransportModes testWoTransportModes = woTransportModesList.get(woTransportModesList.size() - 1);
        assertThat(testWoTransportModes.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createWoTransportModesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woTransportModesRepository.findAll().size();

        // Create the WoTransportModes with an existing ID
        woTransportModes.setId(1L);
        WoTransportModesDTO woTransportModesDTO = woTransportModesMapper.toDto(woTransportModes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoTransportModesMockMvc.perform(post("/api/wo-transport-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woTransportModesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoTransportModes in the database
        List<WoTransportModes> woTransportModesList = woTransportModesRepository.findAll();
        assertThat(woTransportModesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoTransportModes() throws Exception {
        // Initialize the database
        woTransportModesRepository.saveAndFlush(woTransportModes);

        // Get all the woTransportModesList
        restWoTransportModesMockMvc.perform(get("/api/wo-transport-modes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woTransportModes.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getWoTransportModes() throws Exception {
        // Initialize the database
        woTransportModesRepository.saveAndFlush(woTransportModes);

        // Get the woTransportModes
        restWoTransportModesMockMvc.perform(get("/api/wo-transport-modes/{id}", woTransportModes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woTransportModes.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoTransportModes() throws Exception {
        // Get the woTransportModes
        restWoTransportModesMockMvc.perform(get("/api/wo-transport-modes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoTransportModes() throws Exception {
        // Initialize the database
        woTransportModesRepository.saveAndFlush(woTransportModes);

        int databaseSizeBeforeUpdate = woTransportModesRepository.findAll().size();

        // Update the woTransportModes
        WoTransportModes updatedWoTransportModes = woTransportModesRepository.findById(woTransportModes.getId()).get();
        // Disconnect from session so that the updates on updatedWoTransportModes are not directly saved in db
        em.detach(updatedWoTransportModes);
        updatedWoTransportModes
            .name(UPDATED_NAME);
        WoTransportModesDTO woTransportModesDTO = woTransportModesMapper.toDto(updatedWoTransportModes);

        restWoTransportModesMockMvc.perform(put("/api/wo-transport-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woTransportModesDTO)))
            .andExpect(status().isOk());

        // Validate the WoTransportModes in the database
        List<WoTransportModes> woTransportModesList = woTransportModesRepository.findAll();
        assertThat(woTransportModesList).hasSize(databaseSizeBeforeUpdate);
        WoTransportModes testWoTransportModes = woTransportModesList.get(woTransportModesList.size() - 1);
        assertThat(testWoTransportModes.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingWoTransportModes() throws Exception {
        int databaseSizeBeforeUpdate = woTransportModesRepository.findAll().size();

        // Create the WoTransportModes
        WoTransportModesDTO woTransportModesDTO = woTransportModesMapper.toDto(woTransportModes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoTransportModesMockMvc.perform(put("/api/wo-transport-modes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woTransportModesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoTransportModes in the database
        List<WoTransportModes> woTransportModesList = woTransportModesRepository.findAll();
        assertThat(woTransportModesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoTransportModes() throws Exception {
        // Initialize the database
        woTransportModesRepository.saveAndFlush(woTransportModes);

        int databaseSizeBeforeDelete = woTransportModesRepository.findAll().size();

        // Delete the woTransportModes
        restWoTransportModesMockMvc.perform(delete("/api/wo-transport-modes/{id}", woTransportModes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoTransportModes> woTransportModesList = woTransportModesRepository.findAll();
        assertThat(woTransportModesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoTransportModes.class);
        WoTransportModes woTransportModes1 = new WoTransportModes();
        woTransportModes1.setId(1L);
        WoTransportModes woTransportModes2 = new WoTransportModes();
        woTransportModes2.setId(woTransportModes1.getId());
        assertThat(woTransportModes1).isEqualTo(woTransportModes2);
        woTransportModes2.setId(2L);
        assertThat(woTransportModes1).isNotEqualTo(woTransportModes2);
        woTransportModes1.setId(null);
        assertThat(woTransportModes1).isNotEqualTo(woTransportModes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoTransportModesDTO.class);
        WoTransportModesDTO woTransportModesDTO1 = new WoTransportModesDTO();
        woTransportModesDTO1.setId(1L);
        WoTransportModesDTO woTransportModesDTO2 = new WoTransportModesDTO();
        assertThat(woTransportModesDTO1).isNotEqualTo(woTransportModesDTO2);
        woTransportModesDTO2.setId(woTransportModesDTO1.getId());
        assertThat(woTransportModesDTO1).isEqualTo(woTransportModesDTO2);
        woTransportModesDTO2.setId(2L);
        assertThat(woTransportModesDTO1).isNotEqualTo(woTransportModesDTO2);
        woTransportModesDTO1.setId(null);
        assertThat(woTransportModesDTO1).isNotEqualTo(woTransportModesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woTransportModesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woTransportModesMapper.fromId(null)).isNull();
    }
}
