package subway.handler;

public class InformationHandler {

    public static void handleInformation(String information) {
        System.out.println(String.format("[INFO] %s", information));
    }

    private InformationHandler() {
    }
}
