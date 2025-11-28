package com.safemoney.infra;

import com.safemoney.domains.enums.StatusLancamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusLancamentoConverter implements AttributeConverter<StatusLancamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusLancamento attribute) {
        return (attribute == null) ? null : attribute.getId();
    }

    @Override
    public StatusLancamento convertToEntityAttribute(Integer dbData) {
        return (dbData == null) ? null : StatusLancamento.toEnum(dbData);
    }
}