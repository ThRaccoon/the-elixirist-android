package com.theelixirist.f112813.domain.mappers;

import com.theelixirist.f112813.database.dtos.UpgradeDto;
import com.theelixirist.f112813.domain.models.Upgrade;

public class UpgradeDomainMapper {
    private UpgradeDomainMapper() {
    }

    public static Upgrade toDomain(UpgradeDto dto) {
        return new Upgrade(
                dto.id
        );
    }

    public static UpgradeDto toDto(Upgrade upgrade) {
        UpgradeDto dto = new UpgradeDto();
        dto.id = upgrade.getId();
        return dto;
    }
}
