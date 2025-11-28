package com.safemoney.infra;

import com.safemoney.domains.enums.StatusFatura;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusFaturaConverter implements AttributeConverter<StatusFatura, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusFatura attribute) {
        return (attribute == null) ? null : attribute.getId();
    }

    @Override
    public StatusFatura convertToEntityAttribute(Integer dbData) {
        return (dbData == null) ? null : StatusFatura.toEnum(dbData);
    }
}