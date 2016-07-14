package br.com.example;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Configuration
public class JacksonConfiguration {

    @Autowired
    private void registerSerializersDeserializers(List<ObjectMapper> objectMappers, Set<JsonSerializer> serializers, Set<JsonDeserializer> deserializers) {
        SimpleModule simpleModule = new SimpleModule();

        for (JsonSerializer serializer : serializers) {
            TypeToken typeToken = TypeToken.of(serializer.getClass());
            TypeToken parameterTypeToken = typeToken.resolveType(JsonSerializer.class.getTypeParameters()[0]);
            simpleModule.addSerializer(parameterTypeToken.getRawType(), serializer);
        }

        for (JsonDeserializer deserializer : deserializers) {
            TypeToken typeToken = TypeToken.of(deserializer.getClass());
            TypeToken parameterTypeToken = typeToken.resolveType(JsonDeserializer.class.getTypeParameters()[0]);
            simpleModule.addDeserializer(parameterTypeToken.getRawType(), deserializer);
        }

        objectMappers.forEach(objectMapper -> objectMapper.registerModule(simpleModule));
    }
}
