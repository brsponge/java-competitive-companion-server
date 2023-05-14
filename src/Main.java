import io.javalin.Javalin;
import java.io.FileWriter;

public class Main {
    // Entry point for the server
    public static void main(String[] args) {
        PostHandler postHandler = new PostHandler(args);
        Javalin.create()
                .post("/", postHandler)
                .start(10043);
        System.out.println("Server started!");
    }

    // A utility method for writing to a file
    private static void writeFile(String fileName, String content) {
            try {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(content);
                fileWriter.close();
            } catch (Exception ignored) {}
    }

    // A method that generates the JUnit file
    public static void generateJavaJUnitCode(Problem problem) {
        int N = problem.testInputs.size();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            stringBuilder.append(String.format(RawStrings.TEST_METHOD_CODE, i, problem.name, problem.name, i, problem.name, i));
            if (i < N - 1) stringBuilder.append("\n\n");
        }

        String output = String.format(RawStrings.TEST_WRAPPER_CODE, problem.name, stringBuilder);
        writeFile(String.format("test/%sTest.java", problem.name), output);
    }

    // A method that generates the data files from the test cases
    public static void generateDataFiles(Problem problem) {
        int N = problem.testInputs.size();
        for (int i = 0; i < N; i++) {
            writeFile(String.format("data/%s-%d.in", problem.name, i), problem.testInputs.get(i));
            writeFile(String.format("data/%s-%d.out", problem.name, i), problem.testOutputs.get(i));
        }
    }

    // A method that generates the template source code
    public static void generateJavaSourceCode(Problem problem) {
        String content = String.format(RawStrings.SOURCE_TEMPLATE_CODE,
                problem.url,
                problem.name,
                problem.inputType,
                problem.outputType);
        writeFile(String.format("src/%s.java", problem.name), content);
    }
}