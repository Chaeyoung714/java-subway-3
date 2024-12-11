package subway.exception;

public enum ExceptionMessages {
    UNCONNECTED_STATIONS("출발역과 도착역은 연결 가능해야 합니다."),
    DUPLICATED_STATIONS("출발역과 도착역이 동일합니다."),
    STATION_NOT_EXISTS("존재하지 않는 역입니다."),
    WRONG_CHOICE_INPUT("올바른 값을 입력해주세요."),
    WRONG_VALUE_INPUT("값을 입력해주세요."),
    ;

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
