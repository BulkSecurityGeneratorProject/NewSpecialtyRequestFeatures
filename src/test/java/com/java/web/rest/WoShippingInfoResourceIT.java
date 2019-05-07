package com.java.web.rest;

import com.java.NewSpecialtyRequestFeaturesApp;
import com.java.domain.WoShippingInfo;
import com.java.repository.WoShippingInfoRepository;
import com.java.service.WoShippingInfoService;
import com.java.service.dto.WoShippingInfoDTO;
import com.java.service.mapper.WoShippingInfoMapper;
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
 * Integration tests for the {@Link WoShippingInfoResource} REST controller.
 */
@SpringBootTest(classes = NewSpecialtyRequestFeaturesApp.class)
public class WoShippingInfoResourceIT {

    private static final Integer DEFAULT_MONTHLY_SHIP_VOLUME = 20;
    private static final Integer UPDATED_MONTHLY_SHIP_VOLUME = 19;

    private static final Boolean DEFAULT_IS_CUSTOMS_BROKERAGE = false;
    private static final Boolean UPDATED_IS_CUSTOMS_BROKERAGE = true;

    @Autowired
    private WoShippingInfoRepository woShippingInfoRepository;

    @Mock
    private WoShippingInfoRepository woShippingInfoRepositoryMock;

    @Autowired
    private WoShippingInfoMapper woShippingInfoMapper;

    @Mock
    private WoShippingInfoService woShippingInfoServiceMock;

    @Autowired
    private WoShippingInfoService woShippingInfoService;

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

    private MockMvc restWoShippingInfoMockMvc;

    private WoShippingInfo woShippingInfo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoShippingInfoResource woShippingInfoResource = new WoShippingInfoResource(woShippingInfoService);
        this.restWoShippingInfoMockMvc = MockMvcBuilders.standaloneSetup(woShippingInfoResource)
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
    public static WoShippingInfo createEntity(EntityManager em) {
        WoShippingInfo woShippingInfo = new WoShippingInfo()
            .monthlyShipVolume(DEFAULT_MONTHLY_SHIP_VOLUME)
            .isCustomsBrokerage(DEFAULT_IS_CUSTOMS_BROKERAGE);
        return woShippingInfo;
    }

    @BeforeEach
    public void initTest() {
        woShippingInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoShippingInfo() throws Exception {
        int databaseSizeBeforeCreate = woShippingInfoRepository.findAll().size();

        // Create the WoShippingInfo
        WoShippingInfoDTO woShippingInfoDTO = woShippingInfoMapper.toDto(woShippingInfo);
        restWoShippingInfoMockMvc.perform(post("/api/wo-shipping-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woShippingInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the WoShippingInfo in the database
        List<WoShippingInfo> woShippingInfoList = woShippingInfoRepository.findAll();
        assertThat(woShippingInfoList).hasSize(databaseSizeBeforeCreate + 1);
        WoShippingInfo testWoShippingInfo = woShippingInfoList.get(woShippingInfoList.size() - 1);
        assertThat(testWoShippingInfo.getMonthlyShipVolume()).isEqualTo(DEFAULT_MONTHLY_SHIP_VOLUME);
        assertThat(testWoShippingInfo.isIsCustomsBrokerage()).isEqualTo(DEFAULT_IS_CUSTOMS_BROKERAGE);
    }

    @Test
    @Transactional
    public void createWoShippingInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woShippingInfoRepository.findAll().size();

        // Create the WoShippingInfo with an existing ID
        woShippingInfo.setId(1L);
        WoShippingInfoDTO woShippingInfoDTO = woShippingInfoMapper.toDto(woShippingInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoShippingInfoMockMvc.perform(post("/api/wo-shipping-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woShippingInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoShippingInfo in the database
        List<WoShippingInfo> woShippingInfoList = woShippingInfoRepository.findAll();
        assertThat(woShippingInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoShippingInfos() throws Exception {
        // Initialize the database
        woShippingInfoRepository.saveAndFlush(woShippingInfo);

        // Get all the woShippingInfoList
        restWoShippingInfoMockMvc.perform(get("/api/wo-shipping-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woShippingInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].monthlyShipVolume").value(hasItem(DEFAULT_MONTHLY_SHIP_VOLUME)))
            .andExpect(jsonPath("$.[*].isCustomsBrokerage").value(hasItem(DEFAULT_IS_CUSTOMS_BROKERAGE.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllWoShippingInfosWithEagerRelationshipsIsEnabled() throws Exception {
        WoShippingInfoResource woShippingInfoResource = new WoShippingInfoResource(woShippingInfoServiceMock);
        when(woShippingInfoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restWoShippingInfoMockMvc = MockMvcBuilders.standaloneSetup(woShippingInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restWoShippingInfoMockMvc.perform(get("/api/wo-shipping-infos?eagerload=true"))
        .andExpect(status().isOk());

        verify(woShippingInfoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllWoShippingInfosWithEagerRelationshipsIsNotEnabled() throws Exception {
        WoShippingInfoResource woShippingInfoResource = new WoShippingInfoResource(woShippingInfoServiceMock);
            when(woShippingInfoServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restWoShippingInfoMockMvc = MockMvcBuilders.standaloneSetup(woShippingInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restWoShippingInfoMockMvc.perform(get("/api/wo-shipping-infos?eagerload=true"))
        .andExpect(status().isOk());

            verify(woShippingInfoServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getWoShippingInfo() throws Exception {
        // Initialize the database
        woShippingInfoRepository.saveAndFlush(woShippingInfo);

        // Get the woShippingInfo
        restWoShippingInfoMockMvc.perform(get("/api/wo-shipping-infos/{id}", woShippingInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woShippingInfo.getId().intValue()))
            .andExpect(jsonPath("$.monthlyShipVolume").value(DEFAULT_MONTHLY_SHIP_VOLUME))
            .andExpect(jsonPath("$.isCustomsBrokerage").value(DEFAULT_IS_CUSTOMS_BROKERAGE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWoShippingInfo() throws Exception {
        // Get the woShippingInfo
        restWoShippingInfoMockMvc.perform(get("/api/wo-shipping-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoShippingInfo() throws Exception {
        // Initialize the database
        woShippingInfoRepository.saveAndFlush(woShippingInfo);

        int databaseSizeBeforeUpdate = woShippingInfoRepository.findAll().size();

        // Update the woShippingInfo
        WoShippingInfo updatedWoShippingInfo = woShippingInfoRepository.findById(woShippingInfo.getId()).get();
        // Disconnect from session so that the updates on updatedWoShippingInfo are not directly saved in db
        em.detach(updatedWoShippingInfo);
        updatedWoShippingInfo
            .monthlyShipVolume(UPDATED_MONTHLY_SHIP_VOLUME)
            .isCustomsBrokerage(UPDATED_IS_CUSTOMS_BROKERAGE);
        WoShippingInfoDTO woShippingInfoDTO = woShippingInfoMapper.toDto(updatedWoShippingInfo);

        restWoShippingInfoMockMvc.perform(put("/api/wo-shipping-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woShippingInfoDTO)))
            .andExpect(status().isOk());

        // Validate the WoShippingInfo in the database
        List<WoShippingInfo> woShippingInfoList = woShippingInfoRepository.findAll();
        assertThat(woShippingInfoList).hasSize(databaseSizeBeforeUpdate);
        WoShippingInfo testWoShippingInfo = woShippingInfoList.get(woShippingInfoList.size() - 1);
        assertThat(testWoShippingInfo.getMonthlyShipVolume()).isEqualTo(UPDATED_MONTHLY_SHIP_VOLUME);
        assertThat(testWoShippingInfo.isIsCustomsBrokerage()).isEqualTo(UPDATED_IS_CUSTOMS_BROKERAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingWoShippingInfo() throws Exception {
        int databaseSizeBeforeUpdate = woShippingInfoRepository.findAll().size();

        // Create the WoShippingInfo
        WoShippingInfoDTO woShippingInfoDTO = woShippingInfoMapper.toDto(woShippingInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoShippingInfoMockMvc.perform(put("/api/wo-shipping-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woShippingInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoShippingInfo in the database
        List<WoShippingInfo> woShippingInfoList = woShippingInfoRepository.findAll();
        assertThat(woShippingInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoShippingInfo() throws Exception {
        // Initialize the database
        woShippingInfoRepository.saveAndFlush(woShippingInfo);

        int databaseSizeBeforeDelete = woShippingInfoRepository.findAll().size();

        // Delete the woShippingInfo
        restWoShippingInfoMockMvc.perform(delete("/api/wo-shipping-infos/{id}", woShippingInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoShippingInfo> woShippingInfoList = woShippingInfoRepository.findAll();
        assertThat(woShippingInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoShippingInfo.class);
        WoShippingInfo woShippingInfo1 = new WoShippingInfo();
        woShippingInfo1.setId(1L);
        WoShippingInfo woShippingInfo2 = new WoShippingInfo();
        woShippingInfo2.setId(woShippingInfo1.getId());
        assertThat(woShippingInfo1).isEqualTo(woShippingInfo2);
        woShippingInfo2.setId(2L);
        assertThat(woShippingInfo1).isNotEqualTo(woShippingInfo2);
        woShippingInfo1.setId(null);
        assertThat(woShippingInfo1).isNotEqualTo(woShippingInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoShippingInfoDTO.class);
        WoShippingInfoDTO woShippingInfoDTO1 = new WoShippingInfoDTO();
        woShippingInfoDTO1.setId(1L);
        WoShippingInfoDTO woShippingInfoDTO2 = new WoShippingInfoDTO();
        assertThat(woShippingInfoDTO1).isNotEqualTo(woShippingInfoDTO2);
        woShippingInfoDTO2.setId(woShippingInfoDTO1.getId());
        assertThat(woShippingInfoDTO1).isEqualTo(woShippingInfoDTO2);
        woShippingInfoDTO2.setId(2L);
        assertThat(woShippingInfoDTO1).isNotEqualTo(woShippingInfoDTO2);
        woShippingInfoDTO1.setId(null);
        assertThat(woShippingInfoDTO1).isNotEqualTo(woShippingInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woShippingInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woShippingInfoMapper.fromId(null)).isNull();
    }
}
