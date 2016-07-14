package br.com.example.infrastructure.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (Objects.nonNull(attribute)) {
            return Date.from(attribute.atStartOfDay().toInstant(ZoneOffset.UTC));
        }
        return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        if (Objects.nonNull(dbData)) {
            return dbData.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        }
        return null;
    }
}
