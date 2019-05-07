package com.java.service.mapper;

import com.java.domain.*;
import com.java.service.dto.WoPackageTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoPackageType} and its DTO {@link WoPackageTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoPackageTypeMapper extends EntityMapper<WoPackageTypeDTO, WoPackageType> {


    @Mapping(target = "woStorage", ignore = true)
    WoPackageType toEntity(WoPackageTypeDTO woPackageTypeDTO);

    default WoPackageType fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoPackageType woPackageType = new WoPackageType();
        woPackageType.setId(id);
        return woPackageType;
    }
}
