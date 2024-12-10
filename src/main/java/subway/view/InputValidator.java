package subway.view;

import subway.view.answer.FunctionChoice;
import subway.view.answer.RetrieveChoice;

public class InputValidator {

    public static void validateFunctionChoice(String answer) {
        try {
            FunctionChoice.findByInputValue(answer);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException("올바른 값을 입력해주세요.");
        }
    }

    public static void validateRetrieveChoice(String answer) {
        try {
            RetrieveChoice.findByInputValue(answer);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException("올바른 값을 입력해주세요.");
        }
    }
}
