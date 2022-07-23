package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.parse;

public class Differ {

    public static List<DiffElement> generate(File file1, File file2) throws IOException {

        Map<String, Object> data1 = parse(file1);
        Map<String, Object> data2 = parse(file2);

        List<DiffElement> diffResult1 = new ArrayList<>();

        int iter1 = 0;
        int iter2 = 0;

        List<String> keys1 = data1.keySet().stream().toList();
        List<String> keys2 = data2.keySet().stream().toList();

        while (iter1 < data1.size() && iter2 < data2.size()) {

            String key1 = keys1.get(iter1);
            String key2 = keys2.get(iter2);

            if (key1.equals(key2)) {
                if ((data1.get(key1) != null && data2.get(key2) != null) && data1.get(key1).equals(data2.get(key2))) {
                    diffResult1.add(new DiffElement(key1, data1.get(key1), DiffElement.DiffType.EQUALS));
                } else {
                    diffResult1.add(new DiffElement(key1, data1.get(key1), DiffElement.DiffType.REMOVE));
                    diffResult1.add(new DiffElement(key2, data2.get(key2), DiffElement.DiffType.ADD));
                }
                iter1++;
                iter2++;
            } else if (key1.compareTo(key2) < 0) {
                diffResult1.add(new DiffElement(key1, data1.get(key1), DiffElement.DiffType.REMOVE));
                iter1++;
            } else {
                diffResult1.add(new DiffElement(key2, data2.get(key2), DiffElement.DiffType.ADD));
                iter2++;
            }

        }

        while (iter1 < data1.size()) {
            diffResult1.add(new DiffElement(keys1.get(iter1), data1.get(keys1.get(iter1)),
                    DiffElement.DiffType.REMOVE));
            iter1++;
        }

        while (iter2 < data2.size()) {
            diffResult1.add(new DiffElement(keys2.get(iter2), data2.get(keys2.get(iter2)),
                    DiffElement.DiffType.ADD));
            iter2++;
        }

        return diffResult1;
    }
}
