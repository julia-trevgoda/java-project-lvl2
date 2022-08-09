import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import hexlet.code.App;
import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;

public class AppTests {

    //Tests on Parser (json, yml, yaml) > returns Map<String, Object>
    @Test
    @DisplayName("testParseJson: call JsonParser directly")
    void testParseJson() throws IOException {
        String data = TestDataFiles.readFile(TestDataFiles.TEST_DIFF_JSON_1);
        Map<String, Object> actualResult = Parser.parseJson(data);
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_PARSE_1);
        assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    @DisplayName("testParseJson2: call JsonParser through common parse method")
    void testParseJson2() throws Exception {
        String data = TestDataFiles.readFile(TestDataFiles.TEST_DIFF_JSON_2);
        Map<String, Object> actualResult = Parser.parse(data, ".json");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_PARSE_2);
        assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    @DisplayName("testParseYml: call YmlParser directly")
    void testParseYml() throws IOException {
        String data = TestDataFiles.readFile(TestDataFiles.TEST_DIFF_YML_1);
        Map<String, Object> actualResult = Parser.parseYml(data);
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_PARSE_1);
        assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    @DisplayName("testParseYml2: call YmlParser through common parse method")
    void testParseYml2() throws Exception {
        String data = TestDataFiles.readFile(TestDataFiles.TEST_DIFF_YML_2);
        Map<String, Object> actualResult = Parser.parse(data, ".yml");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_PARSE_2);
        assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    @DisplayName("testParseYaml: call YamlParser directly")
    void testParseYaml() throws IOException {
        String data = TestDataFiles.readFile(TestDataFiles.TEST_DIFF_YAML_1);
        Map<String, Object> actualResult = Parser.parseYml(data);
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_PARSE_1);
        assertEquals(actualResult.toString(), expectedResult);
    }

    @Test
    @DisplayName("testParseYaml: call YamlParser through common parse method")
    void testParseYaml2() throws Exception {
        String data = TestDataFiles.readFile(TestDataFiles.TEST_DIFF_YAML_2);
        Map<String, Object> actualResult = Parser.parse(data, ".yaml");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_PARSE_2);
        assertEquals(actualResult.toString(), expectedResult);
    }


    //Tests on Differ > returns *formatted* String
    @Test
    @DisplayName("testDiff: JSON: stylish")
    void testDiffJsonStylish() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_JSON_1;
        String filePath2 = TestDataFiles.TEST_DIFF_JSON_2;
        String actualResult = Differ.generate(filePath1, filePath2, "stylish");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_STYLISH);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: JSON: default")
    void testDiffJsonDefault() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_JSON_1;
        String filePath2 = TestDataFiles.TEST_DIFF_JSON_2;
        String actualResult = Differ.generate(filePath1, filePath2);
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_STYLISH);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: YML: default")
    void testDiffYmlDefault() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_YML_1;
        String filePath2 = TestDataFiles.TEST_DIFF_YML_2;
        String actualResult = Differ.generate(filePath1, filePath2);
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_STYLISH);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: YAML: Stylish")
    void testDiffYamlStylish() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_YAML_1;
        String filePath2 = TestDataFiles.TEST_DIFF_YAML_2;
        String actualResult = Differ.generate(filePath1, filePath2, "stylish");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_STYLISH);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: JSON: plain")
    void testDiffJsonPlain() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_JSON_1;
        String filePath2 = TestDataFiles.TEST_DIFF_JSON_2;
        String actualResult = Differ.generate(filePath1, filePath2, "plain");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_PLAIN);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: YAML: plain")
    void testDiffYamlPlain() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_YAML_1;
        String filePath2 = TestDataFiles.TEST_DIFF_YAML_2;
        String actualResult = Differ.generate(filePath1, filePath2, "plain");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_PLAIN);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: YML: plain")
    void testDiffYmlPlain() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_YML_1;
        String filePath2 = TestDataFiles.TEST_DIFF_YML_2;
        String actualResult = Differ.generate(filePath1, filePath2, "plain");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_PLAIN);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: JSON: Json")
    void testDiffJsonToJson() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_JSON_1;
        String filePath2 = TestDataFiles.TEST_DIFF_JSON_2;
        String actualResult = Differ.generate(filePath1, filePath2, "json");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_JSON);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffJson1: file1.size() > file2.size()")
    void testdiffJson1() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_JSON_1;
        String filePath2 = TestDataFiles.TEST_DIFF_JSON_2_SHORT;
        String actualResult = Differ.generate(filePath1, filePath2);
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_STYLISH_2);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYaml1: file1.size() < file2.size()")
    void testdiffYaml1() throws Exception {
        String filePath1 = TestDataFiles.TEST_DIFF_YAML_1_SHORT;
        String filePath2 = TestDataFiles.TEST_DIFF_YAML_2;
        String actualResult = Differ.generate(filePath1, filePath2, "plain");
        String expectedResult = TestDataFiles.readFile(TestDataFiles.RESULT_DIFF_PLAIN_2);
        assertEquals(actualResult, expectedResult);
    }

    //Test exit codes
    @Test
    @ExpectSystemExitWithStatus(0)
    void testSuccessExitCode1() {
        App.main(new String[]{TestDataFiles.TEST_DIFF_JSON_1, TestDataFiles.TEST_DIFF_JSON_2});
    }

    @Test
    @ExpectSystemExitWithStatus(1)
    void testErrorExitCode() {
        App.main(new String[]{TestDataFiles.TEST_DIFF_JSON_1, "testDiffJson2.json"});
    }

}
