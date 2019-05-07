package com.java.service.mapper;

import com.java.domain.*;
import com.java.service.dto.WoShippingInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoShippingInfo} and its DTO {@link WoShippingInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {WoShippingServicesMapper.class})
public interface WoShippingInfoMapper extends EntityMapper<WoShippingInfoDTO, WoShippingInfo> {


    @Mapping(target = "woWorkOrder", ignore = true)
    WoShippingInfo toEntity(WoShippingInfoDTO woShippingInfoDTO);

    default WoShippingInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoShippingInfo woShippingInfo = new WoShippingInfo();
        woShippingInfo.setId(id);
        return woShippingInfo;
    }
}
