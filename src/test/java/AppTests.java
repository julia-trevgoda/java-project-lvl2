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
        String actualResult = Differ.generate(file1, file2);
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
        String actualResult = Differ.generate(file1, file2);
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
        String actualResult = Differ.generate(file1, file2);
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
        String actualResult = Differ.generate(file1, file2);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYml: yml format parser works correctly")
    void testDiffYml() throws IOException {
        File file1 = new File("src/test/resources/testDiffYaml1_1.yml");
        File file2 = new File("src/test/resources/testDiffYaml1_2.yml");
        String expectedResult = """
                {
                - follow_yml: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
        String actualResult = Differ.generate(file1, file2);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("testDiffYaml: yaml format parser works correctly")
    void testDiffYaml() throws IOException {
        File file1 = new File("src/test/resources/testDiffYaml2_1.yaml");
        File file2 = new File("src/test/resources/testDiffYaml2_2.yaml");
        String expectedResult = """
                {
                - follow_yaml: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
        String actualResult = Differ.generate(file1, file2);
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
        String actualResult = Differ.generate(file1, file2);
        assertEquals(actualResult, expectedResult);
    }
}
