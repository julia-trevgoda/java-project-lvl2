import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataFiles {

    private static final String RES = "src/test/resources/";

    public static final String TEST_DIFF_JSON_1 = RES + "testDiffJson1.json";
    public static final String TEST_DIFF_JSON_2 = RES + "testDiffJson2.json";
    public static final String TEST_DIFF_JSON_2_SHORT = RES + "testDiffJson2_short.json";
    public static final String TEST_DIFF_YAML_1 = RES + "testDiffYaml1.yaml";
    public static final String TEST_DIFF_YAML_1_SHORT = RES + "testDiffYaml1_short.yaml";
    public static final String TEST_DIFF_YAML_2 = RES + "testDiffYaml2.yaml";
    public static final String TEST_DIFF_YML_1 = RES + "testDiffYml1.yml";
    public static final String TEST_DIFF_YML_2 = RES + "testDiffYml2.yml";

    public static final String RESULT_PARSE_1 = RES + "resultParse1.txt";
    public static final String RESULT_PARSE_2 = RES + "resultParse2.txt";
    public static final String RESULT_DIFF_STYLISH = RES + "resultDiffStylish.txt";
    public static final String RESULT_DIFF_STYLISH_2 = RES + "resultDiffStylish2.txt";
    public static final String RESULT_DIFF_PLAIN = RES + "resultDiffPlain.txt";
    public static final String RESULT_DIFF_PLAIN_2 = RES + "resultDiffPlain2.txt";
    public static final String RESULT_DIFF_JSON = RES + "resultDiffJson.txt";

    public static String readFile(String fileName) throws IOException {
        return Files.readString(Paths.get(fileName).toAbsolutePath());
    }
}
