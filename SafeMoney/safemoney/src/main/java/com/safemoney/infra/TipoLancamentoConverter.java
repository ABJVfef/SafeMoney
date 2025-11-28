package com.safemoney.infra;

import com.safemoney.domains.enums.TipoLancamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TipoLancamentoConverter implements AttributeConverter<TipoLancamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoLancamento attribute) {
        return (attribute == null) ? null : attribute.getId();
    }

    @Override
    public TipoLancamento convertToEntityAttribute(Integer dbData) {
        return (dbData == null) ? null : TipoLancamento.toEnum(dbData);
    }
}