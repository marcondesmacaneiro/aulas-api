package br.com.marcondesmacaneiro.infrastructure.spring;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.util.Objects.nonNull;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@Component
class LocalDateToString implements Converter<LocalDate, String> {
    @Override
    public String convert(LocalDate source) {
        if (nonNull(source)) {
            return source.toString();
        }
        return null;
    }
}
