package com.java.service.mapper;

import com.java.domain.*;
import com.java.service.dto.WoAdditionalServicesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoAdditionalServices} and its DTO {@link WoAdditionalServicesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoAdditionalServicesMapper extends EntityMapper<WoAdditionalServicesDTO, WoAdditionalServices> {


    @Mapping(target = "woPickPacks", ignore = true)
    WoAdditionalServices toEntity(WoAdditionalServicesDTO woAdditionalServicesDTO);

    default WoAdditionalServices fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoAdditionalServices woAdditionalServices = new WoAdditionalServices();
        woAdditionalServices.setId(id);
        return woAdditionalServices;
    }
}
