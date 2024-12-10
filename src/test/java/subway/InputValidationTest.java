package subway;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import subway.view.InputValidator;

public class InputValidationTest {

    @Test
    void 전체_기능_선택시_없는_기능을_선택하면_예외가_발생한다() {
        String wrongInput = "2";

        assertThatThrownBy(() -> InputValidator.validateFunctionChoice(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 값을 입력해주세요.");
    }

    @Test
    void 조회_기능_선택시_없는_기능을_선택하면_예외가_발생한다() {
        String wrongInput = "b";

        assertThatThrownBy(() -> InputValidator.validateRetrieveChoice(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 값을 입력해주세요.");
    }

    @Test
    void 역_이름_입력시_빈값을_입력하면_예외가_발생한다() {
        String wrongInput = " ";

        assertThatThrownBy(() -> InputValidator.validateInputValue(wrongInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("값을 입력해주세요.");
    }
}
