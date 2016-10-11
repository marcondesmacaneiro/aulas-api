package br.com.marcondesmacaneiro.infrastructure.jackson;

import br.com.marcondesmacaneiro.domain.vo.Phone;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Component
public class PhoneDeserializer extends JsonDeserializer<Phone> {

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversion;

    @Override
    public Phone deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return conversion.convert(jp.getValueAsString(), Phone.class);
    }
}