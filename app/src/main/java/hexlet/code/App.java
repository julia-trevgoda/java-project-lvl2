package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

public class App implements Callable<Integer> {

    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    @CommandLine.Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish]",
            defaultValue = "stylish"
    )
    private String format;

    @CommandLine.Parameters(
            index = "0",
            paramLabel = "filepath1",
            description = "path to first file"
    )
    private String filePath1;

    @CommandLine.Parameters(
            index = "1",
            paramLabel = "filepath2",
            description = "path to second file"
    )
    private String filePath2;

    /**
     * Diff.generate() method compares 2 files.
     * @return String as a result of comparing
     */
    @Override
    public Integer call() {
        try {
            Differ.generate(filePath1, filePath2, format);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }
}
