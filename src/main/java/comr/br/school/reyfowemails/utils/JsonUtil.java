package comr.br.school.reyfowemails.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);


    public static <T> T toObject(final String payload, final Class<T> valueType) {
        try {
            return mapper.readValue(payload, valueType);
        } catch (IOException e) {
            log.error("Error when serialized message {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static <T> T fileToObject(final String payload, final Class<T> valueType) {
        try {
            return mapper.readValue(payload, valueType);
        } catch (IOException e) {
            log.error("Error when serialized message {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String toJson(final Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }


}
