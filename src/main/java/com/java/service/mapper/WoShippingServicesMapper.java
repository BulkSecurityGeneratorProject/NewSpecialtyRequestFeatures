package com.java.service.mapper;

import com.java.domain.*;
import com.java.service.dto.WoShippingServicesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoShippingServices} and its DTO {@link WoShippingServicesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoShippingServicesMapper extends EntityMapper<WoShippingServicesDTO, WoShippingServices> {


    @Mapping(target = "woShippingInfos", ignore = true)
    WoShippingServices toEntity(WoShippingServicesDTO woShippingServicesDTO);

    default WoShippingServices fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoShippingServices woShippingServices = new WoShippingServices();
        woShippingServices.setId(id);
        return woShippingServices;
    }
}
