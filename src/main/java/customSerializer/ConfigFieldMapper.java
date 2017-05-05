package customSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class ConfigFieldMapper extends ObjectMapper {
    public ConfigFieldMapper() {
        registerModule(new ConfigFieldModule());
        enable(SerializationFeature.INDENT_OUTPUT);
    }
}
