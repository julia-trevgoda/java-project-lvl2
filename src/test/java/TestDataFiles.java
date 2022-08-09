import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataFiles {

    private final static String RES = "src/test/resources/";

    public final static String TEST_DIFF_JSON_1 = RES + "testDiffJson1.json";
    public final static String TEST_DIFF_JSON_2 = RES + "testDiffJson2.json";
    public final static String TEST_DIFF_JSON_2_SHORT = RES + "testDiffJson2_short.json";
    public final static String TEST_DIFF_YAML_1 = RES + "testDiffYaml1.yaml";
    public final static String TEST_DIFF_YAML_1_SHORT = RES + "testDiffYaml1_short.yaml";
    public final static String TEST_DIFF_YAML_2 = RES + "testDiffYaml2.yaml";
    public final static String TEST_DIFF_YML_1 = RES + "testDiffYml1.yml";
    public final static String TEST_DIFF_YML_2 = RES + "testDiffYml2.yml";

    public final static String RESULT_PARSE_1 = RES + "resultParse1.txt";
    public final static String RESULT_PARSE_2 = RES + "resultParse2.txt";
    public final static String RESULT_DIFF_STYLISH = RES + "resultDiffStylish.txt";
    public final static String RESULT_DIFF_STYLISH_2 = RES + "resultDiffStylish2.txt";
    public final static String RESULT_DIFF_PLAIN = RES + "resultDiffPlain.txt";
    public final static String RESULT_DIFF_PLAIN_2 = RES + "resultDiffPlain2.txt";
    public final static String RESULT_DIFF_JSON = RES + "resultDiffJson.txt";

    public static String readFile(String fileName) throws IOException {
        return Files.readString(Paths.get(fileName).toAbsolutePath());
    }
}
