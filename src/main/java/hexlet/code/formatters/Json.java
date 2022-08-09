package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffElement;

import java.io.IOException;
import java.util.List;

public class Json {
    public static String formatJson(List<DiffElement> diff) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diff);
    }
}
