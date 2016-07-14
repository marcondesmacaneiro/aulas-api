package br.com.example.infrastructure.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime attribute) {
        if (Objects.nonNull(attribute)) {
            return Date.from(attribute.toInstant(ZoneOffset.UTC));
        }
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        if (Objects.nonNull(dbData)) {
            return dbData.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        }
        return null;
    }
}
