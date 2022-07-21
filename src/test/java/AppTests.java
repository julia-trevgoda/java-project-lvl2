import hexlet.code.Differ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

public class AppTests {

    @Test
    @DisplayName("'main' method works correctly")
    void testMain() throws IOException {
        String path = "src/test/resources/file1.json";
        File file1 = new File(path);
        File file2 = new File("src/test/resources/file2.json");
        String expectedResult = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        String actualResult = Differ.generate(file1, file2);
        assertEquals(actualResult, expectedResult);
    }
}
