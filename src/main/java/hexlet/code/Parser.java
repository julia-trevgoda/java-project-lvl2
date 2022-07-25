package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public static Map<String, Object> parse(String pathToFile) throws IOException {
        String type = pathToFile.contains(".") ? pathToFile.substring(pathToFile.lastIndexOf(".")) : "";
        Path path = Paths.get(pathToFile);
        File file = path.toFile();
        return switch (type) {
            case ".json" -> parseJson(file);
            case ".yml", ".yaml" -> parseYml(file);
            default -> parseJson(file);
        };
    }
}
