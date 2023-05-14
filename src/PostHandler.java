import io.javalin.http.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public class PostHandler implements Handler {
    // Will take up to (names.length) requests
    private int index;
    private String[] names;

    public PostHandler(String[] names) {
        if (names.length == 0) throw new IllegalArgumentException();
        this.names = names;
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        JSONObject jsonObject = new JSONObject(context.body());

        // Parse and format the input and output for submission to the website (System.in, FileInputStream)
        String inputType = jsonObject.getJSONObject("input").getString("type");
        if (inputType.equals("stdin")) inputType = "System.in";
        else inputType = "new FileInputStream(\"" + jsonObject.getJSONObject("input").getString("fileName") + "\")";

        String outputType = jsonObject.getJSONObject("output").getString("type");
        if (outputType.equals("stdout")) outputType = "System.out";
        else outputType = "new FileOutputStream(\"" + jsonObject.getJSONObject("output").getString("fileName") + "\")";

        // Construct the problem
        Problem problem = new Problem(
                names[index],
                jsonObject.getString("url"),
                inputType,
                outputType
        );

        // Add the test strings
        JSONArray tests = jsonObject.getJSONArray("tests");
        for (Object testObject : tests) {
            JSONObject test = (JSONObject) testObject;
            problem.testInputs.add(test.getString("input"));
            problem.testOutputs.add(test.getString("output"));
        }

        // Call the methods to generate the files
        Main.generateDataFiles(problem);
        Main.generateJavaJUnitCode(problem);
        Main.generateJavaSourceCode(problem);

        // Stop the program if no more arguments
        if (names.length == ++index) System.exit(0);
    }
}
