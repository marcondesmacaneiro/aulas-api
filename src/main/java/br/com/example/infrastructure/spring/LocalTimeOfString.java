package br.com.example.infrastructure.spring;

import br.com.example.domain.exception.InvalidArgumentException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Component
class LocalTimeOfString implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        if (!isNullOrEmpty(source)) {
            try {
                return LocalTime.parse(source);
            } catch (DateTimeParseException ex) {
                throw new InvalidArgumentException("Time must be in ISO-8601!");
            }
        }
        return null;
    }
}
