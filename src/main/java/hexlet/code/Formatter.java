package hexlet.code;

import java.util.List;

public class Formatter {

    public static String formatStylish(List<DiffElement> diff) {
        StringBuilder output = new StringBuilder();

        for (DiffElement diffElement : diff) {
            switch (diffElement.getParam()) {
                case ADD -> output.append("+ ").append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                case REMOVE -> output.append("- ").append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                case EQUALS -> output.append("  ").append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                default -> output.append(" ");
            }
        }

        return "{\n" + output + "}";
    }

    public static String formatDiff(String format, List<DiffElement> diff) {
        return switch (format) {
            case "", "stylish" -> formatStylish(diff);
//            case "plain" -> formatPlain(diff);
            default -> formatStylish(diff);
        };
    }
}
