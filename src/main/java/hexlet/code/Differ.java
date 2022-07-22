package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.parse;

public class Differ {

    public static String generate(File file1, File file2) throws IOException {

        Map<String, Object> data1 = parse(file1);
        Map<String, Object> data2 = parse(file2);

        StringBuilder diffResult = new StringBuilder();

        int size1 = data1.size();
        int size2 = data2.size();

        int iter1 = 0;
        int iter2 = 0;

        List<String> list1 = data1.keySet().stream().toList();
        List<String> list2 = data2.keySet().stream().toList();

        while (iter1 < size1 && iter2 < size2) {
            String key1 = list1.get(iter1);
            String key2 = list2.get(iter2);

            if (key1.equals(key2)) {
                if (data1.get(key1).equals(data2.get(key2))) {
                    diffResult.append("  ").append(key1).append(": ")
                            .append(data1.get(key1)).append("\n");
                } else {
                    diffResult.append("- ").append(key1).append(": ")
                            .append(data1.get(key1)).append("\n");
                    diffResult.append("+ ").append(key2).append(": ")
                            .append(data2.get(key2)).append("\n");
                }
                iter1++;
                iter2++;
            } else if (key1.compareTo(key2) < 0) {
                diffResult.append("- ").append(key1).append(": ")
                        .append(data1.get(key1)).append("\n");
                iter1++;
            } else {
                diffResult.append("+ ").append(key2).append(": ")
                        .append(data2.get(key2)).append("\n");
                iter2++;
            }

        }

        while (iter1 < size1) {
            diffResult.append("- ").append(list1.get(iter1)).append(": ")
                    .append(data1.get(list1.get(iter1))).append("\n");
            iter1++;
        }

        while (iter2 < size2) {
            diffResult.append("+ ").append(list2.get(iter2)).append(": ")
                    .append(data2.get(list2.get(iter2))).append("\n");
            iter2++;
        }

        String diff = "{\n" + diffResult + "}";

        return diff;
    }
}
