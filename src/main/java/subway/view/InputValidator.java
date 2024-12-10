package subway.view;

import subway.exception.ExceptionMessages;
import subway.view.answer.FunctionChoice;
import subway.view.answer.RetrieveChoice;

public class InputValidator {

    public static void validateFunctionChoice(String answer) {
        try {
            FunctionChoice.findByInputValue(answer);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_CHOICE_INPUT.getMessage());
        }
    }

    public static void validateRetrieveChoice(String answer) {
        try {
            RetrieveChoice.findByInputValue(answer);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_CHOICE_INPUT.getMessage());
        }
    }

    public static void validateInputValue(String answer) {
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.WRONG_VALUE_INPUT.getMessage());
        }
    }
}
