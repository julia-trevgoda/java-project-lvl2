import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTests {

//    public static String getExpectedResultParse1(String pathToFile) throws IOException {
//        return Files.readString(Paths.get(pathToFile));
//    }
//    File expectedResultParse1 = Paths.get(pathToFile).toFile();

    public String expectedResultParse1 = "{chars1=[a, b, c], chars2=[d, e, f], " +
            "checked=false, default=null, id=45, key1=value1, numbers1=[1, 2, 3, 4]," +
            " numbers2=[2, 3, 4, 5], numbers3=[3, 4, 5], setting1=Some value," +
            " setting2=200, setting3=true}";
    public String expectedResultParse2 = "{chars1=[a, b, c], chars2=false, checked=true," +
            " default=[value1, value2], id=null, key2=value2, numbers1=[1, 2, 3, 4]," +
            " numbers2=[22, 33, 44, 55], numbers4=[4, 5, 6], obj1={nestedKey=value, isNested=true}," +
            " setting1=Another value, setting2=300, setting3=none}";

    //Tests on Parser (json, yml, yaml) > returns Map<String, Object>

    @Test
    @DisplayName("testParseJson: call JsonParser directly")
    void testParseJson() throws IOException {
        String file = "src/test/resources/testDiffJson1.json";
        Map<String, Object> actualResult = Parser.parseJson(file);
        assertEquals(actualResult.toString(), expectedResultParse1);
    }

    @Test
    @DisplayName("testParseJson2: call JsonParser through common parse method")
    void testParseJson2() throws IOException {
        String file = "src/test/resources/testDiffJson2.json";
        Map<String, Object> actualResult = Parser.parse(file);
        assertEquals(actualResult.toString(), expectedResultParse2);
    }

    @Test
    @DisplayName("testParseYml: call YmlParser directly")
    void testParseYml() throws IOException {
        String file = "src/test/resources/testDiffYml1.yml";
        Map<String, Object> actualResult = Parser.parseYml(file);
        assertEquals(actualResult.toString(), expectedResultParse1);
    }

    @Test
    @DisplayName("testParseYml2: call YmlParser through common parse method")
    void testParseYml2() throws IOException {
        String file = "src/test/resources/testDiffYml2.yml";
        Map<String, Object> actualResult = Parser.parse(file);
        assertEquals(actualResult.toString(), expectedResultParse2);
    }

    @Test
    @DisplayName("testParseYaml: call YamlParser directly")
    void testParseYaml() throws IOException {
        String file = "src/test/resources/testDiffYaml1.yaml";
        Map<String, Object> actualResult = Parser.parseYml(file);
        assertEquals(actualResult.toString(), expectedResultParse1);
    }

    @Test
    @DisplayName("testParseYaml: call YamlParser through common parse method")
    void testParseYaml2() throws IOException {
        String file = "src/test/resources/testDiffYaml2.yaml";
        Map<String, Object> actualResult = Parser.parse(file);
        assertEquals(actualResult.toString(), expectedResultParse2);
    }

    @Test
    @DisplayName("test parse file without extension")
    void testParseFileWithoutExtension() throws IOException {
        String file = "src/test/resources/testDiffDefault";
        Map<String, Object> expectedResult = Map.of("follow", false, "host", "hexlet.io",
                "proxy", "123.234.53.22", "timeout", 50);
        Map<String, Object> actualResult = Parser.parse(file);
        assertEquals(actualResult, expectedResult);
    }


    //Tests on Differ > returns *formatted* String

    @Test
    @DisplayName("testDiff: JSON: stylish")
    void testDiffJsonStylish() throws IOException {
        String file1 = "/Users/jtrevgoda/Hexlet/java-project-lvl2/src/test/resources/testDiffJson1.json";
        String file2 = "/Users/jtrevgoda/Hexlet/java-project-lvl2/src/test/resources/testDiffJson2.json";
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
    @DisplayName("testDiff: JSON: default")
    void testDiffJsonDefault() throws IOException {
        String file1 = "src/test/resources/testDiffJson1.json";
        String file2 = "src/test/resources/testDiffJson2.json";
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
        String actualResult = Differ.generate(file1, file2);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: YML: default format")
    void testDiffYmlDefault() throws IOException {
        String file1 = "src/test/resources/testDiffYml1.yml";
        String file2 = "src/test/resources/testDiffYml2.yml";
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
    @DisplayName("testDiff: JSON: plain")
    void testDiffJsonPlain() throws IOException {
        String file1 = "src/test/resources/testDiffJson1.json";
        String file2 = "src/test/resources/testDiffJson2.json";
        String expectedResult = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        String actualResult = Differ.generate(file1, file2, "plain");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: YAML: plain")
    void testDiffYamlPlain() throws IOException {
        String file1 = "src/test/resources/testDiffYaml1.yaml";
        String file2 = "src/test/resources/testDiffYaml2.yaml";
        String expectedResult = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        String actualResult = Differ.generate(file1, file2, "plain");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiff: JSON: Json")
    void testDiffJsonToJson() throws IOException {
        String file1 = "src/test/resources/testDiffJson1.json";
        String file2 = "src/test/resources/testDiffJson2.json";
        String expected = "[{\"key\":\"chars1\",\"value\":[\"a\",\"b\",\"c\"],\"param\":\"EQUALS\"}," +
                "{\"key\":\"chars2\",\"value\":[\"d\",\"e\",\"f\"],\"param\":\"REMOVE\"}," +
                "{\"key\":\"chars2\",\"value\":false,\"param\":\"ADD\"}," +
                "{\"key\":\"checked\",\"value\":false,\"param\":\"REMOVE\"}," +
                "{\"key\":\"checked\",\"value\":true,\"param\":\"ADD\"}," +
                "{\"key\":\"default\",\"value\":null,\"param\":\"REMOVE\"}," +
                "{\"key\":\"default\",\"value\":[\"value1\",\"value2\"],\"param\":\"ADD\"}," +
                "{\"key\":\"id\",\"value\":45,\"param\":\"REMOVE\"}," +
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

    @Test
    @DisplayName("testDiffJson1: file1.size() > file2.size()")
    void testdiffJson1() throws IOException {
        String file1 = "src/test/resources/testDiffJson1.json";
        String file2 = "src/test/resources/testDiffJson2_short.json";
        String expectedResult = """
                {
                  - chars1: [a, b, c]
                  - chars2: [d, e, f]
                  - checked: false
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  - key1: value1
                  - numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  - numbers3: [3, 4, 5]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  - setting2: 200
                  - setting3: true
                }""";
        String actualResult = Differ.generate(file1, file2, "");
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYaml1: file1.size() < file2.size()")
    void testdiffYaml1() throws IOException {
        String file1 = "src/test/resources/testDiffYaml1_short.yaml";
        String file2 = "src/test/resources/testDiffYaml2.yaml";
        String expectedResult = """
                Property 'chars1' was added with value: [complex value]
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was added with value: true
                Property 'default' was added with value: [complex value]
                Property 'id' was added with value: null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers1' was added with value: [complex value]
                Property 'numbers2' was added with value: [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was added with value: 'Another value'
                Property 'setting2' was added with value: 300
                Property 'setting3' was added with value: 'none'""";
        String actualResult = Differ.generate(file1, file2, "plain");
        assertEquals(actualResult, expectedResult);
    }
}