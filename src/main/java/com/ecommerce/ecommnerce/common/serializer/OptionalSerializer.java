package com.ecommerce.ecommnerce.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

public class  OptionalSerializer extends JsonSerializer<Optional<?>> {
    @Override
    public void serialize(Optional<?> optional, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (optional.isPresent()) {
            serializerProvider.defaultSerializeValue(optional.get(), jsonGenerator);
        } else {
            jsonGenerator.writeNull();
        }
    }
}
