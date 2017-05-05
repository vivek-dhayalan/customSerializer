package customSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import definition.ConfigField;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CustomSerializerV1 extends StdSerializer<Object> {

    private String name;
    private String displayName;
    private String description;
    private boolean required;
    private String multiplicity;
    private boolean supportsExpression;

    public CustomSerializerV1() {
        super(Object.class);
    }

    public CustomSerializerV1(String name, String displayName, String description, boolean required, String multiplicity, boolean
            supportsExpression ) {
        super(Object.class);
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.required = required;
        this.multiplicity = multiplicity;
        this.supportsExpression = supportsExpression;
    }


    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", o.getClass().getSimpleName());
        jsonGenerator.writeArrayFieldStart("definition");

        Field[] fields = o.getClass().getDeclaredFields();
        for(Field field : fields) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(field.getName()), '-').toString().toLowerCase());
            addAnnotationInJson(field, jsonGenerator);
            try {
                addTypeInJson(field, jsonGenerator);
            } catch (Exception e) {

            }
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();

    }

    private void addChildInJson(Field childField, JsonGenerator jsonGenerator) throws IOException, ClassNotFoundException {


        Class<?> childObj = null;
        childObj = Class.forName(childField.getType().getName(), true, ClassLoader.getSystemClassLoader());
        Field[] fields = childObj.getDeclaredFields();

        for(Field field: fields) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(field.getName()), '-').toString().toLowerCase());
            addAnnotationInJson(field, jsonGenerator);
            addTypeInJson(field, jsonGenerator);
            jsonGenerator.writeEndObject();
        }


    }

    private void addTypeInJson(Field field, JsonGenerator jsonGenerator) throws IOException, ClassNotFoundException {
        if(!ClassUtils.isPrimitiveOrWrapper(field.getType()) && !field.getType().getSimpleName().equalsIgnoreCase
                ("string")) {
            jsonGenerator.writeStringField("type", "complex");
            jsonGenerator.writeFieldName("children");
            jsonGenerator.writeStartArray();
            addChildInJson(field, jsonGenerator);
            jsonGenerator.writeEndArray();
        } else {
            jsonGenerator.writeStringField("type", field.getType().getSimpleName());
        }
    }

    private void addAnnotationInJson(Field field, JsonGenerator jsonGenerator) throws IOException {
        Annotation[] annotations = field.getAnnotationsByType(ConfigField.class);
        for(Annotation annotation : annotations) {
            ConfigField configField = (ConfigField) annotation;
            jsonGenerator.writeStringField("displayName", configField.displayName());
            jsonGenerator.writeStringField("description", configField.description());
            jsonGenerator.writeBooleanField("required", configField.required());
            jsonGenerator.writeStringField("multiplicity", configField.multiplicity().getValue());
            jsonGenerator.writeBooleanField("expressionEnabled", configField.expressionEnabled());
        }
        if(annotations.length == 0) {
            jsonGenerator.writeStringField("displayName", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(field.getName()), ' ').toString().toLowerCase());
        }

    }
}
