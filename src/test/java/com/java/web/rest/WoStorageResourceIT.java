package com.java.web.rest;

import com.java.NewSpecialtyRequestFeaturesApp;
import com.java.domain.WoStorage;
import com.java.repository.WoStorageRepository;
import com.java.service.WoStorageService;
import com.java.service.dto.WoStorageDTO;
import com.java.service.mapper.WoStorageMapper;
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
 * Integration tests for the {@Link WoStorageResource} REST controller.
 */
@SpringBootTest(classes = NewSpecialtyRequestFeaturesApp.class)
public class WoStorageResourceIT {

    private static final Integer DEFAULT_QUANTITY = 11;
    private static final Integer UPDATED_QUANTITY = 10;

    private static final Float DEFAULT_SPACE_REQUIREMENT = 1F;
    private static final Float UPDATED_SPACE_REQUIREMENT = 2F;

    private static final String DEFAULT_PRODUCT_INFO = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_INFO = "BBBBBBBBBB";

    @Autowired
    private WoStorageRepository woStorageRepository;

    @Autowired
    private WoStorageMapper woStorageMapper;

    @Autowired
    private WoStorageService woStorageService;

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

    private MockMvc restWoStorageMockMvc;

    private WoStorage woStorage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoStorageResource woStorageResource = new WoStorageResource(woStorageService);
        this.restWoStorageMockMvc = MockMvcBuilders.standaloneSetup(woStorageResource)
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
    public static WoStorage createEntity(EntityManager em) {
        WoStorage woStorage = new WoStorage()
            .quantity(DEFAULT_QUANTITY)
            .spaceRequirement(DEFAULT_SPACE_REQUIREMENT)
            .productInfo(DEFAULT_PRODUCT_INFO);
        return woStorage;
    }

    @BeforeEach
    public void initTest() {
        woStorage = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoStorage() throws Exception {
        int databaseSizeBeforeCreate = woStorageRepository.findAll().size();

        // Create the WoStorage
        WoStorageDTO woStorageDTO = woStorageMapper.toDto(woStorage);
        restWoStorageMockMvc.perform(post("/api/wo-storages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woStorageDTO)))
            .andExpect(status().isCreated());

        // Validate the WoStorage in the database
        List<WoStorage> woStorageList = woStorageRepository.findAll();
        assertThat(woStorageList).hasSize(databaseSizeBeforeCreate + 1);
        WoStorage testWoStorage = woStorageList.get(woStorageList.size() - 1);
        assertThat(testWoStorage.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testWoStorage.getSpaceRequirement()).isEqualTo(DEFAULT_SPACE_REQUIREMENT);
        assertThat(testWoStorage.getProductInfo()).isEqualTo(DEFAULT_PRODUCT_INFO);
    }

    @Test
    @Transactional
    public void createWoStorageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woStorageRepository.findAll().size();

        // Create the WoStorage with an existing ID
        woStorage.setId(1L);
        WoStorageDTO woStorageDTO = woStorageMapper.toDto(woStorage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoStorageMockMvc.perform(post("/api/wo-storages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woStorageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoStorage in the database
        List<WoStorage> woStorageList = woStorageRepository.findAll();
        assertThat(woStorageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoStorages() throws Exception {
        // Initialize the database
        woStorageRepository.saveAndFlush(woStorage);

        // Get all the woStorageList
        restWoStorageMockMvc.perform(get("/api/wo-storages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woStorage.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].spaceRequirement").value(hasItem(DEFAULT_SPACE_REQUIREMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].productInfo").value(hasItem(DEFAULT_PRODUCT_INFO.toString())));
    }
    
    @Test
    @Transactional
    public void getWoStorage() throws Exception {
        // Initialize the database
        woStorageRepository.saveAndFlush(woStorage);

        // Get the woStorage
        restWoStorageMockMvc.perform(get("/api/wo-storages/{id}", woStorage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woStorage.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.spaceRequirement").value(DEFAULT_SPACE_REQUIREMENT.doubleValue()))
            .andExpect(jsonPath("$.productInfo").value(DEFAULT_PRODUCT_INFO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoStorage() throws Exception {
        // Get the woStorage
        restWoStorageMockMvc.perform(get("/api/wo-storages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoStorage() throws Exception {
        // Initialize the database
        woStorageRepository.saveAndFlush(woStorage);

        int databaseSizeBeforeUpdate = woStorageRepository.findAll().size();

        // Update the woStorage
        WoStorage updatedWoStorage = woStorageRepository.findById(woStorage.getId()).get();
        // Disconnect from session so that the updates on updatedWoStorage are not directly saved in db
        em.detach(updatedWoStorage);
        updatedWoStorage
            .quantity(UPDATED_QUANTITY)
            .spaceRequirement(UPDATED_SPACE_REQUIREMENT)
            .productInfo(UPDATED_PRODUCT_INFO);
        WoStorageDTO woStorageDTO = woStorageMapper.toDto(updatedWoStorage);

        restWoStorageMockMvc.perform(put("/api/wo-storages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woStorageDTO)))
            .andExpect(status().isOk());

        // Validate the WoStorage in the database
        List<WoStorage> woStorageList = woStorageRepository.findAll();
        assertThat(woStorageList).hasSize(databaseSizeBeforeUpdate);
        WoStorage testWoStorage = woStorageList.get(woStorageList.size() - 1);
        assertThat(testWoStorage.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testWoStorage.getSpaceRequirement()).isEqualTo(UPDATED_SPACE_REQUIREMENT);
        assertThat(testWoStorage.getProductInfo()).isEqualTo(UPDATED_PRODUCT_INFO);
    }

    @Test
    @Transactional
    public void updateNonExistingWoStorage() throws Exception {
        int databaseSizeBeforeUpdate = woStorageRepository.findAll().size();

        // Create the WoStorage
        WoStorageDTO woStorageDTO = woStorageMapper.toDto(woStorage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoStorageMockMvc.perform(put("/api/wo-storages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woStorageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoStorage in the database
        List<WoStorage> woStorageList = woStorageRepository.findAll();
        assertThat(woStorageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoStorage() throws Exception {
        // Initialize the database
        woStorageRepository.saveAndFlush(woStorage);

        int databaseSizeBeforeDelete = woStorageRepository.findAll().size();

        // Delete the woStorage
        restWoStorageMockMvc.perform(delete("/api/wo-storages/{id}", woStorage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoStorage> woStorageList = woStorageRepository.findAll();
        assertThat(woStorageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoStorage.class);
        WoStorage woStorage1 = new WoStorage();
        woStorage1.setId(1L);
        WoStorage woStorage2 = new WoStorage();
        woStorage2.setId(woStorage1.getId());
        assertThat(woStorage1).isEqualTo(woStorage2);
        woStorage2.setId(2L);
        assertThat(woStorage1).isNotEqualTo(woStorage2);
        woStorage1.setId(null);
        assertThat(woStorage1).isNotEqualTo(woStorage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoStorageDTO.class);
        WoStorageDTO woStorageDTO1 = new WoStorageDTO();
        woStorageDTO1.setId(1L);
        WoStorageDTO woStorageDTO2 = new WoStorageDTO();
        assertThat(woStorageDTO1).isNotEqualTo(woStorageDTO2);
        woStorageDTO2.setId(woStorageDTO1.getId());
        assertThat(woStorageDTO1).isEqualTo(woStorageDTO2);
        woStorageDTO2.setId(2L);
        assertThat(woStorageDTO1).isNotEqualTo(woStorageDTO2);
        woStorageDTO1.setId(null);
        assertThat(woStorageDTO1).isNotEqualTo(woStorageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woStorageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woStorageMapper.fromId(null)).isNull();
    }
}
