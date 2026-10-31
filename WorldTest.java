import java.io.*;
import java.nio.charset.StandardCharsets;

public class WorldTest {
    public static void main(String[] args) throws Exception {
        String expected = "Hello, World!";
        String compact = run("CompactWorld");
        String fresh = run("NewWorld");
        if (!expected.equals(compact)) {
            throw new AssertionError("CompactWorld output mismatch: " + compact);
        }
        if (!expected.equals(fresh)) {
            throw new AssertionError("NewWorld output mismatch: " + fresh);
        }
        if (!compact.equals(fresh)) {
            throw new AssertionError("Outputs differ: CompactWorld='" + compact + "', NewWorld='" + fresh + "'");
        }
        System.out.println("PASS: both programs output identical text");
    }

    private static String run(String className) throws Exception {
        Process process = new ProcessBuilder("java", className)
            .redirectErrorStream(true)
            .start();
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new AssertionError(className + " exited with code " + exitCode + ": " + output);
        }
        return output;
    }
}
