package com.java.service.mapper;

import com.java.domain.*;
import com.java.service.dto.WoWorkOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoWorkOrder} and its DTO {@link WoWorkOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {WoCustomsBrokerageMapper.class, WoStorageMapper.class, WoPickPackMapper.class, WoShippingInfoMapper.class})
public interface WoWorkOrderMapper extends EntityMapper<WoWorkOrderDTO, WoWorkOrder> {

    @Mapping(source = "woCustomsBrokerage.id", target = "woCustomsBrokerageId")
    @Mapping(source = "woStorage.id", target = "woStorageId")
    @Mapping(source = "woPickPack.id", target = "woPickPackId")
    @Mapping(source = "woShippingInfo.id", target = "woShippingInfoId")
    WoWorkOrderDTO toDto(WoWorkOrder woWorkOrder);

    @Mapping(source = "woCustomsBrokerageId", target = "woCustomsBrokerage")
    @Mapping(source = "woStorageId", target = "woStorage")
    @Mapping(source = "woPickPackId", target = "woPickPack")
    @Mapping(source = "woShippingInfoId", target = "woShippingInfo")
    WoWorkOrder toEntity(WoWorkOrderDTO woWorkOrderDTO);

    default WoWorkOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoWorkOrder woWorkOrder = new WoWorkOrder();
        woWorkOrder.setId(id);
        return woWorkOrder;
    }
}
