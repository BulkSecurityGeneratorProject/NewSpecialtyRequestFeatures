package com.java.service.mapper;

import com.java.domain.*;
import com.java.service.dto.WoStorageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoStorage} and its DTO {@link WoStorageDTO}.
 */
@Mapper(componentModel = "spring", uses = {WoPackageTypeMapper.class})
public interface WoStorageMapper extends EntityMapper<WoStorageDTO, WoStorage> {

    @Mapping(source = "woPackageType.id", target = "woPackageTypeId")
    WoStorageDTO toDto(WoStorage woStorage);

    @Mapping(source = "woPackageTypeId", target = "woPackageType")
    @Mapping(target = "woWorkOrder", ignore = true)
    WoStorage toEntity(WoStorageDTO woStorageDTO);

    default WoStorage fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoStorage woStorage = new WoStorage();
        woStorage.setId(id);
        return woStorage;
    }
}
