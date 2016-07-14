package br.com.example.infrastructure.jackson;

import br.com.example.domain.vo.Phone;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Component
public class PhoneSerializer extends JsonSerializer<Phone> {

    @Autowired
    private ConversionService conversion;

    @Override
    public void serialize(Phone value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(conversion.convert(value, String.class));
    }
}