package com.java.service.mapper;

import com.java.domain.*;
import com.java.service.dto.WoTransportModesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoTransportModes} and its DTO {@link WoTransportModesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoTransportModesMapper extends EntityMapper<WoTransportModesDTO, WoTransportModes> {


    @Mapping(target = "woCustomsBrokerages", ignore = true)
    WoTransportModes toEntity(WoTransportModesDTO woTransportModesDTO);

    default WoTransportModes fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoTransportModes woTransportModes = new WoTransportModes();
        woTransportModes.setId(id);
        return woTransportModes;
    }
}
