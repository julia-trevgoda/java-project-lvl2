package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static Map<String, Object> parseJson(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data,
                new TypeReference<TreeMap<String, Object>>() {
                });
    }

    public static Map<String, Object> parseYml(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(data,
                new TypeReference<TreeMap<String, Object>>() {
                });
    }

    public static Map<String, Object> parse(String data, String type) throws Exception {
        if (data.length() == 0) {
            return new HashMap<>();
        } else {
            return switch (type) {
                case ".json" -> parseJson(data);
                case ".yml", ".yaml" -> parseYml(data);
                default -> throw new Exception("Error: " + type + " is a wrong type.");
            };
        }
    }
}
