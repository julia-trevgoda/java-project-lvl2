package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;

import static hexlet.code.DiffElement.DiffType.ADDED;
import static hexlet.code.DiffElement.DiffType.DELETED;
import static hexlet.code.DiffElement.DiffType.CHANGED;
import static hexlet.code.DiffElement.DiffType.UNCHANGED;

public class Comparator {

    public static List<DiffElement> getComparison(Map<String, Object> data1, Map<String, Object> data2) {

        List<DiffElement> diff = new ArrayList<>();

        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            if (!data2.containsKey(key)) {
                diff.add(new DiffElement(key, data1.get(key), DELETED));
            } else if (!data1.containsKey(key)) {
                diff.add(new DiffElement(key, data2.get(key), ADDED));
            } else if ((data1.get(key) != null && data2.get(key) != null) && data1.get(key).equals(data2.get(key))) {
                diff.add(new DiffElement(key, data1.get(key), UNCHANGED));
            } else {
                diff.add(new DiffElement(key, data1.get(key), data2.get(key), CHANGED));
            }
        }
        return diff;
    }

}
