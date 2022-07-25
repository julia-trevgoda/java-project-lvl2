package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.Formatter.formatDiff;
import static hexlet.code.Parser.parse;

public class Differ {

    public static String generate(String file1, String file2, String format) throws IOException {

        Map<String, Object> data1 = parse(file1);
        Map<String, Object> data2 = parse(file2);

        List<DiffElement> diff = new ArrayList<>();

        int iter1 = 0;
        int iter2 = 0;

        List<String> keys1 = data1.keySet().stream().toList();
        List<String> keys2 = data2.keySet().stream().toList();

        while (iter1 < data1.size() && iter2 < data2.size()) {

            String key1 = keys1.get(iter1);
            String key2 = keys2.get(iter2);

            if (key1.equals(key2)) {
                if ((data1.get(key1) != null && data2.get(key2) != null) && data1.get(key1).equals(data2.get(key2))) {
                    diff.add(new DiffElement(key1, data1.get(key1), DiffElement.DiffType.EQUALS));
                } else {
                    diff.add(new DiffElement(key1, data1.get(key1), DiffElement.DiffType.REMOVE));
                    diff.add(new DiffElement(key2, data2.get(key2), DiffElement.DiffType.ADD));
                }
                iter1++;
                iter2++;
            } else if (key1.compareTo(key2) < 0) {
                diff.add(new DiffElement(key1, data1.get(key1), DiffElement.DiffType.REMOVE_ONE));
                iter1++;
            } else {
                diff.add(new DiffElement(key2, data2.get(key2), DiffElement.DiffType.ADD_ONE));
                iter2++;
            }

        }

        while (iter1 < data1.size()) {
            diff.add(new DiffElement(keys1.get(iter1), data1.get(keys1.get(iter1)),
                    DiffElement.DiffType.REMOVE_ONE));
            iter1++;
        }

        while (iter2 < data2.size()) {
            diff.add(new DiffElement(keys2.get(iter2), data2.get(keys2.get(iter2)),
                    DiffElement.DiffType.ADD_ONE));
            iter2++;
        }

        return formatDiff(format, diff);
    }

    public static String generate(String file1, String file2) throws IOException {
        return generate(file1, file2, "");
    }
}
