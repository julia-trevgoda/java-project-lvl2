package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(File file1, File file2) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> json1
                = objectMapper.readValue(Files.readString(file1.toPath().toAbsolutePath()),
                new TypeReference<TreeMap<String, Object>>() { });

        Map<String, Object> json2
                = objectMapper.readValue(Files.readString(file2.toPath().toAbsolutePath()),
                new TypeReference<TreeMap<String, Object>>() { });

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("\n");

        int size1 = json1.size();
        int size2 = json2.size();

        int iter1 = 0;
        int iter2 = 0;

        List<String> list1 = json1.keySet().stream().toList();
        List<String> list2 = json2.keySet().stream().toList();

        while (iter1 < size1 && iter2 < size2) {
            String key1 = list1.get(iter1);
            String key2 = list2.get(iter2);

            if (key1.equals(key2)) {
                if (json1.get(key1).equals(json2.get(key2))) {
                    stringBuilder.append("  ").append(key1).append(": ")
                            .append(json1.get(key1)).append("\n");
                } else {
                    stringBuilder.append("- ").append(key1).append(": ")
                            .append(json1.get(key1)).append("\n");
                    stringBuilder.append("+ ").append(key2).append(": ")
                            .append(json2.get(key2)).append("\n");
                }
                iter1++;
                iter2++;
            } else if (key1.compareTo(key2) < 0) {
                stringBuilder.append("- ").append(key1).append(": ")
                        .append(json1.get(key1)).append("\n");
                iter1++;
            } else {
                stringBuilder.append("+ ").append(key2).append(": ")
                        .append(json2.get(key2)).append("\n");
                iter2++;
            }

        }

        while (iter1 < size1) {
            stringBuilder.append("- ").append(list1.get(iter1)).append(": ")
                    .append(json1.get(list1.get(iter1))).append("\n");
            iter1++;
        }

        while (iter2 < size2) {
            stringBuilder.append("+ ").append(list2.get(iter2)).append(": ")
                    .append(json2.get(list2.get(iter2))).append("\n");
            iter2++;
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
