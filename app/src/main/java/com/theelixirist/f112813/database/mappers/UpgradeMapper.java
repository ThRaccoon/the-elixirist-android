package com.theelixirist.f112813.database.mappers;

import com.theelixirist.f112813.database.dtos.UpgradeDto;
import com.theelixirist.f112813.database.entities.UpgradeEntity;
import com.theelixirist.f112813.game.runtime.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class UpgradeMapper {
    private UpgradeMapper() {
    }

    public static UpgradeEntity toEntity(UpgradeDto dto) {
        UpgradeEntity entity = new UpgradeEntity();
        entity.id = dto.id;
        return entity;
    }

    public static UpgradeDto toDto(UpgradeEntity entity) {
        UpgradeDto dto = new UpgradeDto();
        dto.id = entity.id;
        return dto;
    }

    public static List<UpgradeDto> toDtoList(List<UpgradeEntity> entities) {
        List<UpgradeDto> dtos = new ArrayList<>();
        for (UpgradeEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static UpgradeDto fromRuntime(Upgrade upgrade) {
        UpgradeDto dto = new UpgradeDto();
        dto.id = upgrade.getId();
        return dto;
    }

    public static Upgrade toRuntime(UpgradeDto dto) {
        return new Upgrade(
                dto.id
        );
    }
}
