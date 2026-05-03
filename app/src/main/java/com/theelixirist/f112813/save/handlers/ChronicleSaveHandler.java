package com.theelixirist.f112813.save.handlers;

import com.theelixirist.f112813.database.dtos.ChronicleDto;
import com.theelixirist.f112813.database.repositories.ChronicleRepository;
import com.theelixirist.f112813.domain.mappers.ChronicleDomainMapper;
import com.theelixirist.f112813.domain.models.Chronicle;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.save.Saveable;

public class ChronicleSaveHandler implements Saveable<Chronicle> {
    private final Chronicle chronicle;
    private final ChronicleRepository chronicleRepository;

    ChronicleSaveHandler(Chronicle chronicle, ChronicleRepository chronicleRepository) {
        this.chronicle = chronicle;
        this.chronicleRepository = chronicleRepository;
    }

    @Override
    public Chronicle load() {
        ChronicleDto dto = chronicleRepository.read();
        if (dto != null) {
            return ChronicleDomainMapper.toDomain(dto);
        } else {
            return new Chronicle(
                    System.currentTimeMillis(),
                    System.currentTimeMillis(),
                    BigDouble.ZERO,
                    BigDouble.ZERO,
                    0,
                    0
            );
        }
    }

    @Override
    public void save() {
        chronicleRepository.upsert(ChronicleDomainMapper.toDto(chronicle));
    }
}
