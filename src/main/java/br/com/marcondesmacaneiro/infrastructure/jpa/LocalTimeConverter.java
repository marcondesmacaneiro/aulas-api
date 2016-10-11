package br.com.marcondesmacaneiro.infrastructure.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalTime attribute) {
        if (Objects.nonNull(attribute)) {
            return Date.from(attribute.atDate(LocalDate.MIN).toInstant(ZoneOffset.UTC));
        }
        return null;
    }

    @Override
    public LocalTime convertToEntityAttribute(Date dbData) {
        if (Objects.nonNull(dbData)) {
            return dbData.toInstant().atZone(ZoneOffset.UTC).toLocalTime();
        }
        return null;
    }
}
