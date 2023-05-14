import java.util.ArrayList;

public class Problem {
    String name, url, inputType, outputType;
    ArrayList<String> testInputs;
    ArrayList<String> testOutputs;

    public Problem(String name, String url, String inputType, String outputType) {
        this.name = name;
        this.url = url;
        this.inputType = inputType;
        this.outputType = outputType;
        testInputs = new ArrayList<>();
        testOutputs = new ArrayList<>();
    }
}
