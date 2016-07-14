package br.com.example.infrastructure.spring;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

import static java.util.Objects.nonNull;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Component
class LocalTimeToString implements Converter<LocalTime, String> {
    @Override
    public String convert(LocalTime source) {
        if (nonNull(source)) {
            return source.toString();
        }
        return null;
    }
}
