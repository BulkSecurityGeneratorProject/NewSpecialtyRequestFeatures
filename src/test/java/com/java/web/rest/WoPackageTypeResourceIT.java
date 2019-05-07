package com.java.web.rest;

import com.java.NewSpecialtyRequestFeaturesApp;
import com.java.domain.WoPackageType;
import com.java.repository.WoPackageTypeRepository;
import com.java.service.WoPackageTypeService;
import com.java.service.dto.WoPackageTypeDTO;
import com.java.service.mapper.WoPackageTypeMapper;
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
 * Integration tests for the {@Link WoPackageTypeResource} REST controller.
 */
@SpringBootTest(classes = NewSpecialtyRequestFeaturesApp.class)
public class WoPackageTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private WoPackageTypeRepository woPackageTypeRepository;

    @Autowired
    private WoPackageTypeMapper woPackageTypeMapper;

    @Autowired
    private WoPackageTypeService woPackageTypeService;

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

    private MockMvc restWoPackageTypeMockMvc;

    private WoPackageType woPackageType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoPackageTypeResource woPackageTypeResource = new WoPackageTypeResource(woPackageTypeService);
        this.restWoPackageTypeMockMvc = MockMvcBuilders.standaloneSetup(woPackageTypeResource)
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
    public static WoPackageType createEntity(EntityManager em) {
        WoPackageType woPackageType = new WoPackageType()
            .name(DEFAULT_NAME);
        return woPackageType;
    }

    @BeforeEach
    public void initTest() {
        woPackageType = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoPackageType() throws Exception {
        int databaseSizeBeforeCreate = woPackageTypeRepository.findAll().size();

        // Create the WoPackageType
        WoPackageTypeDTO woPackageTypeDTO = woPackageTypeMapper.toDto(woPackageType);
        restWoPackageTypeMockMvc.perform(post("/api/wo-package-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPackageTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the WoPackageType in the database
        List<WoPackageType> woPackageTypeList = woPackageTypeRepository.findAll();
        assertThat(woPackageTypeList).hasSize(databaseSizeBeforeCreate + 1);
        WoPackageType testWoPackageType = woPackageTypeList.get(woPackageTypeList.size() - 1);
        assertThat(testWoPackageType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createWoPackageTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woPackageTypeRepository.findAll().size();

        // Create the WoPackageType with an existing ID
        woPackageType.setId(1L);
        WoPackageTypeDTO woPackageTypeDTO = woPackageTypeMapper.toDto(woPackageType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoPackageTypeMockMvc.perform(post("/api/wo-package-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPackageTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoPackageType in the database
        List<WoPackageType> woPackageTypeList = woPackageTypeRepository.findAll();
        assertThat(woPackageTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoPackageTypes() throws Exception {
        // Initialize the database
        woPackageTypeRepository.saveAndFlush(woPackageType);

        // Get all the woPackageTypeList
        restWoPackageTypeMockMvc.perform(get("/api/wo-package-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woPackageType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getWoPackageType() throws Exception {
        // Initialize the database
        woPackageTypeRepository.saveAndFlush(woPackageType);

        // Get the woPackageType
        restWoPackageTypeMockMvc.perform(get("/api/wo-package-types/{id}", woPackageType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woPackageType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoPackageType() throws Exception {
        // Get the woPackageType
        restWoPackageTypeMockMvc.perform(get("/api/wo-package-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoPackageType() throws Exception {
        // Initialize the database
        woPackageTypeRepository.saveAndFlush(woPackageType);

        int databaseSizeBeforeUpdate = woPackageTypeRepository.findAll().size();

        // Update the woPackageType
        WoPackageType updatedWoPackageType = woPackageTypeRepository.findById(woPackageType.getId()).get();
        // Disconnect from session so that the updates on updatedWoPackageType are not directly saved in db
        em.detach(updatedWoPackageType);
        updatedWoPackageType
            .name(UPDATED_NAME);
        WoPackageTypeDTO woPackageTypeDTO = woPackageTypeMapper.toDto(updatedWoPackageType);

        restWoPackageTypeMockMvc.perform(put("/api/wo-package-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPackageTypeDTO)))
            .andExpect(status().isOk());

        // Validate the WoPackageType in the database
        List<WoPackageType> woPackageTypeList = woPackageTypeRepository.findAll();
        assertThat(woPackageTypeList).hasSize(databaseSizeBeforeUpdate);
        WoPackageType testWoPackageType = woPackageTypeList.get(woPackageTypeList.size() - 1);
        assertThat(testWoPackageType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingWoPackageType() throws Exception {
        int databaseSizeBeforeUpdate = woPackageTypeRepository.findAll().size();

        // Create the WoPackageType
        WoPackageTypeDTO woPackageTypeDTO = woPackageTypeMapper.toDto(woPackageType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoPackageTypeMockMvc.perform(put("/api/wo-package-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPackageTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoPackageType in the database
        List<WoPackageType> woPackageTypeList = woPackageTypeRepository.findAll();
        assertThat(woPackageTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoPackageType() throws Exception {
        // Initialize the database
        woPackageTypeRepository.saveAndFlush(woPackageType);

        int databaseSizeBeforeDelete = woPackageTypeRepository.findAll().size();

        // Delete the woPackageType
        restWoPackageTypeMockMvc.perform(delete("/api/wo-package-types/{id}", woPackageType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoPackageType> woPackageTypeList = woPackageTypeRepository.findAll();
        assertThat(woPackageTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoPackageType.class);
        WoPackageType woPackageType1 = new WoPackageType();
        woPackageType1.setId(1L);
        WoPackageType woPackageType2 = new WoPackageType();
        woPackageType2.setId(woPackageType1.getId());
        assertThat(woPackageType1).isEqualTo(woPackageType2);
        woPackageType2.setId(2L);
        assertThat(woPackageType1).isNotEqualTo(woPackageType2);
        woPackageType1.setId(null);
        assertThat(woPackageType1).isNotEqualTo(woPackageType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoPackageTypeDTO.class);
        WoPackageTypeDTO woPackageTypeDTO1 = new WoPackageTypeDTO();
        woPackageTypeDTO1.setId(1L);
        WoPackageTypeDTO woPackageTypeDTO2 = new WoPackageTypeDTO();
        assertThat(woPackageTypeDTO1).isNotEqualTo(woPackageTypeDTO2);
        woPackageTypeDTO2.setId(woPackageTypeDTO1.getId());
        assertThat(woPackageTypeDTO1).isEqualTo(woPackageTypeDTO2);
        woPackageTypeDTO2.setId(2L);
        assertThat(woPackageTypeDTO1).isNotEqualTo(woPackageTypeDTO2);
        woPackageTypeDTO1.setId(null);
        assertThat(woPackageTypeDTO1).isNotEqualTo(woPackageTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woPackageTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woPackageTypeMapper.fromId(null)).isNull();
    }
}
