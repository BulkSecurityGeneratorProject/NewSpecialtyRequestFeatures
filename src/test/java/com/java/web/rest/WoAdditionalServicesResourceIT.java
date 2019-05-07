package com.java.web.rest;

import com.java.NewSpecialtyRequestFeaturesApp;
import com.java.domain.WoAdditionalServices;
import com.java.repository.WoAdditionalServicesRepository;
import com.java.service.WoAdditionalServicesService;
import com.java.service.dto.WoAdditionalServicesDTO;
import com.java.service.mapper.WoAdditionalServicesMapper;
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
 * Integration tests for the {@Link WoAdditionalServicesResource} REST controller.
 */
@SpringBootTest(classes = NewSpecialtyRequestFeaturesApp.class)
public class WoAdditionalServicesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private WoAdditionalServicesRepository woAdditionalServicesRepository;

    @Autowired
    private WoAdditionalServicesMapper woAdditionalServicesMapper;

    @Autowired
    private WoAdditionalServicesService woAdditionalServicesService;

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

    private MockMvc restWoAdditionalServicesMockMvc;

    private WoAdditionalServices woAdditionalServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoAdditionalServicesResource woAdditionalServicesResource = new WoAdditionalServicesResource(woAdditionalServicesService);
        this.restWoAdditionalServicesMockMvc = MockMvcBuilders.standaloneSetup(woAdditionalServicesResource)
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
    public static WoAdditionalServices createEntity(EntityManager em) {
        WoAdditionalServices woAdditionalServices = new WoAdditionalServices()
            .name(DEFAULT_NAME);
        return woAdditionalServices;
    }

    @BeforeEach
    public void initTest() {
        woAdditionalServices = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoAdditionalServices() throws Exception {
        int databaseSizeBeforeCreate = woAdditionalServicesRepository.findAll().size();

        // Create the WoAdditionalServices
        WoAdditionalServicesDTO woAdditionalServicesDTO = woAdditionalServicesMapper.toDto(woAdditionalServices);
        restWoAdditionalServicesMockMvc.perform(post("/api/wo-additional-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woAdditionalServicesDTO)))
            .andExpect(status().isCreated());

        // Validate the WoAdditionalServices in the database
        List<WoAdditionalServices> woAdditionalServicesList = woAdditionalServicesRepository.findAll();
        assertThat(woAdditionalServicesList).hasSize(databaseSizeBeforeCreate + 1);
        WoAdditionalServices testWoAdditionalServices = woAdditionalServicesList.get(woAdditionalServicesList.size() - 1);
        assertThat(testWoAdditionalServices.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createWoAdditionalServicesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woAdditionalServicesRepository.findAll().size();

        // Create the WoAdditionalServices with an existing ID
        woAdditionalServices.setId(1L);
        WoAdditionalServicesDTO woAdditionalServicesDTO = woAdditionalServicesMapper.toDto(woAdditionalServices);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoAdditionalServicesMockMvc.perform(post("/api/wo-additional-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woAdditionalServicesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoAdditionalServices in the database
        List<WoAdditionalServices> woAdditionalServicesList = woAdditionalServicesRepository.findAll();
        assertThat(woAdditionalServicesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoAdditionalServices() throws Exception {
        // Initialize the database
        woAdditionalServicesRepository.saveAndFlush(woAdditionalServices);

        // Get all the woAdditionalServicesList
        restWoAdditionalServicesMockMvc.perform(get("/api/wo-additional-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woAdditionalServices.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getWoAdditionalServices() throws Exception {
        // Initialize the database
        woAdditionalServicesRepository.saveAndFlush(woAdditionalServices);

        // Get the woAdditionalServices
        restWoAdditionalServicesMockMvc.perform(get("/api/wo-additional-services/{id}", woAdditionalServices.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woAdditionalServices.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoAdditionalServices() throws Exception {
        // Get the woAdditionalServices
        restWoAdditionalServicesMockMvc.perform(get("/api/wo-additional-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoAdditionalServices() throws Exception {
        // Initialize the database
        woAdditionalServicesRepository.saveAndFlush(woAdditionalServices);

        int databaseSizeBeforeUpdate = woAdditionalServicesRepository.findAll().size();

        // Update the woAdditionalServices
        WoAdditionalServices updatedWoAdditionalServices = woAdditionalServicesRepository.findById(woAdditionalServices.getId()).get();
        // Disconnect from session so that the updates on updatedWoAdditionalServices are not directly saved in db
        em.detach(updatedWoAdditionalServices);
        updatedWoAdditionalServices
            .name(UPDATED_NAME);
        WoAdditionalServicesDTO woAdditionalServicesDTO = woAdditionalServicesMapper.toDto(updatedWoAdditionalServices);

        restWoAdditionalServicesMockMvc.perform(put("/api/wo-additional-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woAdditionalServicesDTO)))
            .andExpect(status().isOk());

        // Validate the WoAdditionalServices in the database
        List<WoAdditionalServices> woAdditionalServicesList = woAdditionalServicesRepository.findAll();
        assertThat(woAdditionalServicesList).hasSize(databaseSizeBeforeUpdate);
        WoAdditionalServices testWoAdditionalServices = woAdditionalServicesList.get(woAdditionalServicesList.size() - 1);
        assertThat(testWoAdditionalServices.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingWoAdditionalServices() throws Exception {
        int databaseSizeBeforeUpdate = woAdditionalServicesRepository.findAll().size();

        // Create the WoAdditionalServices
        WoAdditionalServicesDTO woAdditionalServicesDTO = woAdditionalServicesMapper.toDto(woAdditionalServices);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoAdditionalServicesMockMvc.perform(put("/api/wo-additional-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woAdditionalServicesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoAdditionalServices in the database
        List<WoAdditionalServices> woAdditionalServicesList = woAdditionalServicesRepository.findAll();
        assertThat(woAdditionalServicesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoAdditionalServices() throws Exception {
        // Initialize the database
        woAdditionalServicesRepository.saveAndFlush(woAdditionalServices);

        int databaseSizeBeforeDelete = woAdditionalServicesRepository.findAll().size();

        // Delete the woAdditionalServices
        restWoAdditionalServicesMockMvc.perform(delete("/api/wo-additional-services/{id}", woAdditionalServices.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoAdditionalServices> woAdditionalServicesList = woAdditionalServicesRepository.findAll();
        assertThat(woAdditionalServicesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoAdditionalServices.class);
        WoAdditionalServices woAdditionalServices1 = new WoAdditionalServices();
        woAdditionalServices1.setId(1L);
        WoAdditionalServices woAdditionalServices2 = new WoAdditionalServices();
        woAdditionalServices2.setId(woAdditionalServices1.getId());
        assertThat(woAdditionalServices1).isEqualTo(woAdditionalServices2);
        woAdditionalServices2.setId(2L);
        assertThat(woAdditionalServices1).isNotEqualTo(woAdditionalServices2);
        woAdditionalServices1.setId(null);
        assertThat(woAdditionalServices1).isNotEqualTo(woAdditionalServices2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoAdditionalServicesDTO.class);
        WoAdditionalServicesDTO woAdditionalServicesDTO1 = new WoAdditionalServicesDTO();
        woAdditionalServicesDTO1.setId(1L);
        WoAdditionalServicesDTO woAdditionalServicesDTO2 = new WoAdditionalServicesDTO();
        assertThat(woAdditionalServicesDTO1).isNotEqualTo(woAdditionalServicesDTO2);
        woAdditionalServicesDTO2.setId(woAdditionalServicesDTO1.getId());
        assertThat(woAdditionalServicesDTO1).isEqualTo(woAdditionalServicesDTO2);
        woAdditionalServicesDTO2.setId(2L);
        assertThat(woAdditionalServicesDTO1).isNotEqualTo(woAdditionalServicesDTO2);
        woAdditionalServicesDTO1.setId(null);
        assertThat(woAdditionalServicesDTO1).isNotEqualTo(woAdditionalServicesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woAdditionalServicesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woAdditionalServicesMapper.fromId(null)).isNull();
    }
}
