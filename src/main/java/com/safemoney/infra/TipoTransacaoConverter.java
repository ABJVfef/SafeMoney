package com.safemoney.infra;

import com.safemoney.domains.enums.TipoTransacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TipoTransacaoConverter implements AttributeConverter<TipoTransacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoTransacao attribute) {
        return (attribute == null) ? null : attribute.getId();
    }

    @Override
    public TipoTransacao convertToEntityAttribute(Integer dbData) {
        return (dbData == null) ? null : TipoTransacao.toEnum(dbData);
    }
}