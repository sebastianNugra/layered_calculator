package dev.sebas.calculadora.dto.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

public class StrictDoubleDeserializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

        JsonToken token = parser.currentToken();

        if (token == JsonToken.VALUE_NUMBER_FLOAT ||
                token == JsonToken.VALUE_NUMBER_INT) {

            return parser.getDoubleValue();
        }

        if (token == JsonToken.VALUE_STRING) {
            throw JsonMappingException.from(
                    parser,
                    "Se esperaba un valor numerico, no un string.");
        }

        throw JsonMappingException.from(
                parser,
                "Se esperaba un valor numerico.");
    }
}