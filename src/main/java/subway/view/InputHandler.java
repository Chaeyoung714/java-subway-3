package subway.view;


import subway.exception.RetryHandler;
import subway.view.answer.FunctionChoice;
import subway.view.answer.RetrieveChoice;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public FunctionChoice readFunctionChoice() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readFunctionChoice();
            InputValidator.validateFunctionChoice(answer);
            return FunctionChoice.findByInputValue(answer);
        });
    }

    public RetrieveChoice readRetrieveChoice() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readRetrieveChoice();
            InputValidator.validateRetrieveChoice(answer);
            return RetrieveChoice.findByInputValue(answer);
        });
    }

    public String readFirstStation() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readFirstStation();
            //TODO : 검증추가
            return answer;
        });
    }

    public String readLastStation() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readLastStation();
            //TODO : 검증추가
            return answer;
        });
    }
}
