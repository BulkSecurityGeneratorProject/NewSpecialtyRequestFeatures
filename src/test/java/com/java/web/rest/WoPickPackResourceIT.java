package com.java.web.rest;

import com.java.NewSpecialtyRequestFeaturesApp;
import com.java.domain.WoPickPack;
import com.java.repository.WoPickPackRepository;
import com.java.service.WoPickPackService;
import com.java.service.dto.WoPickPackDTO;
import com.java.service.mapper.WoPickPackMapper;
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
 * Integration tests for the {@Link WoPickPackResource} REST controller.
 */
@SpringBootTest(classes = NewSpecialtyRequestFeaturesApp.class)
public class WoPickPackResourceIT {

    private static final Float DEFAULT_SKU = 1F;
    private static final Float UPDATED_SKU = 2F;

    private static final Float DEFAULT_AVG_ORDERS = 1F;
    private static final Float UPDATED_AVG_ORDERS = 2F;

    private static final Float DEFAULT_SHIPMENT_WEIGHT = 1F;
    private static final Float UPDATED_SHIPMENT_WEIGHT = 2F;

    private static final Integer DEFAULT_SHIPMENT_SIZE = 20;
    private static final Integer UPDATED_SHIPMENT_SIZE = 19;

    private static final Integer DEFAULT_MONTHLY_VOLUME = 20;
    private static final Integer UPDATED_MONTHLY_VOLUME = 19;

    private static final Boolean DEFAULT_IS_PROMOTIONAL_INSERTS = false;
    private static final Boolean UPDATED_IS_PROMOTIONAL_INSERTS = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private WoPickPackRepository woPickPackRepository;

    @Mock
    private WoPickPackRepository woPickPackRepositoryMock;

    @Autowired
    private WoPickPackMapper woPickPackMapper;

    @Mock
    private WoPickPackService woPickPackServiceMock;

    @Autowired
    private WoPickPackService woPickPackService;

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

    private MockMvc restWoPickPackMockMvc;

    private WoPickPack woPickPack;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoPickPackResource woPickPackResource = new WoPickPackResource(woPickPackService);
        this.restWoPickPackMockMvc = MockMvcBuilders.standaloneSetup(woPickPackResource)
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
    public static WoPickPack createEntity(EntityManager em) {
        WoPickPack woPickPack = new WoPickPack()
            .sku(DEFAULT_SKU)
            .avgOrders(DEFAULT_AVG_ORDERS)
            .shipmentWeight(DEFAULT_SHIPMENT_WEIGHT)
            .shipmentSize(DEFAULT_SHIPMENT_SIZE)
            .monthlyVolume(DEFAULT_MONTHLY_VOLUME)
            .isPromotionalInserts(DEFAULT_IS_PROMOTIONAL_INSERTS)
            .description(DEFAULT_DESCRIPTION);
        return woPickPack;
    }

    @BeforeEach
    public void initTest() {
        woPickPack = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoPickPack() throws Exception {
        int databaseSizeBeforeCreate = woPickPackRepository.findAll().size();

        // Create the WoPickPack
        WoPickPackDTO woPickPackDTO = woPickPackMapper.toDto(woPickPack);
        restWoPickPackMockMvc.perform(post("/api/wo-pick-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPickPackDTO)))
            .andExpect(status().isCreated());

        // Validate the WoPickPack in the database
        List<WoPickPack> woPickPackList = woPickPackRepository.findAll();
        assertThat(woPickPackList).hasSize(databaseSizeBeforeCreate + 1);
        WoPickPack testWoPickPack = woPickPackList.get(woPickPackList.size() - 1);
        assertThat(testWoPickPack.getSku()).isEqualTo(DEFAULT_SKU);
        assertThat(testWoPickPack.getAvgOrders()).isEqualTo(DEFAULT_AVG_ORDERS);
        assertThat(testWoPickPack.getShipmentWeight()).isEqualTo(DEFAULT_SHIPMENT_WEIGHT);
        assertThat(testWoPickPack.getShipmentSize()).isEqualTo(DEFAULT_SHIPMENT_SIZE);
        assertThat(testWoPickPack.getMonthlyVolume()).isEqualTo(DEFAULT_MONTHLY_VOLUME);
        assertThat(testWoPickPack.isIsPromotionalInserts()).isEqualTo(DEFAULT_IS_PROMOTIONAL_INSERTS);
        assertThat(testWoPickPack.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createWoPickPackWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woPickPackRepository.findAll().size();

        // Create the WoPickPack with an existing ID
        woPickPack.setId(1L);
        WoPickPackDTO woPickPackDTO = woPickPackMapper.toDto(woPickPack);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoPickPackMockMvc.perform(post("/api/wo-pick-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPickPackDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoPickPack in the database
        List<WoPickPack> woPickPackList = woPickPackRepository.findAll();
        assertThat(woPickPackList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoPickPacks() throws Exception {
        // Initialize the database
        woPickPackRepository.saveAndFlush(woPickPack);

        // Get all the woPickPackList
        restWoPickPackMockMvc.perform(get("/api/wo-pick-packs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woPickPack.getId().intValue())))
            .andExpect(jsonPath("$.[*].sku").value(hasItem(DEFAULT_SKU.doubleValue())))
            .andExpect(jsonPath("$.[*].avgOrders").value(hasItem(DEFAULT_AVG_ORDERS.doubleValue())))
            .andExpect(jsonPath("$.[*].shipmentWeight").value(hasItem(DEFAULT_SHIPMENT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].shipmentSize").value(hasItem(DEFAULT_SHIPMENT_SIZE)))
            .andExpect(jsonPath("$.[*].monthlyVolume").value(hasItem(DEFAULT_MONTHLY_VOLUME)))
            .andExpect(jsonPath("$.[*].isPromotionalInserts").value(hasItem(DEFAULT_IS_PROMOTIONAL_INSERTS.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllWoPickPacksWithEagerRelationshipsIsEnabled() throws Exception {
        WoPickPackResource woPickPackResource = new WoPickPackResource(woPickPackServiceMock);
        when(woPickPackServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restWoPickPackMockMvc = MockMvcBuilders.standaloneSetup(woPickPackResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restWoPickPackMockMvc.perform(get("/api/wo-pick-packs?eagerload=true"))
        .andExpect(status().isOk());

        verify(woPickPackServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllWoPickPacksWithEagerRelationshipsIsNotEnabled() throws Exception {
        WoPickPackResource woPickPackResource = new WoPickPackResource(woPickPackServiceMock);
            when(woPickPackServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restWoPickPackMockMvc = MockMvcBuilders.standaloneSetup(woPickPackResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restWoPickPackMockMvc.perform(get("/api/wo-pick-packs?eagerload=true"))
        .andExpect(status().isOk());

            verify(woPickPackServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getWoPickPack() throws Exception {
        // Initialize the database
        woPickPackRepository.saveAndFlush(woPickPack);

        // Get the woPickPack
        restWoPickPackMockMvc.perform(get("/api/wo-pick-packs/{id}", woPickPack.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woPickPack.getId().intValue()))
            .andExpect(jsonPath("$.sku").value(DEFAULT_SKU.doubleValue()))
            .andExpect(jsonPath("$.avgOrders").value(DEFAULT_AVG_ORDERS.doubleValue()))
            .andExpect(jsonPath("$.shipmentWeight").value(DEFAULT_SHIPMENT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.shipmentSize").value(DEFAULT_SHIPMENT_SIZE))
            .andExpect(jsonPath("$.monthlyVolume").value(DEFAULT_MONTHLY_VOLUME))
            .andExpect(jsonPath("$.isPromotionalInserts").value(DEFAULT_IS_PROMOTIONAL_INSERTS.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoPickPack() throws Exception {
        // Get the woPickPack
        restWoPickPackMockMvc.perform(get("/api/wo-pick-packs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoPickPack() throws Exception {
        // Initialize the database
        woPickPackRepository.saveAndFlush(woPickPack);

        int databaseSizeBeforeUpdate = woPickPackRepository.findAll().size();

        // Update the woPickPack
        WoPickPack updatedWoPickPack = woPickPackRepository.findById(woPickPack.getId()).get();
        // Disconnect from session so that the updates on updatedWoPickPack are not directly saved in db
        em.detach(updatedWoPickPack);
        updatedWoPickPack
            .sku(UPDATED_SKU)
            .avgOrders(UPDATED_AVG_ORDERS)
            .shipmentWeight(UPDATED_SHIPMENT_WEIGHT)
            .shipmentSize(UPDATED_SHIPMENT_SIZE)
            .monthlyVolume(UPDATED_MONTHLY_VOLUME)
            .isPromotionalInserts(UPDATED_IS_PROMOTIONAL_INSERTS)
            .description(UPDATED_DESCRIPTION);
        WoPickPackDTO woPickPackDTO = woPickPackMapper.toDto(updatedWoPickPack);

        restWoPickPackMockMvc.perform(put("/api/wo-pick-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPickPackDTO)))
            .andExpect(status().isOk());

        // Validate the WoPickPack in the database
        List<WoPickPack> woPickPackList = woPickPackRepository.findAll();
        assertThat(woPickPackList).hasSize(databaseSizeBeforeUpdate);
        WoPickPack testWoPickPack = woPickPackList.get(woPickPackList.size() - 1);
        assertThat(testWoPickPack.getSku()).isEqualTo(UPDATED_SKU);
        assertThat(testWoPickPack.getAvgOrders()).isEqualTo(UPDATED_AVG_ORDERS);
        assertThat(testWoPickPack.getShipmentWeight()).isEqualTo(UPDATED_SHIPMENT_WEIGHT);
        assertThat(testWoPickPack.getShipmentSize()).isEqualTo(UPDATED_SHIPMENT_SIZE);
        assertThat(testWoPickPack.getMonthlyVolume()).isEqualTo(UPDATED_MONTHLY_VOLUME);
        assertThat(testWoPickPack.isIsPromotionalInserts()).isEqualTo(UPDATED_IS_PROMOTIONAL_INSERTS);
        assertThat(testWoPickPack.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingWoPickPack() throws Exception {
        int databaseSizeBeforeUpdate = woPickPackRepository.findAll().size();

        // Create the WoPickPack
        WoPickPackDTO woPickPackDTO = woPickPackMapper.toDto(woPickPack);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoPickPackMockMvc.perform(put("/api/wo-pick-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woPickPackDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoPickPack in the database
        List<WoPickPack> woPickPackList = woPickPackRepository.findAll();
        assertThat(woPickPackList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoPickPack() throws Exception {
        // Initialize the database
        woPickPackRepository.saveAndFlush(woPickPack);

        int databaseSizeBeforeDelete = woPickPackRepository.findAll().size();

        // Delete the woPickPack
        restWoPickPackMockMvc.perform(delete("/api/wo-pick-packs/{id}", woPickPack.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoPickPack> woPickPackList = woPickPackRepository.findAll();
        assertThat(woPickPackList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoPickPack.class);
        WoPickPack woPickPack1 = new WoPickPack();
        woPickPack1.setId(1L);
        WoPickPack woPickPack2 = new WoPickPack();
        woPickPack2.setId(woPickPack1.getId());
        assertThat(woPickPack1).isEqualTo(woPickPack2);
        woPickPack2.setId(2L);
        assertThat(woPickPack1).isNotEqualTo(woPickPack2);
        woPickPack1.setId(null);
        assertThat(woPickPack1).isNotEqualTo(woPickPack2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoPickPackDTO.class);
        WoPickPackDTO woPickPackDTO1 = new WoPickPackDTO();
        woPickPackDTO1.setId(1L);
        WoPickPackDTO woPickPackDTO2 = new WoPickPackDTO();
        assertThat(woPickPackDTO1).isNotEqualTo(woPickPackDTO2);
        woPickPackDTO2.setId(woPickPackDTO1.getId());
        assertThat(woPickPackDTO1).isEqualTo(woPickPackDTO2);
        woPickPackDTO2.setId(2L);
        assertThat(woPickPackDTO1).isNotEqualTo(woPickPackDTO2);
        woPickPackDTO1.setId(null);
        assertThat(woPickPackDTO1).isNotEqualTo(woPickPackDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woPickPackMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woPickPackMapper.fromId(null)).isNull();
    }
}
