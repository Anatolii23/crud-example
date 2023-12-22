package com.plant.api.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;

/**
 * String Trim Deserializer.
 *
 * @author Anatolii Hamza
 */
public class StringTrimDeserializer extends JsonDeserializer<String> {

    private final StringDeserializer stringDeserializer;

    public StringTrimDeserializer() {
        stringDeserializer = new StringDeserializer();
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var result = stringDeserializer.deserialize(p, ctxt);
        return result == null ? null : result.trim();
    }

    @Override
    public String deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer)
            throws IOException {

        return deserialize(p, ctxt);
    }
}
