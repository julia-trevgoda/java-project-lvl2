package hexlet.code;

import picocli.CommandLine;

import java.io.File;
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
    String format;

    @CommandLine.Parameters(
            index = "0",
            paramLabel = "filepath1",
            description = "path to first file"
    )
    private File file1;

    @CommandLine.Parameters(
            index = "1",
            paramLabel = "filepath2",
            description = "path to second file"
    )
    private File file2;

    @Override
    public Void call() throws Exception {

        var diff = Differ.generate(file1, file2);
        System.out.println(diff);
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
