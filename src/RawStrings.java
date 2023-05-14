public class RawStrings {
    public static final String SOURCE_TEMPLATE_CODE = """
            import java.util.*;
            import java.io.*;
            import java.math.*;

            // %s
            public class %s {
                public static void main(String[] args) throws Exception {
                    solve(%s, %s);
                }

                public static void solve(InputStream inputStream, OutputStream outputStream) {
                    FastScanner in = new FastScanner(inputStream);
                    PrintWriter out = new PrintWriter(outputStream);



                    out.flush();
                }

                private static class FastScanner {
                    private BufferedReader bufferedReader;
                    private StringTokenizer stringTokenizer;
                    public FastScanner(InputStream inputStream) {
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    }

                    public String next() {
                        try {
                            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens())
                                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                            return stringTokenizer.nextToken();
                        } catch (Exception ignored) {}
                        return null;
                    }

                    public int nextInt() {return Integer.parseInt(next());}
                    public long nextLong() {return Long.parseLong(next());}
                    public double nextDouble() {return Double.parseDouble(next());}
                }
            }""";

    public static final String TEST_METHOD_CODE = """
            \s\s\s\s@Test
            \s\s\s\svoid test%s() throws Exception {
            \s\s\s\s\s\s\s\sByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            \s\s\s\s\s\s\s\s%s.solve(new FileInputStream("data/%s-%s.in"), byteArrayOutputStream);
            \s\s\s\s\s\s\s\sUtility.test("data/%s-%s.out", byteArrayOutputStream);
            \s\s\s\s}""";

    public static final String TEST_WRAPPER_CODE = """
            import org.junit.jupiter.api.Test;
            import java.io.*;
            
            public class %sTest {
            %s
            }""";
}
