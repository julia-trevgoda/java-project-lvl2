package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

public class App implements Callable<Void> {

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
    private String file1;

    @CommandLine.Parameters(
            index = "1",
            paramLabel = "filepath2",
            description = "path to second file"
    )
    private String file2;

    /**
     * Diff.generate() method compares 2 files.
     * @return String as a result of comparing
     * @throws Exception when something goes wrong
     */
    @Override
    public Void call() throws Exception {
        System.out.println(Differ.generate(file1, file2, format));
        return null;
    }

    public static void main(String[] args) {
        try {
            new CommandLine(new App()).execute(args);
        } catch (Throwable throwable) {
            System.out.println("Error " + throwable);
        }
    }
}
