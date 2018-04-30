package com.qianqian.infant.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.qianqian.infant.enums.TypeEnum;
import com.qianqian.infant.utils.EnumUtil;

import java.io.IOException;

public class TypeSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer type, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String value = EnumUtil.getByCode(type, TypeEnum.class).getMessage();
        jsonGenerator.writeString(value);
    }

}
