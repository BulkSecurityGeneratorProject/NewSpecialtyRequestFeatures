package com.java.web.rest;

import com.java.NewSpecialtyRequestFeaturesApp;
import com.java.domain.WoShippingServices;
import com.java.repository.WoShippingServicesRepository;
import com.java.service.WoShippingServicesService;
import com.java.service.dto.WoShippingServicesDTO;
import com.java.service.mapper.WoShippingServicesMapper;
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
 * Integration tests for the {@Link WoShippingServicesResource} REST controller.
 */
@SpringBootTest(classes = NewSpecialtyRequestFeaturesApp.class)
public class WoShippingServicesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private WoShippingServicesRepository woShippingServicesRepository;

    @Autowired
    private WoShippingServicesMapper woShippingServicesMapper;

    @Autowired
    private WoShippingServicesService woShippingServicesService;

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

    private MockMvc restWoShippingServicesMockMvc;

    private WoShippingServices woShippingServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoShippingServicesResource woShippingServicesResource = new WoShippingServicesResource(woShippingServicesService);
        this.restWoShippingServicesMockMvc = MockMvcBuilders.standaloneSetup(woShippingServicesResource)
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
    public static WoShippingServices createEntity(EntityManager em) {
        WoShippingServices woShippingServices = new WoShippingServices()
            .name(DEFAULT_NAME);
        return woShippingServices;
    }

    @BeforeEach
    public void initTest() {
        woShippingServices = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoShippingServices() throws Exception {
        int databaseSizeBeforeCreate = woShippingServicesRepository.findAll().size();

        // Create the WoShippingServices
        WoShippingServicesDTO woShippingServicesDTO = woShippingServicesMapper.toDto(woShippingServices);
        restWoShippingServicesMockMvc.perform(post("/api/wo-shipping-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woShippingServicesDTO)))
            .andExpect(status().isCreated());

        // Validate the WoShippingServices in the database
        List<WoShippingServices> woShippingServicesList = woShippingServicesRepository.findAll();
        assertThat(woShippingServicesList).hasSize(databaseSizeBeforeCreate + 1);
        WoShippingServices testWoShippingServices = woShippingServicesList.get(woShippingServicesList.size() - 1);
        assertThat(testWoShippingServices.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createWoShippingServicesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woShippingServicesRepository.findAll().size();

        // Create the WoShippingServices with an existing ID
        woShippingServices.setId(1L);
        WoShippingServicesDTO woShippingServicesDTO = woShippingServicesMapper.toDto(woShippingServices);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoShippingServicesMockMvc.perform(post("/api/wo-shipping-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woShippingServicesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoShippingServices in the database
        List<WoShippingServices> woShippingServicesList = woShippingServicesRepository.findAll();
        assertThat(woShippingServicesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoShippingServices() throws Exception {
        // Initialize the database
        woShippingServicesRepository.saveAndFlush(woShippingServices);

        // Get all the woShippingServicesList
        restWoShippingServicesMockMvc.perform(get("/api/wo-shipping-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woShippingServices.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getWoShippingServices() throws Exception {
        // Initialize the database
        woShippingServicesRepository.saveAndFlush(woShippingServices);

        // Get the woShippingServices
        restWoShippingServicesMockMvc.perform(get("/api/wo-shipping-services/{id}", woShippingServices.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woShippingServices.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoShippingServices() throws Exception {
        // Get the woShippingServices
        restWoShippingServicesMockMvc.perform(get("/api/wo-shipping-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoShippingServices() throws Exception {
        // Initialize the database
        woShippingServicesRepository.saveAndFlush(woShippingServices);

        int databaseSizeBeforeUpdate = woShippingServicesRepository.findAll().size();

        // Update the woShippingServices
        WoShippingServices updatedWoShippingServices = woShippingServicesRepository.findById(woShippingServices.getId()).get();
        // Disconnect from session so that the updates on updatedWoShippingServices are not directly saved in db
        em.detach(updatedWoShippingServices);
        updatedWoShippingServices
            .name(UPDATED_NAME);
        WoShippingServicesDTO woShippingServicesDTO = woShippingServicesMapper.toDto(updatedWoShippingServices);

        restWoShippingServicesMockMvc.perform(put("/api/wo-shipping-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woShippingServicesDTO)))
            .andExpect(status().isOk());

        // Validate the WoShippingServices in the database
        List<WoShippingServices> woShippingServicesList = woShippingServicesRepository.findAll();
        assertThat(woShippingServicesList).hasSize(databaseSizeBeforeUpdate);
        WoShippingServices testWoShippingServices = woShippingServicesList.get(woShippingServicesList.size() - 1);
        assertThat(testWoShippingServices.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingWoShippingServices() throws Exception {
        int databaseSizeBeforeUpdate = woShippingServicesRepository.findAll().size();

        // Create the WoShippingServices
        WoShippingServicesDTO woShippingServicesDTO = woShippingServicesMapper.toDto(woShippingServices);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoShippingServicesMockMvc.perform(put("/api/wo-shipping-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woShippingServicesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoShippingServices in the database
        List<WoShippingServices> woShippingServicesList = woShippingServicesRepository.findAll();
        assertThat(woShippingServicesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoShippingServices() throws Exception {
        // Initialize the database
        woShippingServicesRepository.saveAndFlush(woShippingServices);

        int databaseSizeBeforeDelete = woShippingServicesRepository.findAll().size();

        // Delete the woShippingServices
        restWoShippingServicesMockMvc.perform(delete("/api/wo-shipping-services/{id}", woShippingServices.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoShippingServices> woShippingServicesList = woShippingServicesRepository.findAll();
        assertThat(woShippingServicesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoShippingServices.class);
        WoShippingServices woShippingServices1 = new WoShippingServices();
        woShippingServices1.setId(1L);
        WoShippingServices woShippingServices2 = new WoShippingServices();
        woShippingServices2.setId(woShippingServices1.getId());
        assertThat(woShippingServices1).isEqualTo(woShippingServices2);
        woShippingServices2.setId(2L);
        assertThat(woShippingServices1).isNotEqualTo(woShippingServices2);
        woShippingServices1.setId(null);
        assertThat(woShippingServices1).isNotEqualTo(woShippingServices2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoShippingServicesDTO.class);
        WoShippingServicesDTO woShippingServicesDTO1 = new WoShippingServicesDTO();
        woShippingServicesDTO1.setId(1L);
        WoShippingServicesDTO woShippingServicesDTO2 = new WoShippingServicesDTO();
        assertThat(woShippingServicesDTO1).isNotEqualTo(woShippingServicesDTO2);
        woShippingServicesDTO2.setId(woShippingServicesDTO1.getId());
        assertThat(woShippingServicesDTO1).isEqualTo(woShippingServicesDTO2);
        woShippingServicesDTO2.setId(2L);
        assertThat(woShippingServicesDTO1).isNotEqualTo(woShippingServicesDTO2);
        woShippingServicesDTO1.setId(null);
        assertThat(woShippingServicesDTO1).isNotEqualTo(woShippingServicesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woShippingServicesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woShippingServicesMapper.fromId(null)).isNull();
    }
}
