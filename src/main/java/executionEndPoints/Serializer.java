package executionEndPoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import customSerializer.ConfigFieldMapper;
import implementation.CustomAnnotation;


public class Serializer {
    private static final ConfigFieldMapper MAPPER =
            new ConfigFieldMapper();

    public static void main(String[] args) throws JsonProcessingException {

        CustomAnnotation customAnnotation = new CustomAnnotation();
        String result = MAPPER.writeValueAsString((Object) customAnnotation);
        System.out.print(result);

    }

}
