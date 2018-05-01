package com.qianqian.infant.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.qianqian.infant.enums.OrderStatusEnum;
import com.qianqian.infant.utils.EnumUtil;

import java.io.IOException;

public class OrderStatusSerializer extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String value = EnumUtil.getByCode(status, OrderStatusEnum.class).getMessage();
        jsonGenerator.writeString(value);
    }

}
