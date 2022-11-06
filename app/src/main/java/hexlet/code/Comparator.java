package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.DiffElement.DiffType.ADDED;
import static hexlet.code.DiffElement.DiffType.DELETED;
import static hexlet.code.DiffElement.DiffType.CHANGED;
import static hexlet.code.DiffElement.DiffType.UNCHANGED;

public class Comparator {

    public static List<DiffElement> getComparison(Map<String, Object> data1, Map<String, Object> data2) {

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
                    diff.add(new DiffElement(key1, data1.get(key1), UNCHANGED));
                } else {
                    diff.add(new DiffElement(key1, data1.get(key1), data2.get(key1), CHANGED));
                }
                iter1++;
                iter2++;
            } else if (key1.compareTo(key2) < 0) {
                diff.add(new DiffElement(key1, data1.get(key1), DELETED));
                iter1++;
            } else {
                diff.add(new DiffElement(key2, data2.get(key2), ADDED));
                iter2++;
            }

        }

        while (iter1 < data1.size()) {
            diff.add(new DiffElement(keys1.get(iter1), data1.get(keys1.get(iter1)), DELETED));
            iter1++;
        }

        while (iter2 < data2.size()) {
            diff.add(new DiffElement(keys2.get(iter2), data2.get(keys2.get(iter2)), ADDED));
            iter2++;
        }

        return diff;
    }

}
