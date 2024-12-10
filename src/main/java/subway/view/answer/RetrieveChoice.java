package subway.view.answer;

import java.util.Arrays;

public enum RetrieveChoice {
    BY_DISTANCE("1"),
    BY_TIME("2"),
    BACK("B"),
    ;

    private final String inputValue;

    RetrieveChoice(String inputValue) {
        this.inputValue = inputValue;
    }

    public static RetrieveChoice findByInputValue(String inputValue) {
        return Arrays.stream(RetrieveChoice.values())
                .filter(a -> a.getInputValue().equals(inputValue))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private String getInputValue() {
        return inputValue;
    }
}
