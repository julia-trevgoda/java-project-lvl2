package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static hexlet.code.DiffElement.DiffType.EQUALS;

public class Formatter {

    public static boolean isValueComplex(Object value) {
        return value != null && (value.getClass().isArray() || value instanceof Map || value instanceof List);
    }

    public static Object getPlainValue(Object value) {

        Object plainValue;

        if (isValueComplex(value)) {
            plainValue = "[complex value]";
        } else if (value instanceof String) {
            plainValue = "'" + value + "'";
        } else {
            plainValue = value;
        }
        return plainValue;
    }

    public static String formatStylish(List<DiffElement> diff) {
        StringBuilder output = new StringBuilder();

        for (DiffElement diffElement : diff) {
            switch (diffElement.getParam()) {
                case ADD_UPDATED, ADD_ONE -> output.append("  + ").append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                case REMOVE_UPDATED, REMOVE_ONE -> output.append("  - ").append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                case EQUALS -> output.append("    ").append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                default -> {
                }
            }
        }

        return "{\n" + output + "}";
    }

    public static String formatPlain(List<DiffElement> diff) {
        StringBuilder output = new StringBuilder();
        DiffElement diffElement;

        for (int i = 0; i < diff.size(); i++) {
            diffElement = diff.get(i);
            switch (diffElement.getParam()) {
                case ADD_ONE -> output.append("Property ").append("'" + diffElement.getKey() + "'")
                        .append(" was added with value: ")
                        .append(getPlainValue(diffElement.getValue()));
                case REMOVE_ONE -> output.append("Property ").append("'" + diffElement.getKey() + "'")
                        .append(" was removed");
                case REMOVE_UPDATED -> output.append("Property ").append("'" + diffElement.getKey() + "'")
                        .append(" was updated. From ").append(getPlainValue(diffElement.getValue()) + " ");
                case ADD_UPDATED -> output.append("to ").append(getPlainValue(diffElement.getValue()));
                default -> {
                }
            }
            if (i != diff.size() - 1 && !output.isEmpty()
                    && (!Objects.equals(output.substring(output.length() - 1), " "))
                    && !diffElement.getParam().equals(EQUALS)) {
                output.append("\n");
            }

        }
        return output.toString();
    }

    public static String formatJson(List<DiffElement> diff) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(diff);
    }

    public static String formatDiff(String format, List<DiffElement> diff) throws IOException {
        return switch (format) {
            case "", "stylish" -> formatStylish(diff);
            case "plain" -> formatPlain(diff);
            case "json" -> formatJson(diff);
            default -> formatStylish(diff);
        };
    }
}
