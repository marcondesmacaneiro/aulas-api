package br.com.marcondesmacaneiro.infrastructure.spring;

import br.com.marcondesmacaneiro.domain.vo.Phone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@Component
class PhoneOfStringConverter implements Converter<String, Phone> {

    @Override
    public Phone convert(String source) {
        if (!isNullOrEmpty(source)) {
            return Phone.of(source);
        }
        return null;
    }
}
