package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static hexlet.code.DiffElement.DiffType.ADDED;
import static hexlet.code.DiffElement.DiffType.DELETED;
import static hexlet.code.DiffElement.DiffType.CHANGED;
import static hexlet.code.DiffElement.DiffType.UNCHANGED;

public class Plain {

    public static boolean isValueComplex(Object value) {
        return value != null
                && (value.getClass().isArray() || value instanceof Map || value instanceof List);
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

    public static String formatPlain(List<DiffElement> diff) {
        StringBuilder output = new StringBuilder();

        for (DiffElement diffElement : diff) {
            DiffElement.DiffType diffElementStatus = diffElement.getParam();

            if (!diffElementStatus.equals(UNCHANGED)) {

                switch (diffElementStatus) {
                    case ADDED -> output.append("Property ").append("'")
                            .append(diffElement.getKey()).append("'")
                            .append(" was added with value: ")
                            .append(getPlainValue(diffElement.getValue()));
                    case DELETED -> output.append("Property ").append("'")
                            .append(diffElement.getKey()).append("'")
                            .append(" was removed");
                    case CHANGED -> output.append("Property ").append("'")
                            .append(diffElement.getKey()).append("'")
                            .append(" was updated. From ")
                            .append(getPlainValue(diffElement.getValue())).append(" ")
                            .append("to ")
                            .append(getPlainValue(diffElement.getValue2()));
                    default -> throw new IllegalStateException("Unexpected value: " + diffElementStatus);
                }
                if (diff.indexOf(diffElement) != diff.size() - 1
                        && !output.isEmpty()
                        && !Objects.equals(output.substring(output.length() - 1), " ")) {
                    output.append("\n");
                }
            }

//            if (diffElementStatus == ADDED) {
//                output.append("Property ").append("'")
//                        .append(diffElement.getKey()).append("'")
//                        .append(" was added with value: ")
//                        .append(getPlainValue(diffElement.getValue()));
//            } else if (diffElementStatus == DELETED) {
//                output.append("Property ").append("'")
//                        .append(diffElement.getKey()).append("'")
//                        .append(" was removed");
//            } else if (diffElementStatus == CHANGED) {
//                output.append("Property ").append("'")
//                        .append(diffElement.getKey()).append("'")
//                        .append(" was updated. From ")
//                        .append(getPlainValue(diffElement.getValue())).append(" ")
//                        .append("to ")
//                        .append(getPlainValue(diffElement.getValue2()));
//            }

//            if (i != diff.size() - 1 && !output.isEmpty()
//                    && !Objects.equals(output.substring(output.length() - 1), " ")
//                    && !diffElementStatus.equals(UNCHANGED)) {
//                output.append("\n");
//            }
        }
        return output.toString();
    }
}
