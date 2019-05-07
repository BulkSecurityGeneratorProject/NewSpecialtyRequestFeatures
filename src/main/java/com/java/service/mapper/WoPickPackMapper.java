package com.java.service.mapper;

import com.java.domain.*;
import com.java.service.dto.WoPickPackDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoPickPack} and its DTO {@link WoPickPackDTO}.
 */
@Mapper(componentModel = "spring", uses = {WoAdditionalServicesMapper.class})
public interface WoPickPackMapper extends EntityMapper<WoPickPackDTO, WoPickPack> {


    @Mapping(target = "woWorkOrder", ignore = true)
    WoPickPack toEntity(WoPickPackDTO woPickPackDTO);

    default WoPickPack fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoPickPack woPickPack = new WoPickPack();
        woPickPack.setId(id);
        return woPickPack;
    }
}
