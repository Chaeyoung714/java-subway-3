package subway;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import subway.exception.ExceptionMessages;
import subway.view.InputValidator;

public class InputValidationTest {

    @Test
    void 전체_기능_선택시_없는_기능을_선택하면_예외가_발생한다() {
        String wrongInput = "2";

        assertThatThrownBy(() -> InputValidator.validateFunctionChoice(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_CHOICE_INPUT.getMessage());
    }

    @Test
    void 조회_기능_선택시_없는_기능을_선택하면_예외가_발생한다() {
        String wrongInput = "b";

        assertThatThrownBy(() -> InputValidator.validateRetrieveChoice(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_CHOICE_INPUT.getMessage());
    }

    @Test
    void 역_이름_입력시_빈값을_입력하면_예외가_발생한다() {
        String wrongInput = " ";

        assertThatThrownBy(() -> InputValidator.validateInputValue(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.WRONG_VALUE_INPUT.getMessage());
    }
}
