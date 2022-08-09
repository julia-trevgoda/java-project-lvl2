package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;

public class Stylish {
    public static String formatStylish(List<DiffElement> diff) throws Exception {
        StringBuilder output = new StringBuilder();

        for (DiffElement diffElement : diff) {
            switch (diffElement.getParam()) {
                case ADDED -> output.append("  + ")
                        .append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                case DELETED -> output.append("  - ")
                        .append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                case UNCHANGED -> output.append("    ")
                        .append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n");
                case CHANGED -> output.append("  - ")
                        .append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue()).append("\n")
                        .append("  + ")
                        .append(diffElement.getKey()).append(": ")
                        .append(diffElement.getValue2()).append("\n");
                default -> throw new Exception("Error: " + diffElement.getParam() + " is wrong status.");
            }
        }

        return "{\n" + output + "}";
    }
}
