package com.qianqian.infant.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.qianqian.infant.enums.RoleEnum;
import com.qianqian.infant.utils.EnumUtil;

import java.io.IOException;

public class RoleSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer role, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String value = EnumUtil.getByCode(role, RoleEnum.class).getMessage();
        jsonGenerator.writeString(value);
    }

}
