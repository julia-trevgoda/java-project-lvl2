import hexlet.code.App;
import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AppTests {

    @Test
    @DisplayName("testDiffJson1: file1.size() > file2.size()")
    void testdiffJson1() throws IOException {
        File file1 = new File("src/test/resources/testDiffJson1_1.json");
        File file2 = new File("src/test/resources/testDiffJson1_2.json");
        String expectedResult = """
                {
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
        String actualResult = Differ.generate(file1, file2, "");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("json format parser files works correctly")
    void testParseJson() throws IOException {
        File file = new File("src/test/resources/testDiffJson1_1.json");
        Map<String, Object> expectedResult = Map.of("follow", false, "host", "hexlet.io",
                "proxy", "123.234.53.22", "timeout", 50);
        Map<String, Object> actualResult = Parser.parseJson(file);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("json format parser files works correctly")
    void testParseJson2() throws IOException {
        File file = new File("src/test/resources/testDiffJson1_1.json");
        Map<String, Object> expectedResult = Map.of("follow", false, "host", "hexlet.io",
                "proxy", "123.234.53.22", "timeout", 50);
        Map<String, Object> actualResult = Parser.parse(file);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffJson2: file2.size() > file1.size()")
    void testDiffJson2() throws IOException {
        File file1 = new File("src/test/resources/testDiffJson2_1.json");
        File file2 = new File("src/test/resources/testDiffJson2_2.json");
        String expectedResult = """
                {
                + follow: false
                  host: hexlet.io
                + proxy: 123.234.53.22
                - timeout: 20
                + timeout: 50
                - verbose: true
                }""";
        String actualResult = Differ.generate(file1, file2, "stylish");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffJson3: file2 is empty")
    void testDiffJson3() throws IOException {
        File file1 = new File("src/test/resources/testDiffJson3_1.json");
        File file2 = new File("src/test/resources/testDiffJson3_2.json");
        String expectedResult = """
                {
                - host: hexlet.io
                - timeout: 20
                - verbose: true
                }""";
        String actualResult = Differ.generate(file1, file2, "");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffJson4: file1 doesn't exist")
    void testDiffJson4() throws IOException {
        File file1 = new File("src/test/resources/testDiffJson4_1.json");
        File file2 = new File("src/test/resources/testDiffJson4_2.json");
        String expectedResult = """
                {
                + host: hexlet.io
                + timeout: 20
                + verbose: true
                }""";
        String actualResult = Differ.generate(file1, file2, "stylish");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYml: yml format parser works correctly")
    void testParseYml() throws IOException {
        File file = new File("src/test/resources/testDiffYaml1_1.yml");
        Map<String, Object> expectedResult = Map.of("follow_yml", false, "host", "hexlet.io",
                "proxy", "123.234.53.22", "timeout", 50);
        Map<String, Object> actualResult = Parser.parseYml(file);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYml: yml format parser works correctly")
    void testParseYml2() throws IOException {
        File file = new File("src/test/resources/testDiffYaml1_1.yml");
        Map<String, Object> expectedResult = Map.of("follow_yml", false, "host", "hexlet.io",
                "proxy", "123.234.53.22", "timeout", 50);
        Map<String, Object> actualResult = Parser.parse(file);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testParseYaml: yaml format parser works correctly")
    void testParseYaml() throws IOException {
        File file = new File("src/test/resources/testDiffYaml2_1.yaml");
        Map<String, Object> expectedResult = Map.of("follow_yaml", false, "host", "hexlet.io",
                "proxy", "123.234.53.22", "timeout", 50);
        Map<String, Object> actualResult = Parser.parseYml(file);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testParseYaml: yaml format parser works correctly")
    void testParseYaml2() throws IOException {
        File file = new File("src/test/resources/testDiffYaml2_1.yaml");
        Map<String, Object> expectedResult = Map.of("follow_yaml", false, "host", "hexlet.io",
                "proxy", "123.234.53.22", "timeout", 50);
        Map<String, Object> actualResult = Parser.parse(file);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYml: file2 is empty")
    void testDiffYml2() throws IOException {
        File file1 = new File("src/test/resources/testDiffYaml3_1.yml");
        File file2 = new File("src/test/resources/testDiffYaml3_2.yml");
        String expectedResult = """
                {
                - host: hexlet.io
                - timeout: 20
                - verbose: true
                }""";
        String actualResult = Differ.generate(file1, file2, "");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffJson5: files with nested data")
    void testDiffJson5() throws IOException {
        File file1 = new File("src/test/resources/testDiffJson5_1.json");
        File file2 = new File("src/test/resources/testDiffJson5_2.json");
        String expectedResult = """
                {
                  chars1: [a, b, c]
                - chars2: [d, e, f]
                + chars2: false
                - checked: false
                + checked: true
                - default: null
                + default: [value1, value2]
                - id: 45
                + id: null
                - key1: value1
                + key2: value2
                  numbers1: [1, 2, 3, 4]
                - numbers2: [2, 3, 4, 5]
                + numbers2: [22, 33, 44, 55]
                - numbers3: [3, 4, 5]
                + numbers4: [4, 5, 6]
                + obj1: {nestedKey=value, isNested=true}
                - setting1: Some value
                + setting1: Another value
                - setting2: 200
                + setting2: 300
                - setting3: true
                + setting3: none
                }""";
        String actualResult = Differ.generate(file1, file2, "stylish");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYaml3: files with nested data")
    void testDiffYaml3() throws IOException {
        File file1 = new File("src/test/resources/testDiffYaml5_1.yml");
        File file2 = new File("src/test/resources/testDiffYaml5_2.yml");
        String expectedResult = """
                {
                  chars1: [a, b, c]
                - chars2: [d, e, f]
                + chars2: false
                - checked: false
                + checked: true
                - default: null
                + default: [value1, value2]
                - id: 45
                + id: null
                - key1: value1
                + key2: value2
                  numbers1: [1, 2, 3, 4]
                - numbers2: [2, 3, 4, 5]
                + numbers2: [22, 33, 44, 55]
                - numbers3: [3, 4, 5]
                + numbers4: [4, 5, 6]
                + obj1: {nestedKey=value, isNested=true}
                - setting1: Some value
                + setting1: Another value
                - setting2: 200
                + setting2: 300
                - setting3: true
                + setting3: none
                }""";
        String actualResult = Differ.generate(file1, file2, "");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffJsonPlain: diff output formatted with plain")
    void testDiffJsonPlain() throws IOException {
        File file1 = new File("src/test/resources/testDiffJson5_1.json");
        File file2 = new File("src/test/resources/testDiffJson5_2.json");
        String expectedResult = """
                Property chars2 was updated. From [complex value] to false
                Property checked was updated. From false to true
                Property default was updated. From null to [complex value]
                Property id was updated. From 45 to null
                Property key1 was removed
                Property key2 was added with value: 'value2'
                Property numbers2 was updated. From [complex value] to [complex value]
                Property numbers3 was removed
                Property numbers4 was added with value: [complex value]
                Property obj1 was added with value: [complex value]
                Property setting1 was updated. From 'Some value' to 'Another value'
                Property setting2 was updated. From 200 to 300
                Property setting3 was updated. From true to 'none'
                """;
        String actualResult = Differ.generate(file1, file2, "plain");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYmlPlain: diff output formatted with plain")
    void testDiffYmlPlain() throws IOException {
        File file1 = new File("src/test/resources/testDiffYaml5_1.yml");
        File file2 = new File("src/test/resources/testDiffYaml5_2.yml");
        String expectedResult = """
                Property chars2 was updated. From [complex value] to false
                Property checked was updated. From false to true
                Property default was updated. From null to [complex value]
                Property id was updated. From 45 to null
                Property key1 was removed
                Property key2 was added with value: 'value2'
                Property numbers2 was updated. From [complex value] to [complex value]
                Property numbers3 was removed
                Property numbers4 was added with value: [complex value]
                Property obj1 was added with value: [complex value]
                Property setting1 was updated. From 'Some value' to 'Another value'
                Property setting2 was updated. From 200 to 300
                Property setting3 was updated. From true to 'none'
                """;
        String actualResult = Differ.generate(file1, file2, "plain");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("test run with cli - default format")
    void testRun1() {
        App.main(new String[]{"src/test/resources/testDiffYaml5_1.yml", "src/test/resources/testDiffYaml5_2.yml"});
    }

    @Test
    @DisplayName("test run with cli - stylish format")
    void testRun2() {
        App.main(new String[]{"src/test/resources/testDiffJson5_1.json", "src/test/resources/testDiffJson5_2.json", "-f stylish"});
    }

    @Test
    @DisplayName("test run with cli - plain format")
    void testRun3() {
        App.main(new String[]{"src/test/resources/testDiffJson5_1.json", "src/test/resources/testDiffJson5_2.json", "-f plain"});
    }

//    @Test
//    @DisplayName("test run with cli - no params")
//    void testRun4 () {
//        App.main(new String[] {});
//    }

    @Test
    @DisplayName("test parse file without extension")
    void testParseFileWithoutExtension() throws IOException {
        File file = new File("src/test/resources/testDiffDefault");
        Map<String, Object> expectedResult = Map.of("follow", false, "host", "hexlet.io",
                "proxy", "123.234.53.22", "timeout", 50);
        Map<String, Object> actualResult = Parser.parse(file);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("hgdfjhgdsf")
    void testmhbfjdfbvkfd() throws IOException {
        File file1 = new File("src/test/resources/testDiffJson5_1.json");
        File file2 = new File("src/test/resources/testDiffJson5_2.json");
        String expected = "[{\"key\":\"chars1\",\"value\":[\"a\",\"b\",\"c\"],\"param\":\"EQUALS\"}," +
                "{\"key\":\"chars2\",\"value\":[\"d\",\"e\",\"f\"],\"param\":\"REMOVE\"}," +
                "{\"key\":\"chars2\",\"value\":false,\"param\":\"ADD\"}," +
                "{\"key\":\"checked\",\"value\":false,\"param\":\"REMOVE\"}," +
                "{\"key\":\"checked\",\"value\":true,\"param\":\"ADD\"}," +
                "{\"key\":\"default\",\"value\":null,\"param\":\"REMOVE\"}," +
                "{\"key\":\"default\",\"value\":[\"value1\",\"value2\"],\"param\":\"ADD\"}" +
                ",{\"key\":\"id\",\"value\":45,\"param\":\"REMOVE\"}," +
                "{\"key\":\"id\",\"value\":null,\"param\":\"ADD\"}," +
                "{\"key\":\"key1\",\"value\":\"value1\",\"param\":\"REMOVE_ONE\"}," +
                "{\"key\":\"key2\",\"value\":\"value2\",\"param\":\"ADD_ONE\"}," +
                "{\"key\":\"numbers1\",\"value\":[1,2,3,4],\"param\":\"EQUALS\"}," +
                "{\"key\":\"numbers2\",\"value\":[2,3,4,5],\"param\":\"REMOVE\"}," +
                "{\"key\":\"numbers2\",\"value\":[22,33,44,55],\"param\":\"ADD\"}," +
                "{\"key\":\"numbers3\",\"value\":[3,4,5],\"param\":\"REMOVE_ONE\"}," +
                "{\"key\":\"numbers4\",\"value\":[4,5,6],\"param\":\"ADD_ONE\"}," +
                "{\"key\":\"obj1\",\"value\":{\"nestedKey\":\"value\",\"isNested\":true},\"param\":\"ADD_ONE\"}," +
                "{\"key\":\"setting1\",\"value\":\"Some value\",\"param\":\"REMOVE\"}," +
                "{\"key\":\"setting1\",\"value\":\"Another value\",\"param\":\"ADD\"}," +
                "{\"key\":\"setting2\",\"value\":200,\"param\":\"REMOVE\"}," +
                "{\"key\":\"setting2\",\"value\":300,\"param\":\"ADD\"}," +
                "{\"key\":\"setting3\",\"value\":true,\"param\":\"REMOVE\"}," +
                "{\"key\":\"setting3\",\"value\":\"none\",\"param\":\"ADD\"}]";
        String actual = Differ.generate(file1, file2, "json");
        assertEquals(actual, expected);
    }
}
