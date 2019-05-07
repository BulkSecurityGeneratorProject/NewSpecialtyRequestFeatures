package com.java.web.rest;

import com.java.NewSpecialtyRequestFeaturesApp;
import com.java.domain.WoCustomsBrokerage;
import com.java.repository.WoCustomsBrokerageRepository;
import com.java.service.WoCustomsBrokerageService;
import com.java.service.dto.WoCustomsBrokerageDTO;
import com.java.service.mapper.WoCustomsBrokerageMapper;
import com.java.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.java.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link WoCustomsBrokerageResource} REST controller.
 */
@SpringBootTest(classes = NewSpecialtyRequestFeaturesApp.class)
public class WoCustomsBrokerageResourceIT {

    private static final String DEFAULT_MONTHLY_IMPORT_SHIPMENT = "AAAAAAAAAA";
    private static final String UPDATED_MONTHLY_IMPORT_SHIPMENT = "BBBBBBBBBB";

    private static final String DEFAULT_MONTHLY_EXPORT_SHIPMENT = "AAAAAAAAAA";
    private static final String UPDATED_MONTHLY_EXPORT_SHIPMENT = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPMENT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPMENT_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_INFO = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_INFO = "BBBBBBBBBB";

    @Autowired
    private WoCustomsBrokerageRepository woCustomsBrokerageRepository;

    @Mock
    private WoCustomsBrokerageRepository woCustomsBrokerageRepositoryMock;

    @Autowired
    private WoCustomsBrokerageMapper woCustomsBrokerageMapper;

    @Mock
    private WoCustomsBrokerageService woCustomsBrokerageServiceMock;

    @Autowired
    private WoCustomsBrokerageService woCustomsBrokerageService;

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

    private MockMvc restWoCustomsBrokerageMockMvc;

    private WoCustomsBrokerage woCustomsBrokerage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoCustomsBrokerageResource woCustomsBrokerageResource = new WoCustomsBrokerageResource(woCustomsBrokerageService);
        this.restWoCustomsBrokerageMockMvc = MockMvcBuilders.standaloneSetup(woCustomsBrokerageResource)
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
    public static WoCustomsBrokerage createEntity(EntityManager em) {
        WoCustomsBrokerage woCustomsBrokerage = new WoCustomsBrokerage()
            .monthlyImportShipment(DEFAULT_MONTHLY_IMPORT_SHIPMENT)
            .monthlyExportShipment(DEFAULT_MONTHLY_EXPORT_SHIPMENT)
            .shipmentValue(DEFAULT_SHIPMENT_VALUE)
            .productInfo(DEFAULT_PRODUCT_INFO);
        return woCustomsBrokerage;
    }

    @BeforeEach
    public void initTest() {
        woCustomsBrokerage = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoCustomsBrokerage() throws Exception {
        int databaseSizeBeforeCreate = woCustomsBrokerageRepository.findAll().size();

        // Create the WoCustomsBrokerage
        WoCustomsBrokerageDTO woCustomsBrokerageDTO = woCustomsBrokerageMapper.toDto(woCustomsBrokerage);
        restWoCustomsBrokerageMockMvc.perform(post("/api/wo-customs-brokerages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woCustomsBrokerageDTO)))
            .andExpect(status().isCreated());

        // Validate the WoCustomsBrokerage in the database
        List<WoCustomsBrokerage> woCustomsBrokerageList = woCustomsBrokerageRepository.findAll();
        assertThat(woCustomsBrokerageList).hasSize(databaseSizeBeforeCreate + 1);
        WoCustomsBrokerage testWoCustomsBrokerage = woCustomsBrokerageList.get(woCustomsBrokerageList.size() - 1);
        assertThat(testWoCustomsBrokerage.getMonthlyImportShipment()).isEqualTo(DEFAULT_MONTHLY_IMPORT_SHIPMENT);
        assertThat(testWoCustomsBrokerage.getMonthlyExportShipment()).isEqualTo(DEFAULT_MONTHLY_EXPORT_SHIPMENT);
        assertThat(testWoCustomsBrokerage.getShipmentValue()).isEqualTo(DEFAULT_SHIPMENT_VALUE);
        assertThat(testWoCustomsBrokerage.getProductInfo()).isEqualTo(DEFAULT_PRODUCT_INFO);
    }

    @Test
    @Transactional
    public void createWoCustomsBrokerageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woCustomsBrokerageRepository.findAll().size();

        // Create the WoCustomsBrokerage with an existing ID
        woCustomsBrokerage.setId(1L);
        WoCustomsBrokerageDTO woCustomsBrokerageDTO = woCustomsBrokerageMapper.toDto(woCustomsBrokerage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoCustomsBrokerageMockMvc.perform(post("/api/wo-customs-brokerages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woCustomsBrokerageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoCustomsBrokerage in the database
        List<WoCustomsBrokerage> woCustomsBrokerageList = woCustomsBrokerageRepository.findAll();
        assertThat(woCustomsBrokerageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoCustomsBrokerages() throws Exception {
        // Initialize the database
        woCustomsBrokerageRepository.saveAndFlush(woCustomsBrokerage);

        // Get all the woCustomsBrokerageList
        restWoCustomsBrokerageMockMvc.perform(get("/api/wo-customs-brokerages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woCustomsBrokerage.getId().intValue())))
            .andExpect(jsonPath("$.[*].monthlyImportShipment").value(hasItem(DEFAULT_MONTHLY_IMPORT_SHIPMENT.toString())))
            .andExpect(jsonPath("$.[*].monthlyExportShipment").value(hasItem(DEFAULT_MONTHLY_EXPORT_SHIPMENT.toString())))
            .andExpect(jsonPath("$.[*].shipmentValue").value(hasItem(DEFAULT_SHIPMENT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].productInfo").value(hasItem(DEFAULT_PRODUCT_INFO.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllWoCustomsBrokeragesWithEagerRelationshipsIsEnabled() throws Exception {
        WoCustomsBrokerageResource woCustomsBrokerageResource = new WoCustomsBrokerageResource(woCustomsBrokerageServiceMock);
        when(woCustomsBrokerageServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restWoCustomsBrokerageMockMvc = MockMvcBuilders.standaloneSetup(woCustomsBrokerageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restWoCustomsBrokerageMockMvc.perform(get("/api/wo-customs-brokerages?eagerload=true"))
        .andExpect(status().isOk());

        verify(woCustomsBrokerageServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllWoCustomsBrokeragesWithEagerRelationshipsIsNotEnabled() throws Exception {
        WoCustomsBrokerageResource woCustomsBrokerageResource = new WoCustomsBrokerageResource(woCustomsBrokerageServiceMock);
            when(woCustomsBrokerageServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restWoCustomsBrokerageMockMvc = MockMvcBuilders.standaloneSetup(woCustomsBrokerageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restWoCustomsBrokerageMockMvc.perform(get("/api/wo-customs-brokerages?eagerload=true"))
        .andExpect(status().isOk());

            verify(woCustomsBrokerageServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getWoCustomsBrokerage() throws Exception {
        // Initialize the database
        woCustomsBrokerageRepository.saveAndFlush(woCustomsBrokerage);

        // Get the woCustomsBrokerage
        restWoCustomsBrokerageMockMvc.perform(get("/api/wo-customs-brokerages/{id}", woCustomsBrokerage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woCustomsBrokerage.getId().intValue()))
            .andExpect(jsonPath("$.monthlyImportShipment").value(DEFAULT_MONTHLY_IMPORT_SHIPMENT.toString()))
            .andExpect(jsonPath("$.monthlyExportShipment").value(DEFAULT_MONTHLY_EXPORT_SHIPMENT.toString()))
            .andExpect(jsonPath("$.shipmentValue").value(DEFAULT_SHIPMENT_VALUE.toString()))
            .andExpect(jsonPath("$.productInfo").value(DEFAULT_PRODUCT_INFO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoCustomsBrokerage() throws Exception {
        // Get the woCustomsBrokerage
        restWoCustomsBrokerageMockMvc.perform(get("/api/wo-customs-brokerages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoCustomsBrokerage() throws Exception {
        // Initialize the database
        woCustomsBrokerageRepository.saveAndFlush(woCustomsBrokerage);

        int databaseSizeBeforeUpdate = woCustomsBrokerageRepository.findAll().size();

        // Update the woCustomsBrokerage
        WoCustomsBrokerage updatedWoCustomsBrokerage = woCustomsBrokerageRepository.findById(woCustomsBrokerage.getId()).get();
        // Disconnect from session so that the updates on updatedWoCustomsBrokerage are not directly saved in db
        em.detach(updatedWoCustomsBrokerage);
        updatedWoCustomsBrokerage
            .monthlyImportShipment(UPDATED_MONTHLY_IMPORT_SHIPMENT)
            .monthlyExportShipment(UPDATED_MONTHLY_EXPORT_SHIPMENT)
            .shipmentValue(UPDATED_SHIPMENT_VALUE)
            .productInfo(UPDATED_PRODUCT_INFO);
        WoCustomsBrokerageDTO woCustomsBrokerageDTO = woCustomsBrokerageMapper.toDto(updatedWoCustomsBrokerage);

        restWoCustomsBrokerageMockMvc.perform(put("/api/wo-customs-brokerages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woCustomsBrokerageDTO)))
            .andExpect(status().isOk());

        // Validate the WoCustomsBrokerage in the database
        List<WoCustomsBrokerage> woCustomsBrokerageList = woCustomsBrokerageRepository.findAll();
        assertThat(woCustomsBrokerageList).hasSize(databaseSizeBeforeUpdate);
        WoCustomsBrokerage testWoCustomsBrokerage = woCustomsBrokerageList.get(woCustomsBrokerageList.size() - 1);
        assertThat(testWoCustomsBrokerage.getMonthlyImportShipment()).isEqualTo(UPDATED_MONTHLY_IMPORT_SHIPMENT);
        assertThat(testWoCustomsBrokerage.getMonthlyExportShipment()).isEqualTo(UPDATED_MONTHLY_EXPORT_SHIPMENT);
        assertThat(testWoCustomsBrokerage.getShipmentValue()).isEqualTo(UPDATED_SHIPMENT_VALUE);
        assertThat(testWoCustomsBrokerage.getProductInfo()).isEqualTo(UPDATED_PRODUCT_INFO);
    }

    @Test
    @Transactional
    public void updateNonExistingWoCustomsBrokerage() throws Exception {
        int databaseSizeBeforeUpdate = woCustomsBrokerageRepository.findAll().size();

        // Create the WoCustomsBrokerage
        WoCustomsBrokerageDTO woCustomsBrokerageDTO = woCustomsBrokerageMapper.toDto(woCustomsBrokerage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoCustomsBrokerageMockMvc.perform(put("/api/wo-customs-brokerages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woCustomsBrokerageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoCustomsBrokerage in the database
        List<WoCustomsBrokerage> woCustomsBrokerageList = woCustomsBrokerageRepository.findAll();
        assertThat(woCustomsBrokerageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoCustomsBrokerage() throws Exception {
        // Initialize the database
        woCustomsBrokerageRepository.saveAndFlush(woCustomsBrokerage);

        int databaseSizeBeforeDelete = woCustomsBrokerageRepository.findAll().size();

        // Delete the woCustomsBrokerage
        restWoCustomsBrokerageMockMvc.perform(delete("/api/wo-customs-brokerages/{id}", woCustomsBrokerage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoCustomsBrokerage> woCustomsBrokerageList = woCustomsBrokerageRepository.findAll();
        assertThat(woCustomsBrokerageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoCustomsBrokerage.class);
        WoCustomsBrokerage woCustomsBrokerage1 = new WoCustomsBrokerage();
        woCustomsBrokerage1.setId(1L);
        WoCustomsBrokerage woCustomsBrokerage2 = new WoCustomsBrokerage();
        woCustomsBrokerage2.setId(woCustomsBrokerage1.getId());
        assertThat(woCustomsBrokerage1).isEqualTo(woCustomsBrokerage2);
        woCustomsBrokerage2.setId(2L);
        assertThat(woCustomsBrokerage1).isNotEqualTo(woCustomsBrokerage2);
        woCustomsBrokerage1.setId(null);
        assertThat(woCustomsBrokerage1).isNotEqualTo(woCustomsBrokerage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoCustomsBrokerageDTO.class);
        WoCustomsBrokerageDTO woCustomsBrokerageDTO1 = new WoCustomsBrokerageDTO();
        woCustomsBrokerageDTO1.setId(1L);
        WoCustomsBrokerageDTO woCustomsBrokerageDTO2 = new WoCustomsBrokerageDTO();
        assertThat(woCustomsBrokerageDTO1).isNotEqualTo(woCustomsBrokerageDTO2);
        woCustomsBrokerageDTO2.setId(woCustomsBrokerageDTO1.getId());
        assertThat(woCustomsBrokerageDTO1).isEqualTo(woCustomsBrokerageDTO2);
        woCustomsBrokerageDTO2.setId(2L);
        assertThat(woCustomsBrokerageDTO1).isNotEqualTo(woCustomsBrokerageDTO2);
        woCustomsBrokerageDTO1.setId(null);
        assertThat(woCustomsBrokerageDTO1).isNotEqualTo(woCustomsBrokerageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woCustomsBrokerageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woCustomsBrokerageMapper.fromId(null)).isNull();
    }
}
