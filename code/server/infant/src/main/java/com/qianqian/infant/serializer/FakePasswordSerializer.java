package com.qianqian.infant.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.qianqian.infant.config.Constant;

import java.io.IOException;

public class FakePasswordSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String password, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(Constant.FAKE_PASSWORD);
    }

}
