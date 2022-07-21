package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Utils {
    public static Map<String, Object> parseJson(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (file.length() == 0) {
            return new HashMap<>();
        } else {
            return objectMapper.readValue(Files.readString(file.toPath().toAbsolutePath()),
                    new TypeReference<TreeMap<String, Object>>() { });
        }
    }
}
