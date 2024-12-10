package subway.view.answer;

import java.util.Arrays;

public enum FunctionChoice {
    RETRIEVE("1"),
    QUIT("Q"),
    ;

    private final String inputValue;

    FunctionChoice(String inputValue) {
        this.inputValue = inputValue;
    }

    public static FunctionChoice findByInputValue(String inputValue) {
        return Arrays.stream(FunctionChoice.values())
                .filter(a -> a.getInputValue().equals(inputValue))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private String getInputValue() {
        return inputValue;
    }
}
