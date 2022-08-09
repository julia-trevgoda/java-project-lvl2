package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;

import static hexlet.code.formatters.Json.formatJson;
import static hexlet.code.formatters.Plain.formatPlain;
import static hexlet.code.formatters.Stylish.formatStylish;

public class Formatter {

    public static String formatDiff(String format, List<DiffElement> diff) throws Exception {
        return switch (format) {
            case "stylish" -> formatStylish(diff);
            case "plain" -> formatPlain(diff);
            case "json" -> formatJson(diff);
            default -> throw new Exception("Error: Wrong " + format);
        };
    }
}
