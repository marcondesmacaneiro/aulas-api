package br.com.marcondesmacaneiro.infrastructure.spring;

import br.com.marcondesmacaneiro.domain.vo.Phone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@Component
class PhoneToStringConverter implements Converter<Phone, String> {

    @Override
    public String convert(Phone source) {
        if (nonNull(source)) {
            return source.toString();
        }
        return null;
    }
}
