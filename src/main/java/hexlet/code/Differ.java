package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.Formatter.formatDiff;

public class Differ {

    public static Map<String, Object> getFileContent(String pathToFile) throws Exception {
        String data = Files.readString(Paths.get(pathToFile).toAbsolutePath());
        String type = pathToFile.contains(".") ? pathToFile.substring(pathToFile.lastIndexOf(".")) : "";
        return Parser.parse(data, type);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object> data1 = getFileContent(filePath1);
        Map<String, Object> data2 = getFileContent(filePath2);
        List<DiffElement> diff = Comparator.getComparison(data1, data2);
        return formatDiff(format, diff);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
