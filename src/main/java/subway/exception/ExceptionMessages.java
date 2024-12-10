package subway.exception;

public enum ExceptionMessages {
    ;

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
