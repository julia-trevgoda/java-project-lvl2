package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.apache.commons.io.FileUtils.getExtension;

public class Parser {

    public static Map<String, Object> parseJson(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (file.length() == 0) {
            return new HashMap<>();
        } else {
            return objectMapper.readValue(Files.readString(file.toPath().toAbsolutePath()),
                    new TypeReference<TreeMap<String, Object>>() { });
        }
    }

    public static Map<String, Object> parseYml(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        if (file.length() == 0) {
            return new HashMap<>();
        } else {
            return objectMapper.readValue(Files.readString(file.toPath().toAbsolutePath()),
                    new TypeReference<TreeMap<String, Object>>() { });
        }
    }

    public static Map<String, Object> parse(File file) throws IOException {
        String type = getExtension(String.valueOf(file.toPath()));
        return switch (type) {
            case "json" -> parseJson(file);
            case "yml", "yaml" -> parseYml(file);
            default -> parseJson(file);
        };
    }
}
