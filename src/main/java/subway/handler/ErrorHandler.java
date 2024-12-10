package subway.handler;

public class ErrorHandler {

    public static void handleUserError(IllegalArgumentException e) {
        System.out.println(System.lineSeparator() + "[ERROR] " + e.getMessage());
    }

    private ErrorHandler() {
    }
}
