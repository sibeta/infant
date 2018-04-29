package com.qianqian.infant.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.qianqian.infant.entity.Category;
import com.qianqian.infant.enums.CustomerLevelEnum;
import com.qianqian.infant.service.CategoryService;
import com.qianqian.infant.utils.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CategorySerializer extends JsonSerializer<Integer> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void serialize(Integer categoryId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        Category category = categoryService.findOne(categoryId);
        String value = "不详";
        if(category != null) {
            value = category.getCategoryName();
        }
        jsonGenerator.writeString(value);
    }

}
