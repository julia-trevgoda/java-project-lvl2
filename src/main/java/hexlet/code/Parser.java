package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static Map<String, Object> parseJson(String pathToFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = Paths.get(pathToFile).toFile();
        if (file.length() == 0) {
            return new HashMap<>();
        } else {
            return objectMapper.readValue(file,
                    new TypeReference<TreeMap<String, Object>>() { });
        }
    }

    public static Map<String, Object> parseYml(String pathToFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        File file = Paths.get(pathToFile).toFile();
        if (file.length() == 0) {
            return new HashMap<>();
        } else {
            return objectMapper.readValue(file,
                    new TypeReference<TreeMap<String, Object>>() { });
        }
    }

    public static Map<String, Object> parse(String pathToFile) throws IOException {
        String type = pathToFile.contains(".") ? pathToFile.substring(pathToFile.lastIndexOf(".")) : "";
        return switch (type) {
            case ".json" -> parseJson(pathToFile);
            case ".yml", ".yaml" -> parseYml(pathToFile);
            default -> parseJson(pathToFile);
        };
    }
}
