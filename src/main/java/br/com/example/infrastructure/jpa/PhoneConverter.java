package br.com.example.infrastructure.jpa;

import br.com.example.domain.vo.Phone;
import org.springframework.core.convert.ConversionService;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static br.com.example.Application.getBean;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Converter(autoApply = true)
public class PhoneConverter implements AttributeConverter<Phone, String> {

    @Override
    public String convertToDatabaseColumn(Phone attribute) {
        return getBean(ConversionService.class).convert(attribute, String.class);
    }

    @Override
    public Phone convertToEntityAttribute(String dbData) {
        return getBean(ConversionService.class).convert(dbData, Phone.class);
    }
}
