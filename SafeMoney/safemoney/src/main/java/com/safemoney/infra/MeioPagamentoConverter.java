package com.safemoney.infra;

import com.safemoney.domains.enums.MeioPagamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class MeioPagamentoConverter implements AttributeConverter<MeioPagamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MeioPagamento attribute) {
        return (attribute == null) ? null : attribute.getId();
    }

    @Override
    public MeioPagamento convertToEntityAttribute(Integer dbData) {
        return (dbData == null) ? null : MeioPagamento.toEnum(dbData);
    }
}